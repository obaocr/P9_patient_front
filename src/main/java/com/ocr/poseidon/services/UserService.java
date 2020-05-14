package com.ocr.poseidon.services;

import com.ocr.poseidon.domain.User;
import com.ocr.poseidon.repositories.UserRepository;
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


