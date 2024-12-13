package application.converter;

import application.common.RoleEnum;
import application.entity.Role;
import application.entity.User;
import application.exception.RoleNotExistsException;
import application.model.dto.UserDTO;
import application.model.request.SignUpUserRequest;
import application.model.request.UserCreationRequest;
import application.model.request.UserUpdateRequest;
import application.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserConvert {
    @Autowired
    ModelMapper mapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    private Long roleUserID = 3L;
    public User toUser(Object request){
        User user = mapper.map(request, User.class);
        Role role = roleRepository.findById(roleUserID).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
    public UserDTO toUserDTO(User user){
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        userDTO.setFullName(user.getFirstName() + " " + user.getLastName());
        return userDTO;
    }
    public User toUserUpdate(UserUpdateRequest request, User user){
        if(request.getPassword() != null){
            user.setPassword(passwordEncoder.encode(request.getPassword()));
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
        return user;
    }
}
