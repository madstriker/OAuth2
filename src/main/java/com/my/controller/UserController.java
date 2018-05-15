package com.my.controller;

import com.my.model.User;
import com.my.requestdto.UserRequestDto;
import com.my.responsedto.UserResponseDto;
import com.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

import static com.my.commons.WebUrlConstant.User.*;

@RestController
@RequestMapping(USER)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> add(@RequestBody @Valid UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        System.out.println(userResponseDto);
        return new ResponseEntity(userResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers()  {
        List<UserResponseDto> userResponseDtoList = userService.getUsers();
        return new ResponseEntity(userResponseDtoList, HttpStatus.OK);
    }


    @GetMapping(GET_USER_BY_ID)
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        UserResponseDto user = userService.getUser(userId);
        if(user==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
