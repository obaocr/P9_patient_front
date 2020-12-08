package com.ocr.p9front.services;

import com.ocr.p9front.domain.User;
import com.ocr.p9front.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
    }
}


