package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.CurvePoint;
import com.ocr.poseidon.domain.Rating;
import com.ocr.poseidon.repositories.RatingRepository;
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
@WebMvcTest(RatingController.class)
class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingRepository ratingRepository;

    // Pour utiliser que ce bean dans un contexte avec spring security ... ça passe
    @Configuration
    static class ContextConfiguration {
        @Bean
        public RatingController getRatingController() {
            return new RatingController();
        }
    }

    @Test
    void GetAllShouldReturnOK() throws Exception {
        List<Rating> ratings = new ArrayList<>();
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("OK");
        rating.setSandPRating("OK");
        rating.setFitchRating("OK");
        rating.setOrderNumber(1);
        ratings.add(rating);
        when(ratingRepository.findAll()).thenReturn(ratings);

        this.mockMvc.perform(get("/rating/list")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("rating/list"))
                .andExpect(status().isOk());

        verify(ratingRepository, Mockito.times(1)).findAll();
    }

    @Test
    void GetForAddShouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/rating/add")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("rating/add"))
                .andExpect(status().isOk());
    }

    @Test
    void AddWithRatingShouldRedirect() throws Exception {
        // Inutile mais je laisse
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("OK");
        rating.setSandPRating("OK");
        rating.setFitchRating("OK");
        rating.setOrderNumber(1);
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        this.mockMvc.perform(post("/rating/validate")
                .param("moodysRating","moodysRating")
                .param("sandPRating","sandPRating")
                .param("sandPRating","sandPRating")
                .param("orderNumber","1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/rating/list"));

        verify(ratingRepository, Mockito.times(1)).save(any());
    }

    @Test
    void AddWithoutParamShouldReturnView() throws Exception {
        this.mockMvc.perform(post("/rating/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("rating/add"))
                .andExpect(status().isOk());
    }

    @Test
    void GetUpdateShouldReturnOK() throws Exception {
        final String UPDATE_URL = "/rating/update/" + "1";
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("OK");
        rating.setSandPRating("OK");
        rating.setFitchRating("OK");
        rating.setOrderNumber(1);
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        this.mockMvc.perform(get(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithoutParamShouldReturnView() throws Exception {
        final String UPDATE_URL = "/rating/update/" + "1";
        this.mockMvc.perform(post(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("rating/update"))
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithParamShouldReturnRedirect() throws Exception {
        final String UPDATE_URL = "/rating/update/" + "1";

        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("OK");
        rating.setSandPRating("OK");
        rating.setFitchRating("OK");
        rating.setOrderNumber(1);
        when(ratingRepository.findById(any())).thenReturn(Optional.of(rating));

        this.mockMvc.perform(post(UPDATE_URL)
                .param("moodysRating","moodysRating")
                .param("sandPRating","sandPRating")
                .param("sandPRating","sandPRating")
                .param("orderNumber","1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/rating/list"));

        verify(ratingRepository, Mockito.times(1)).save(any());
    }

    @Test
    void DeleteWithParamShouldReturnRedirect() throws Exception {
        final String DELETE_URL = "/rating/delete/" + "1";

        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("OK");
        rating.setSandPRating("OK");
        rating.setFitchRating("OK");
        rating.setOrderNumber(1);
        when(ratingRepository.findById(any())).thenReturn(Optional.of(rating));

        this.mockMvc.perform(get(DELETE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/rating/list"));

        verify(ratingRepository, Mockito.times(1)).delete(any());
    }
}
