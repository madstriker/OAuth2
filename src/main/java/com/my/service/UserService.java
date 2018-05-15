package com.my.service;


import com.my.requestdto.UserRequestDto;
import com.my.responsedto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto addUser(UserRequestDto userRequestDto);
    List<UserResponseDto> getUsers();
    UserResponseDto getUser(String userName);

}
