package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.BidList;
import com.ocr.poseidon.domain.CurvePoint;
import com.ocr.poseidon.repositories.CurvePointRepository;
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

// TODO des methodes en erreur

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@WebMvcTest(CurveController.class)
class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointRepository curvePointRepository;

    // Pour utiliser que ce bean dans un contexte avec spring security ... ça passe
    @Configuration
    static class ContextConfiguration {
        @Bean
        public CurveController getBCurveController() {
            return new CurveController();
        }
    }

    @Test
    void GetAllShouldReturnOK() throws Exception {
        List<CurvePoint> curvePoints = new ArrayList<>();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(5);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(10.0);
        curvePoints.add(curvePoint);
        when(curvePointRepository.findAll()).thenReturn(curvePoints);

        this.mockMvc.perform(get("/curvePoint/list")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(status().isOk());

        // Verify curvePointRepository.findAll is called
        verify(curvePointRepository, Mockito.times(1)).findAll();
    }

    @Test
    void GetForAddShouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/curvePoint/add")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("curvePoint/add"))
                .andExpect(status().isOk());
    }

    @Test
    void AddWithCurvePointShouldRedirect() throws Exception {
        // Inutile mais je laisse
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(5);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(10.0);
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePoint);

        this.mockMvc.perform(post("/curvePoint/validate")
                .param("curveId","99")
                .param("term","10.0")
                .param("value","11.0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/curvePoint/list"));

        verify(curvePointRepository, Mockito.times(1)).save(any());
    }

    @Test
    void AddWithoutParamShouldReturnView() throws Exception {
        this.mockMvc.perform(post("/curvePoint/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("curvePoint/add"))
                .andExpect(status().isOk());
    }

    @Test
    void GetUpdateShouldReturnOK() throws Exception {
        final String UPDATE_URL = "/curvePoint/update/" + "1";
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(5);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(10.0);
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));

        this.mockMvc.perform(get(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithoutParamShouldReturnView() throws Exception {
        final String UPDATE_URL = "/curvePoint/update/" + "1";
        this.mockMvc.perform(post(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithParamShouldReturnRedirect() throws Exception {
        final String UPDATE_URL = "/curvePoint/update/" + "1";
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(5);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(10.0);
        when(curvePointRepository.findById(any())).thenReturn(Optional.of(curvePoint));

        this.mockMvc.perform(post(UPDATE_URL)
                .param("curveId","15")
                .param("term","10.0")
                .param("value","15.0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/curvePoint/list"));

        verify(curvePointRepository, Mockito.times(1)).save(any());
    }

    @Test
    void DeleteWithParamShouldReturnRedirect() throws Exception {
        final String DELETE_URL = "/curvePoint/delete/" + "1";
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(5);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(10.0);
        when(curvePointRepository.findById(any())).thenReturn(Optional.of(curvePoint));

        this.mockMvc.perform(get(DELETE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/curvePoint/list"));

        verify(curvePointRepository, Mockito.times(1)).delete(any());
    }
}
