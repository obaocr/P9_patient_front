package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.BidList;
import com.ocr.poseidon.repositories.BidListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {SecurityFilterAutoConfiguration.class, SecurityAutoConfiguration.class})
class BidlistControllerIntgTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BidListRepository bidListRepository;

    // Exemple pour tests mockmvc Spring MVC https://modernjava.io/testing-spring-mvc-mockmvc/

    @BeforeEach
    @Test
    void deleteAll() {
        bidListRepository.deleteAll();
    }

    @Test
    @WithUserDetails("admin")
    void GetAllShouldReturnOK() throws Exception {
        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setAccount("Account");
        bid.setType("Type");
        bid.setBidQuantity(10.0);
        bidListRepository.save(bid);

        this.mockMvc.perform(get("/bidList/list")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("bidList/list"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(1));
    }

    @Test
    @WithUserDetails("admin")
    void UpdateShouldReturnOK() throws Exception {

        BidList bid = new BidList();
        bid.setAccount("Account");
        bid.setType("Type");
        bid.setBidQuantity(10.0);
        bidListRepository.save(bid);

        List<BidList> bidLists = new ArrayList<>();
        bidLists = bidListRepository.findAll();
        bid = bidLists.get(0);
        String UPDATE_URL = "/bidList/update/" + bid.getBidListId();

        this.mockMvc.perform(post(UPDATE_URL)
                .with(csrf())
                .param("account","Account-UPDATED")
                .param("type","Type")
                .param("bidQuantity","10.0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/bidList/list"));

        BidList bidUpdated =  bidListRepository.findAll().get(0);
        assertTrue(bidUpdated.getAccount().equals("Account-UPDATED"));
    }

    @Test
    @WithUserDetails("admin")
    void DeleteShouldReturnOK() throws Exception {
        final String DELETE_URL = "/bidList/delete/" + "1";

        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setAccount("Account");
        bid.setType("Type");
        bid.setBidQuantity(10.0);
        bidListRepository.save(bid);

        this.mockMvc.perform(get(DELETE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/bidList/list"));

        // Verification que c'est vide car il y a un delete All en Before
        List<BidList> bidListLists = bidListRepository.findAll();
        assertTrue(bidListLists.size() == 0);


    }
}
