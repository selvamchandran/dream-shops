package com.selvam.dreamshops.service.user;

import com.selvam.dreamshops.dto.UserDto;
import com.selvam.dreamshops.model.User;
import com.selvam.dreamshops.request.CreateUserRequest;
import com.selvam.dreamshops.request.UpdateUserRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);
}
