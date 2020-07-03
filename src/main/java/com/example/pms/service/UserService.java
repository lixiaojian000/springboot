package com.example.pms.service;

import com.example.pms.pojo.User;
import com.example.pms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public List<User> findAll(){
        List<User> users=userRepository.findAll();
        return users;
    }
    public List<User> findAl(){
        List<User> users=userRepository.findAl();
        return users;
    }
    public User login(String username,String password){
        User us=userRepository.login(username,password);
        return us;
    }
    public User findUserByUsername(String username){
        User us=userRepository.findUserByUsername(username);
        return us;
    };
    public Set<String> findRoleByUsername(String username){
        Set<String> us=userRepository.findRoleByUsername(username);
        return us;
    };
}
