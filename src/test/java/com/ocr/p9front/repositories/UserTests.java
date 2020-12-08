package com.ocr.medicalcare.repositories;

import com.ocr.medicalcare.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll() {
        List<User> users = userRepository.findAll();
        assertTrue(users.size() > 0);
    }

    @Test
    public void findByUserName() {
        User user = userRepository.findByUserName("user");
        assertTrue(user.getFullname().equals("User"));

    }
}
