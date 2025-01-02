package application.controller;

import application.entity.User;
import application.model.dto.UserDTO;
import application.model.request.UserCreationRequest;
import application.model.request.UserUpdateRequest;
import application.model.response.APIResponse;
import application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserAPI {
    @Autowired
    UserService userService;

    @GetMapping("/my-info")
    APIResponse<?> getMyInfo(){
        User user = userService.getMyInfo();
        return APIResponse.<User>builder()
                .result(user)
                .build();
    }
    @PostMapping
    APIResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        return APIResponse.<User>builder()
                .result(userService.createUser(request))
                .build();
    }
    @PutMapping("/{userID}")
    APIResponse<UserDTO> updateUser(@RequestBody UserUpdateRequest request,
                                    @PathVariable Long userID){
        UserDTO user = userService.updateUser(request, userID);
        return APIResponse.<UserDTO>builder()
                .result(user)
                .build();
    }

    @DeleteMapping("/{userID}")
    APIResponse<String> deleteUser(@PathVariable Long userID){
        userService.deleteUser(userID);
        return APIResponse.<String>builder()
                .result("DELETE_SUCCESS")
                .build();
    }
    @GetMapping("{userID}")
    APIResponse<UserDTO> getUser(@PathVariable Long userID){
        return APIResponse.<UserDTO>builder()
                .result(userService.getUser(userID))
                .build();
    }

    @GetMapping
    APIResponse<List<UserDTO>> getUsers(@RequestParam Map<String, String> params){
        return APIResponse.<List<UserDTO>>builder()
                .result(userService.getUsers(params))
                .build();
    }
}
