package com.my.serviceImpl;

import com.my.dao.UserDAO;
import com.my.model.User;
import com.my.requestdto.UserRequestDto;
import com.my.responsedto.UserResponseDto;
import com.my.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service(value="userService")
@Transactional
public class UserServiceImpl implements UserDetailsService,UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.passwordEncoder=passwordEncoder;
        this.userDAO = userDAO;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userDAO.addUser(new User( "admin", passwordEncoder.encode("admin"),
                3000,33,"admin@yahoo.com"));

        User user=userDAO.getUser(username);
        if(user==null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthority());
    }

    private List getAuthority(){
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List findAll(){
        List list=new ArrayList();
        userDAO.getUsers().iterator().forEachRemaining(list::add);
        return list;
    }










    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        User user1 = userDAO.getUserByEmailId(userRequestDto.getEmail());
        if (user1 == null) {
            User user = new User();
            user.setUsername(userRequestDto.getUsername());
            user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
            user.setAge(userRequestDto.getAge());
            user.setSalary(userRequestDto.getSalary());
            user.setEmail(userRequestDto.getEmail());
            userDAO.addUser(user);
            UserResponseDto userResponseDto =new UserResponseDto();
            BeanUtils.copyProperties(user, userResponseDto);
            return userResponseDto;

        } else {
            throw new RuntimeException("user already exit.");
        }
    }

    public List<UserResponseDto> getUsers() {
        List<User> userList= userDAO.getUsers();

        if(userList==null || userList.size()==0){
            throw new RuntimeException("cannot find userList.");
        }else{
            List<UserResponseDto> userResponseDtoList =new ArrayList<>();
            for(User user:userList){
                UserResponseDto userResponseDto =new UserResponseDto();
                BeanUtils.copyProperties(user, userResponseDto);
                userResponseDtoList.add(userResponseDto);
            }
            return userResponseDtoList;
        }
    }

    @Override
    public UserResponseDto getUser(String userName) {
        UserResponseDto userResponseDto = new UserResponseDto();
        User user=userDAO.getUser(userName);
        BeanUtils.copyProperties(user, userResponseDto);
        return userResponseDto;
    }


}
