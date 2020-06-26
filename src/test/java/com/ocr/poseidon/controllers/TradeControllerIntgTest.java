package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.CurvePoint;
import com.ocr.poseidon.domain.Trade;
import com.ocr.poseidon.repositories.CurvePointRepository;
import com.ocr.poseidon.repositories.TradeRepository;
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
class TradeControllerIntgTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TradeRepository tradeRepository;

    // Exemple pour tests mockmvc Spring MVC https://modernjava.io/testing-spring-mvc-mockmvc/

    @BeforeEach
    @Test
    void deleteAll() {
        tradeRepository.deleteAll();
    }

    @Test
    @WithUserDetails("admin")
    void GetAllShouldReturnOK() throws Exception {
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(10.0);
        tradeRepository.save(trade);

        this.mockMvc.perform(get("/trade/list")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("trade/list"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(1));
    }
}
