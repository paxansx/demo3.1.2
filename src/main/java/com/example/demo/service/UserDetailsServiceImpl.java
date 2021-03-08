package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.demo.dao.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.model.User;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService,UserService {

    private final UserRepository userRepository;


    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByName(s);
    }
    @Override
    public void addUser(User user) {
        if (userRepository.findByName(user.getName())==null){
            userRepository.save(user);

        }

    }

    @Override
    public void updateUser(User user) {

        if (userRepository.findByName(user.getName())==null||
                userRepository.findByName(user.getName()).getId().equals(user.getId())) {
            userRepository.save(user);
        }
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }



}
