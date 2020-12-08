package com.ocr.medicalcare.controllers;

import com.ocr.medicalcare.domain.User;
import com.ocr.medicalcare.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)

class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    // Pour utiliser que ce bean dans un contexte avec spring security ... ça passe
    @Configuration
    static class ContextConfiguration {
        @Bean
        public UserController getUserController() {
            return new UserController();
        }
    }

    @Test
    void GetAllShouldReturnOK() throws Exception {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setFullname("fullname");
        user.setUsername("username");
        user.setPassword("Passwo8!");
        user.setRole("USER");
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        this.mockMvc.perform(get("/user/list")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("user/list"))
                .andExpect(status().isOk());

        verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    void GetForAddShouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/user/add")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("user/add"))
                .andExpect(status().isOk());
    }

    @Test
    void AddWithUserShouldRedirect() throws Exception {
        // Inutile mais je laisse
        User user = new User();
        user.setId(1);
        user.setFullname("fullname");
        user.setUsername("username");
        user.setPassword("Passwo8!");
        user.setRole("USER");
        when(userRepository.save(any(User.class))).thenReturn(user);

        this.mockMvc.perform(post("/user/validate")
                .param("fullname", "Fullname")
                .param("username", "Username")
                .param("password", "Passwo8!")
                .param("role", "USER")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/user/list"));

        verify(userRepository, Mockito.times(1)).save(any());
    }

    @Test
    void AddWithoutParamShouldReturnView() throws Exception {
        this.mockMvc.perform(post("/user/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("user/add"))
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithoutParamShouldReturnView() throws Exception {
        final String UPDATE_URL = "/user/update/" + "1";
        this.mockMvc.perform(post(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("user/update"))
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithParamShouldReturnRedirect() throws Exception {
        final String UPDATE_URL = "/user/update/" + "1";

        User user = new User();
        user.setId(1);
        user.setFullname("fullname");
        user.setUsername("Username");
        user.setPassword("Passwo8!");
        user.setRole("USER");
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        this.mockMvc.perform(post(UPDATE_URL)
                .param("fullname", "Fullname")
                .param("username", "Username")
                .param("password", "Passwo8!")
                .param("role", "USER")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/user/list"));

        verify(userRepository, Mockito.times(1)).save(any());
    }

    @Test
    void DeleteWithParamShouldReturnRedirect() throws Exception {
        final String DELETE_URL = "/user/delete/" + "1";

        User user = new User();
        user.setId(1);
        user.setFullname("fullname");
        user.setUsername("Username");
        user.setPassword("Passwo8!");
        user.setRole("USER");
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        this.mockMvc.perform(get(DELETE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/user/list"));

        verify(userRepository, Mockito.times(1)).delete(any());
    }
}
