package application.service;

import application.entity.User;
import application.model.dto.UserDTO;
import application.model.request.SignUpUserRequest;
import application.model.request.UserCreationRequest;
import application.model.request.UserUpdateRequest;

import java.util.List;
import java.util.Map;

public interface UserService {
    User createUser(UserCreationRequest request);
    User createUser(SignUpUserRequest request);
    UserDTO getUser(Long userID);
    List<UserDTO> getUsers(Map<String, String> params);
    UserDTO updateUser(UserUpdateRequest request, Long userID);
    void deleteUser(Long UserId);
    User getMyInfo();

}
