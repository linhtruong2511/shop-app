package application.service.impl;

import application.common.RoleEnum;
import application.converter.UserConvert;
import application.entity.Role;
import application.entity.User;
import application.exception.EmailAlreadyExistsException;
import application.exception.RoleNotExistsException;
import application.exception.UsernameAlreadyExistsException;
import application.model.dto.UserDTO;
import application.model.request.LoginUserRequest;
import application.model.request.SignUpUserRequest;
import application.repository.RoleRepository;
import application.repository.UserRepository;
import application.service.AuthService;
import application.service.UserService;
import application.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserConvert userConvert;
    @Override
    public UserDTO signUp(SignUpUserRequest signUpRequestDTO) {
        User user = userService.createUser(signUpRequestDTO);
        return userConvert.toUserDTO(user);
    }
    @Override
    public String login(LoginUserRequest loginUserRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserRequest.getUsername(),
                        loginUserRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateToken(authentication);
    }
}
