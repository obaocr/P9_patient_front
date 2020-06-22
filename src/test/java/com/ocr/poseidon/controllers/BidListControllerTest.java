package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.BidList;
import com.ocr.poseidon.repositories.BidListRepository;
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
@WebMvcTest(BidListController.class)
class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListRepository bidListRepository;

    // Pour utiliser que ce bean dans un contexte avec spring security ... ça passe
    @Configuration
    static class ContextConfiguration {
        @Bean
        public BidListController getBidListController() {
            return new BidListController();
        }
    }

    @Test
    void GetAllShouldReturnOK() throws Exception {
        List<BidList> bidlists = new ArrayList<>();
        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setAccount("Account");
        bid.setType("Type");
        bid.setBidQuantity(10.0);
        bidlists.add(bid);
        when(bidListRepository.findAll()).thenReturn(bidlists);

        this.mockMvc.perform(get("/bidList/list")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("bidList/list"))
                .andExpect(status().isOk());

        // Verify bidListRepository.findAll is called
        verify(bidListRepository, Mockito.times(1)).findAll();
    }

    @Test
    void GetForAddShouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/bidList/add")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("bidList/add"))
                .andExpect(status().isOk());
    }

    // TODO avec ou sans param c'est OK... bizarre
    @Test
    void AddWithBidlistShouldRedirect() throws Exception {
        // Inutile mais je laisse
        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setAccount("compte1");
        bid.setType("type1");
        bid.setBidQuantity(1.0);
        when(bidListRepository.save(any(BidList.class))).thenReturn(bid);

        this.mockMvc.perform(post("/bidList/validate")
                .param("account","compte1")
                .param("type","type1")
                //.param("bidQuantity","1.0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/bidList/list"));

        // Verify bidListRepository.save is called
        verify(bidListRepository, Mockito.times(1)).save(any());
    }

    @Test
    void AddWithoutParamShouldReturnView() throws Exception {
        this.mockMvc.perform(post("/bidList/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("bidList/add"))
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithoutParamShouldReturnView() throws Exception {
        final String UPDATE_URL = "/bidList/update/" + "1";
        this.mockMvc.perform(post(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("bidList/update"))
                .andExpect(status().isOk());
    }

    // TODO Avec ou sans param ca marche ... le bodye st  null je pense...
    @Test
    void UpdateWithParamShouldReturnRedirect() throws Exception {
        final String UPDATE_URL = "/bidList/update/" + "1";

        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setAccount("Account");
        bid.setType("Type");
        bid.setBidQuantity(10.0);
        when(bidListRepository.findById(any())).thenReturn(Optional.of(bid));

        this.mockMvc.perform(post(UPDATE_URL)
                .param("account","compte1")
                .param("type","type1")
                .param("bidQuantity","1.0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/bidList/list"));

        // Verify bidListRepository.save is called
        verify(bidListRepository, Mockito.times(1)).save(any());
    }

    @Test
    void DeleteWithParamShouldReturnRedirect() throws Exception {
        final String DELETE_URL = "/bidList/delete/" + "1";

        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setAccount("Account");
        bid.setType("Type");
        bid.setBidQuantity(10.0);
        when(bidListRepository.findById(any())).thenReturn(Optional.of(bid));

        this.mockMvc.perform(get(DELETE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/bidList/list"));

        // Verify bidListRepository.save is called
        verify(bidListRepository, Mockito.times(1)).delete(any());
    }
}

