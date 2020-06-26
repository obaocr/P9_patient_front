package com.ocr.poseidon.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@WebMvcTest(BidListController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Configuration
    static class ContextConfiguration {
        @Bean
        public HomeController getHomeController() {
            return new HomeController();
        }
    }

    @Test
    void GetHomeOK() throws Exception {
        this.mockMvc.perform(get("/")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("home"));
    }

    @Test
    void GetAdminHomeOK() throws Exception {
        this.mockMvc.perform(get("/admin/home")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/bidList/list"));
    }

}
