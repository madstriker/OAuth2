package com.my.dao;

import com.my.model.User;

import java.util.List;

public interface UserDAO {
    User addUser(User user);
    List<User> getUsers();
    User getUser(String userName);
    User getUserByEmailId(String email);
}
