package com.alwriter.ContentGeneratorAgent.service;

import com.alwriter.ContentGeneratorAgent.entity.Project;
import com.alwriter.ContentGeneratorAgent.entity.User;
import com.alwriter.ContentGeneratorAgent.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userrepo;
    public UserService(UserRepository userrepo){
        this.userrepo = userrepo;
    }

    public User findUser(String email){

        User user = userrepo.findUserByEmail(email).orElseThrow(()-> new RuntimeException("User not Found"));
        return  user;
    }


}
