package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.BidList;
import com.ocr.poseidon.repositories.BidListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BidlistControllerIntgTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BidListRepository bidListRepository;

    // Exemple pour tests mockmvc pring MVC https://modernjava.io/testing-spring-mvc-mockmvc/

    @BeforeEach
    @Test
    @WithUserDetails("admin")
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

    // TODO .. KO, dans les logs on voit le body à NULL, pour le post ça devrait être dans le body...

    @Test
    @WithUserDetails("admin")
    void UpdateShouldReturnOK() throws Exception {
        final String UPDATE_URL = "/bidList/update/" + "1";

        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setAccount("Account");
        bid.setType("Type");
        bid.setBidQuantity(10.0);
        bidListRepository.save(bid);

        this.mockMvc.perform(post(UPDATE_URL)
                .param("account","compte1")
                .param("type","type1")
                .param("bidQuantity","1.0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/bidList/list"));
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
