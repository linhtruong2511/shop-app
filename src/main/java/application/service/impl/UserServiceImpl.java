package application.service.impl;

import application.entity.Role;
import application.entity.User;
import application.exception.*;
import application.model.dto.UserDTO;
import application.model.request.UserCreationRequest;
import application.model.request.UserUpdateRequest;
import application.repository.RoleRepository;
import application.repository.UserRepository;
import application.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.GenericDeclaration;
import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    ModelMapper mapper;
    RoleRepository roleRepository;

    @Override
    public User createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new UsernameAlreadyExistsException("USER NAME IS ALREADY EXISTS");
        }
        else if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("EMAIL IS ALREADY EXISTS");
        }
        Set<Role> roles = new HashSet<>();
        for(Long id : request.getRoleID()){
            roles.add(roleRepository.findById(id).orElseThrow(
                    () -> new RoleNotExistsException("ROLE IS NOT EXISTS")
            ));
        }
        User user = mapper.map(request, User.class);
        user.setRoles(roles);
        user.setPassword(encodePassword(request.getPassword()));
        return userRepository.save(user);
    }
    private String encodePassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(15);
        return encoder.encode(password);
    }
    @Override
    public UserDTO getUser(Long userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier not found"));
        if(!user.isEnabled()){
            throw new UserDeletedException("USER IS DELETED");
        }
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        userDTO.setFullName(user.getFirstName() + " " + user.getLastName());
        return userDTO;
    }

    @Override
    public List<UserDTO> getUsers(Map<String, String> params) {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findByUsernameContaining(
                params.getOrDefault("username", ""));
        if(!users.isEmpty())
            users.forEach(
                    item -> {
                        if(item.isEnabled()) {
                                UserDTO userDTO = mapper.map(item, UserDTO.class);
                                userDTO.setFullName(item.getFirstName() + " " + item.getLastName());
                                userDTOS.add(userDTO);
                            }
                        }
                    );
        return userDTOS;
    }

    @Override
    public UserDTO updateUser(UserUpdateRequest request, Long userID) {
        User user = userRepository.findById(userID).orElseThrow(
                () ->  new UserNotFoundException("user not exists")
        );
        if(user.isEnabled()){
            throw new UserDeletedException("USER IS DELETED");
        }
        mappingUser(user, request);
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("USER NOT EXISTS"));
        if(!user.isEnabled()){
            throw new UserDeletedException("USER IS DELETED");
        }
        user.setEnabled(false);
        userRepository.save(user);
    }
    private void mappingUser(User user, UserUpdateRequest request){
        if(request.getPassword() != null){
            user.setPassword(request.getPassword());
        }
        if(request.getPhoneNumber() != null){
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if(request.getAddress() != null){
            user.setAddress(request.getAddress());
        }
        if(request.getDob() != null){
            user.setDod(request.getDob());
        }
        if(request.getRoleIDs() != null && !request.getRoleIDs().isEmpty()){
            user.getRoles().forEach(role ->
                    role.getUsers().remove(user)
                    );
            user.getRoles().clear();
            Set<Role> roles = new HashSet<>();
            request.getRoleIDs().forEach(id ->
                    roles.add(roleRepository.findById(id).orElseThrow(
                            () -> new RoleNotExistsException("ROLE ID(" + id + ") NOT EXISTS")
                    )));
            user.setRoles(roles);
        }
    }
}
