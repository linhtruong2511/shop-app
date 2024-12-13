package application.service.impl;

import application.converter.UserConvert;
import application.entity.Role;
import application.entity.User;
import application.exception.*;
import application.model.dto.UserDTO;
import application.model.dto.UserI;
import application.model.request.SignUpUserRequest;
import application.model.request.UserCreationRequest;
import application.model.request.UserUpdateRequest;
import application.repository.RoleRepository;
import application.repository.UserRepository;
import application.secutity.UserDetailsImpl;
import application.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    PasswordEncoder passwordEncoder;
    UserConvert userConvert;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
    public User createUser(UserCreationRequest request) {
        checkExistedUser(request);
        User user = userConvert.toUser(request);
        return userRepository.save(user);
    }

    @PostAuthorize("returnObject.username == authentication.principal.username")
    public User getMyInfo(){
        var user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User createUser(SignUpUserRequest request) {
        checkExistedUser(request);
        User user = userConvert.toUser(request);
        return userRepository.save(user);
    }

    private void checkExistedUser(UserI request) {
        // check user mới có trùng thông tin với các user cũ không
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("USER NAME IS ALREADY EXISTS");
        } else if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("EMAIL IS ALREADY EXISTS");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
    @Override
    public UserDTO getUser(Long userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));
        if (!user.isEnabled()) {
            throw new UserDeletedException("USER IS DELETED");
        }
        return userConvert.toUserDTO(user);
    }



    @Override
    public List<UserDTO> getUsers(Map<String, String> params) {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findByUsernameContaining(
                params.getOrDefault("username", ""));
        if (!users.isEmpty()) {
            users.forEach(item -> userDTOS.add(userConvert.toUserDTO(item)));
        }
        return userDTOS;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public UserDTO updateUser(UserUpdateRequest request, Long userID) {
        User user = userRepository.findById(userID).orElseThrow(
                () -> new UserNotFoundException("user not exists")
        );
        if (!user.isEnabled()) {
            throw new UserDeletedException("USER IS DELETED");
        }
        user = userConvert.toUserUpdate(request, user);
        userRepository.save(user);
        return userConvert.toUserDTO(user);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("USER NOT EXISTS"));
        if (!user.isEnabled()) {
            throw new UserDeletedException("USER IS DELETED");
        }
        user.setEnabled(false);
        userRepository.save(user);
    }
}
