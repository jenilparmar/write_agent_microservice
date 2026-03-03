package com.alwriter.ContentGeneratorAgent.service;

import com.alwriter.ContentGeneratorAgent.entity.Project;
import com.alwriter.ContentGeneratorAgent.entity.User;
import com.alwriter.ContentGeneratorAgent.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public int updateUserPreference(UUID userId , String userPreferneces) {
        return userrepo.updatePreferenceByUserId(userId , userPreferneces);
    }

}
