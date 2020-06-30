package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.CurvePoint;
import com.ocr.poseidon.domain.RuleName;
import com.ocr.poseidon.repositories.CurvePointRepository;
import com.ocr.poseidon.repositories.RuleNameRepository;
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
public class RuleNameControllerIntgTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RuleNameRepository ruleNameRepository;

    // Exemple pour tests mockmvc Spring MVC https://modernjava.io/testing-spring-mvc-mockmvc/

    @BeforeEach
    @Test
    void deleteAll() {
        ruleNameRepository.deleteAll();
    }

    @Test
    @WithUserDetails("admin")
    void GetAllShouldReturnOK() throws Exception {
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Name");
        ruleName.setDescription("Desc");
        ruleName.setTemplate("Template");
        ruleName.setJson("Json");
        ruleName.setSqlStr("SQL");
        ruleNameRepository.save(ruleName);

        this.mockMvc.perform(get("/ruleName/list")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(1));
    }
}