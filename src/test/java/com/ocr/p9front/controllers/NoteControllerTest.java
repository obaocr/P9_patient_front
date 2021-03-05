package com.ocr.p9front.controllers;

import com.ocr.p9front.domain.NoteDTO;
import com.ocr.p9front.domain.PatientDTO;
import com.ocr.p9front.domain.PatientRiskDTO;
import com.ocr.p9front.proxies.AssessProxy;
import com.ocr.p9front.services.NoteProxyService;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test Class for NoteController
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@WebMvcTest(NoteControllerTest.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientProxyService patientProxyService;

    @MockBean
    private NoteProxyService noteProxyService;

    @MockBean
    private AssessProxy assessProxy;

    // This bean in spring security context
    @Configuration
    static class ContextConfiguration {
        @Bean
        public NoteController getNoteController() {
            return new NoteController();
        }
    }

    @Test
    void GetNotesForPatientShouldReturnOK() throws Exception {
        List<NoteDTO> notes = new ArrayList<>();
        NoteDTO note = new NoteDTO();
        note.setPatientId(1);
        note.setTitle("Title");
        note.setNote("Note");
        notes.add(note);

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

        PatientRiskDTO patientRiskDTO = new PatientRiskDTO();
        patientRiskDTO.setPatientId(1);
        patientRiskDTO.setCalculated(true);
        patientRiskDTO.setPatientInfo("Infos patient");
        patientRiskDTO.setRisk("None");

        when(noteProxyService.getNoteByPatientId(1)).thenReturn(notes);
        when(patientProxyService.getPatientById(1)).thenReturn(patient);
        when(assessProxy.getPatientRiskById(1)).thenReturn(patientRiskDTO);

        this.mockMvc.perform(get("/notes/1")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("note/list"))
                .andExpect(status().isOk());
        verify(noteProxyService, Mockito.times(1)).getNoteByPatientId(any());
    }

    @Test
    void DeleteNoteWithParamShouldReturnRedirect() throws Exception {
        final String DELETE_URL = "/note/delete/" + "1";

        NoteDTO note = new NoteDTO();
        note.setNoteId("1");
        note.setPatientId(1);
        note.setTitle("Title");
        note.setNote("Note");

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

        PatientRiskDTO patientRiskDTO = new PatientRiskDTO();
        patientRiskDTO.setPatientId(1);
        patientRiskDTO.setCalculated(true);
        patientRiskDTO.setPatientInfo("Infos patient");
        patientRiskDTO.setRisk("None");

        when(noteProxyService.getNoteByNoteId("1")).thenReturn(note);
        when(patientProxyService.getPatientById(1)).thenReturn(patient);
        when(assessProxy.getPatientRiskById(1)).thenReturn(patientRiskDTO);

        this.mockMvc.perform(get(DELETE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("note/list"))
                .andExpect(status().isOk());

        verify(noteProxyService, Mockito.times(1)).deleteNoteByNoteId(any());
    }

    @Test
    void GetForAddShouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/note/add/1")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("note/add"))
                .andExpect(status().isOk());
    }

    @Test
    void AddWithoutParamShouldReturnView() throws Exception {
        this.mockMvc.perform(post("/note/validate/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("note/add"))
                .andExpect(status().isOk());
    }

    @Test
    void AddWithNoteShouldRedirect() throws Exception {
        NoteDTO note = new NoteDTO();
        note.setNoteId("1");
        note.setPatientId(1);
        note.setTitle("Title");
        note.setNote("Note");
        when(noteProxyService.addNote(any(NoteDTO.class))).thenReturn(note);

        this.mockMvc.perform(post("/note/validate/1")
                .param("title","Title")
                .param("note","Note")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/notes/1"));

        verify(noteProxyService, Mockito.times(1)).addNote(any());
    }

    @Test
    void GetUpdateShouldReturnOK() throws Exception {
        final String UPDATE_URL = "/note/update/" + "1";

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

        NoteDTO note = new NoteDTO();
        note.setNoteId("1");
        note.setPatientId(1);
        note.setTitle("Title");
        note.setNote("Note");
        when(noteProxyService.getNoteByNoteId("1")).thenReturn(note);

        this.mockMvc.perform(get(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithoutParamShouldReturnView() throws Exception {
        final String UPDATE_URL = "/note/update/" + "1";
        this.mockMvc.perform(post(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("note/update"))
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithParamShouldReturnRedirect() throws Exception {
        final String UPDATE_URL = "/note/update/" + "1";

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

        NoteDTO note = new NoteDTO();
        note.setNoteId("1");
        note.setPatientId(1);
        note.setTitle("Title");
        note.setNote("Note");
        when(noteProxyService.getNoteByNoteId("1")).thenReturn(note);

        this.mockMvc.perform(post(UPDATE_URL)
                .param("noteId","1")
                .param("title","Title")
                .param("note","Note")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/notes/1"));

        verify(noteProxyService, Mockito.times(1)).updateNote(any());
    }

}
