package com.ocr.p9front.controllers;

import com.ocr.p9front.domain.PatientDTO;
import com.ocr.p9front.services.PatientProxyService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientProxyService patientProxyService;

    // Pour utiliser que ce bean dans un contexte avec spring security ... ça passe
    @Configuration
    static class ContextConfiguration {
        @Bean
        public PatientController getBPatientController() {
            return new PatientController();
        }
    }

    @Test
    void GetAllShouldReturnOK() throws Exception {
        List<PatientDTO> patients = new ArrayList<>();
        PatientDTO patient = new PatientDTO();
        LocalDate birth = LocalDate.of(2000,1,15);
        LocalDateTime dtTest = LocalDateTime.now();
        patient.setId(1);
        patient.setAddress("12 rue des oliviers");
        patient.setFamilly("TestFamille");
        patient.setGiven("Test");
        patient.setSex("M");
        patient.setBirthDate(birth);
        patient.setCreateDate(dtTest);
        patient.setUpdateDate(dtTest);
        patients.add(patient);
        when(patientProxyService.getAllPatients()).thenReturn(patients);

        this.mockMvc.perform(get("/patient/list")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("patient/list"))
                .andExpect(status().isOk());

        verify(patientProxyService, Mockito.times(1)).getAllPatients();
    }

    @Test
    void GetForAddShouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/patient/add")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("patient/add"))
                .andExpect(status().isOk());
    }

    @Test
    void AddWithoutParamShouldReturnView() throws Exception {
        this.mockMvc.perform(post("/patient/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("patient/add"))
                .andExpect(status().isOk());
    }

    @Test
    void AddWithPatientShouldRedirect() throws Exception {
        when(patientProxyService.addPatient(any(PatientDTO.class))).thenReturn(1);

        this.mockMvc.perform(post("/patient/validate")
                .param("familly","martin")
                .param("given","test")
                .param("phone","0102030405")
                .param("address","25 avenue des ternes")
                .param("sex","M")
                .param("birthDate","01/01/1900")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/patient/list"));

        verify(patientProxyService, Mockito.times(1)).addPatient(any());
    }

    @Test
    void UpdateWithoutParamShouldReturnView() throws Exception {
        final String UPDATE_URL = "/patient/update/" + "1";
        this.mockMvc.perform(post(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("patient/update"))
                .andExpect(status().isOk());
    }

    @Test
    void GetUpdateShouldReturnOK() throws Exception {
        final String UPDATE_URL = "/patient/update/" + "1";
        PatientDTO patient = new PatientDTO();
        LocalDate birth = LocalDate.of(2000,1,15);
        LocalDateTime dtTest = LocalDateTime.now();
        patient.setId(1);
        patient.setAddress("12 rue des oliviers");
        patient.setFamilly("TestFamille");
        patient.setGiven("Test");
        patient.setSex("M");
        patient.setBirthDate(birth);
        patient.setCreateDate(dtTest);
        patient.setUpdateDate(dtTest);
        when(patientProxyService.getPatientById(1)).thenReturn(patient);

        this.mockMvc.perform(get(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithParamShouldReturnRedirect() throws Exception {
        final String UPDATE_URL = "/patient/update/" + "1";

        PatientDTO patient = new PatientDTO();
        LocalDate birth = LocalDate.of(2000,1,15);
        LocalDateTime dtTest = LocalDateTime.now();
        patient.setId(1);
        patient.setAddress("12 rue des oliviers");
        patient.setFamilly("TestFamille");
        patient.setGiven("Test");
        patient.setSex("M");
        patient.setBirthDate(birth);
        patient.setCreateDate(dtTest);
        patient.setUpdateDate(dtTest);
        when(patientProxyService.getPatientById(any())).thenReturn(patient);

        this.mockMvc.perform(post(UPDATE_URL)
                .param("familly","martin")
                .param("given","test")
                .param("phone","0102030405")
                .param("address","25 avenue des ternes")
                .param("sex","M")
                .param("birthDate","01/01/1900")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/patient/list"));

        // Verify bidListRepository.save is called
        verify(patientProxyService, Mockito.times(1)).updatePatient(any());
    }
    @Test
    void DeleteWithParamShouldReturnRedirect() throws Exception {
        final String DELETE_URL = "/patient/delete/" + "1";

        PatientDTO patient = new PatientDTO();
        LocalDate birth = LocalDate.of(2000,1,15);
        LocalDateTime dtTest = LocalDateTime.now();
        patient.setId(1);
        patient.setAddress("12 rue des oliviers");
        patient.setFamilly("TestFamille");
        patient.setGiven("Test");
        patient.setSex("M");
        patient.setBirthDate(birth);
        patient.setCreateDate(dtTest);
        patient.setUpdateDate(dtTest);
        when(patientProxyService.getPatientById(any())).thenReturn(patient);

        this.mockMvc.perform(get(DELETE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/patient/list"));

        verify(patientProxyService, Mockito.times(1)).deletePatientById(any());
    }


}
