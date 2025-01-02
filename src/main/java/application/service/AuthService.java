package application.service;

import application.model.dto.UserDTO;
import application.model.request.LoginUserRequest;
import application.model.request.SignUpUserRequest;

public interface AuthService {
    UserDTO signUp(SignUpUserRequest signUpRequestDTO);
    String login(LoginUserRequest loginUserRequest);
}
