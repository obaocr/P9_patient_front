package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.BidList;
import com.ocr.poseidon.domain.CurvePoint;
import com.ocr.poseidon.repositories.BidListRepository;
import com.ocr.poseidon.repositories.CurvePointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {SecurityFilterAutoConfiguration.class, SecurityAutoConfiguration.class})
class CurveControllerIntgTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurvePointRepository curvePointRepository;

    // Exemple pour tests mockmvc Spring MVC https://modernjava.io/testing-spring-mvc-mockmvc/

    @BeforeEach
    @Test
    void deleteAll() {
        curvePointRepository.deleteAll();
    }

    @Test
    @WithUserDetails("admin")
    void GetAllShouldReturnOK() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(5);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(10.0);
        curvePointRepository.save(curvePoint);

        this.mockMvc.perform(get("/curvePoint/list")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(1));
    }
}
