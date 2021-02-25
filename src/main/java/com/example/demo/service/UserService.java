package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;


public interface UserService {
    public void addUser(User user);
    public void updateUser(User user);
    public void removeUser(long id);
    public User getUserById(long id);
    public List<User> getAllUser();
    Object loadUserByUsername(String name);
}
