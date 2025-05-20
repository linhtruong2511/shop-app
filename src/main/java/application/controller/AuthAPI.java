package application.controller;

import application.model.dto.UserDTO;
import application.model.request.LoginUserRequest;
import application.model.request.SignUpUserRequest;
import application.model.response.APIResponse;
import application.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/")
public class AuthAPI {
    @Autowired
    AuthService authService;

    @PostMapping("sign-up")
    APIResponse<UserDTO> signUp(@RequestBody SignUpUserRequest request) {
        UserDTO userDTO = authService.signUp(request);
        return APIResponse.<UserDTO>builder()
                .result(userDTO)
                .build();
    }

    @PostMapping("sign-in")
    APIResponse<String> login(@RequestBody LoginUserRequest request) {
        String token = authService.login(request);
        return APIResponse.<String>builder()
                .result(token)
                .build();
    }
}
