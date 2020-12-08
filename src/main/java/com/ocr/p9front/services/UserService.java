package com.ocr.medicalcare.services;

import com.ocr.medicalcare.domain.User;
import com.ocr.medicalcare.repositories.UserRepository;
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


