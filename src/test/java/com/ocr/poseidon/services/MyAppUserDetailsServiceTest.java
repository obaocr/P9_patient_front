package com.ocr.poseidon.services;

import com.ocr.poseidon.domain.User;
import com.ocr.poseidon.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MyAppUserDetailsServiceTest {

        @MockBean
        private UserRepository userRepository;

        @Autowired
        private MyAppUserDetailsService testedMyAppUserDetailsService;

        @Test
        public void LoginTest() {
            User user = new User();
            user.setUsername("user1");
            user.setFullname("utilisateur1");
            user.setRole("User");
            user.setPassword("Parach1!");

            when(userRepository.findByUserName("user1")).thenReturn(user);
            UserDetails ud = testedMyAppUserDetailsService.loadUserByUsername(user.getUsername());
            String userDetails = ud.toString();

            //Assert
            assertNotNull(userDetails);
            assertTrue(ud.getPassword() == user.getPassword());
            assertTrue(ud.getUsername() == user.getUsername());
        }
}
