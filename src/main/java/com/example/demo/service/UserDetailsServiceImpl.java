package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dao.UserDao;
import org.springframework.stereotype.Service;
import com.example.demo.model.User;
import java.util.List;
import java.util.Optional;



@Service
public class UserDetailsServiceImpl implements UserDetailsService,UserService {

    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.findByName(s);
    }
    @Override
    public void addUser(User user) {
        if (userDao.findByName(user.getName())==null){
            userDao.save(user);

        }

    }

    @Override
    public void updateUser(User user) {
        if (userDao.findByName(user.getName()).getId().equals(user.getId())) {
            userDao.save(user);
        }
    }

    @Override
    public void removeUser(long id) {
        userDao.deleteById(id);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getOne(id);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.findAll();
    }



}
