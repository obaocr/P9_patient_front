package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.RuleName;
import com.ocr.poseidon.domain.Trade;
import com.ocr.poseidon.repositories.TradeRepository;
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
@WebMvcTest(TradeController.class)
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeRepository tradeRepository;

    // Pour utiliser que ce bean dans un contexte avec spring security ... ça passe
    @Configuration
    static class ContextConfiguration {
        @Bean
        public TradeController getTradeController() {
            return new TradeController();
        }
    }

    @Test
    void GetAllShouldReturnOK() throws Exception {
        List<Trade> trades = new ArrayList<>();
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(10.0);
        trades.add(trade);
        when(tradeRepository.findAll()).thenReturn(trades);

        this.mockMvc.perform(get("/trade/list")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("trade/list"))
                .andExpect(status().isOk());

        // Verify bidListRepository.findAll is called
        verify(tradeRepository, Mockito.times(1)).findAll();
    }

    @Test
    void GetForAddShouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/trade/add")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("trade/add"))
                .andExpect(status().isOk());
    }

    // TODO avec ou sans param c'est OK... bizarre
    @Test
    void AddWithTradeShouldRedirect() throws Exception {
        // Inutile mais je laisse
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(10.0);
        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);

        this.mockMvc.perform(post("/trade/validate")
                .param("account","compte1")
                .param("type","type1")
                //.param("buyQuantity","1.0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/trade/list"));

        // Verify bidListRepository.save is called
        verify(tradeRepository, Mockito.times(1)).save(any());
    }

    @Test
    void AddWithoutParamShouldReturnView() throws Exception {
        this.mockMvc.perform(post("/trade/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("trade/add"))
                .andExpect(status().isOk());
    }

    @Test
    void GetUpdateShouldReturnOK() throws Exception {
        final String UPDATE_URL = "/trade/update/" + "1";
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(10.0);
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        this.mockMvc.perform(get(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithoutParamShouldReturnView() throws Exception {
        final String UPDATE_URL = "/trade/update/" + "1";
        this.mockMvc.perform(post(UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(view().name("trade/update"))
                .andExpect(status().isOk());
    }

    @Test
    void UpdateWithParamShouldReturnRedirect() throws Exception {
        final String UPDATE_URL = "/trade/update/" + "1";

        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(10.0);
        when(tradeRepository.findById(any())).thenReturn(Optional.of(trade));

        this.mockMvc.perform(post(UPDATE_URL)
                .param("account","compte1")
                .param("type","type1")
                .param("buyQuantity","1.0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/trade/list"));

        // Verify Repository.save is called
        verify(tradeRepository, Mockito.times(1)).save(any());
    }

    @Test
    void DeleteWithParamShouldReturnRedirect() throws Exception {
        final String DELETE_URL = "/trade/delete/" + "1";

        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(10.0);
        when(tradeRepository.findById(any())).thenReturn(Optional.of(trade));

        this.mockMvc.perform(get(DELETE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(redirectedUrl("/trade/list"));

        // Verify bidListRepository.save is called
        verify(tradeRepository, Mockito.times(1)).delete(any());
    }

}
