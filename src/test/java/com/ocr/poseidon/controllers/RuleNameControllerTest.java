package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.Rating;
import com.ocr.poseidon.domain.RuleName;
import com.ocr.poseidon.repositories.RuleNameRepository;
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
@WebMvcTest(RuleNameController.class)
class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameRepository ruleNameRepository;

    // Pour utiliser que ce bean dans un contexte avec spring security ... ça passe
    @Configuration
    static class ContextConfiguration {
        @Bean
        public RuleNameController getRuleNameController() {
            return new RuleNameController();
        }
    }

    @Test
    void GetAllShouldReturnOK() throws Exception {
        List<RuleName> ruleNames = new ArrayList<>();
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Name");
        ruleName.setDescription("Desc");
        ruleName.setTemplate("Template");
        ruleName.setJson("Json");
        ruleName.setSqlStr("SQL");
        ruleNames.add(ruleName);
        when(ruleNameRepository.findAll()).thenReturn(ruleNames);

        this.mockMvc.perform(get("/ruleName/list")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("ruleName/list"))
                .andExpect(status().isOk());

        verify(ruleNameRepository, Mockito.times(1)).findAll();
    }

    @Test
    void GetForAddShouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/ruleName/add")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("ruleName/add"))
                .andExpect(status().isOk());
    }

    @Test
    void AddWithRuleNameShouldRedirect() throws Exception {
        // Inutile mais je laisse
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Name");
        ruleName.setDescription("Desc");
        ruleName.setTemplate("Template");
        ruleName.setJson("Json");
        ruleName.setSqlStr("SQL");
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(ruleName);

        this.mockMvc.perform(post("/ruleName/validate")
                .param("name","compte1")
                .param("description","type1")
                .param("template","type1")
                .param("json","type1")
                .param("sqlStr","sql")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/ruleName/list"));

        verify(ruleNameRepository, Mockito.times(1)).save(any());
    }

    @Test
    void AddWithoutParamShouldReturnView() throws Exception {
        this.mockMvc.perform(post("/ruleName/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("ruleName/add"))
                .andExpect(status().isOk());
    }

    @Test
    void GetUpdateShouldReturnOK() throws Exception {
        final String UPDATE_URL = "/ruleName/update/" + "1";
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Name");
        ruleName.setDescription("Desc");
        ruleName.setTemplate("Template");
        ruleName.setJson("Json");
        ruleName.setSqlStr("SQL");
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));

        this.mockMvc.perform(get(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithoutParamShouldReturnView() throws Exception {
        final String UPDATE_URL = "/ruleName/update/" + "1";
        this.mockMvc.perform(post(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("ruleName/update"))
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithParamShouldReturnRedirect() throws Exception {
        final String UPDATE_URL = "/ruleName/update/" + "1";

        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Name");
        ruleName.setDescription("Desc");
        ruleName.setTemplate("Template");
        ruleName.setJson("Json");
        ruleName.setSqlStr("SQL");
        when(ruleNameRepository.findById(any())).thenReturn(Optional.of(ruleName));

        this.mockMvc.perform(post(UPDATE_URL)
                .param("name","compte1")
                //.param("description","type1")
                .param("template","type1")
                .param("json","type1")
                .param("sqlStr","sql")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/ruleName/list"));

        verify(ruleNameRepository, Mockito.times(1)).save(any());
    }

    @Test
    void DeleteWithParamShouldReturnRedirect() throws Exception {
        final String DELETE_URL = "/ruleName/delete/" + "1";

        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Name");
        ruleName.setDescription("Desc");
        ruleName.setTemplate("Template");
        ruleName.setJson("Json");
        ruleName.setSqlStr("SQL");
        when(ruleNameRepository.findById(any())).thenReturn(Optional.of(ruleName));

        this.mockMvc.perform(get(DELETE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/ruleName/list"));

        verify(ruleNameRepository, Mockito.times(1)).delete(any());
    }

}
