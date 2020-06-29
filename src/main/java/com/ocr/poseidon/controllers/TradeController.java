package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.BidList;
import com.ocr.poseidon.domain.Trade;
import com.ocr.poseidon.repositories.BidListRepository;
import com.ocr.poseidon.repositories.TradeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Class TradeController for Trade
 */

@Controller
public class TradeController {

    private static final Logger log = LogManager.getLogger(TradeController.class);

    @Autowired
    TradeRepository tradeRepository;

    /**
     * Endpoint for trade list
     * @param model
     * @return trade list
     */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        log.debug("home");
        model.addAttribute("trades", tradeRepository.findAll());
        log.info("trades displayed");
        return "trade/list";
    }

    /**
     * Endpoint to display Trade add IHM
     * @param model
     */
    @GetMapping("/trade/add")
    public String addTrade(Model model) {
        log.debug("addTrade");
        Trade trade = new Trade();
        model.addAttribute("trade", trade);
        log.info("trades get for add");
        return "trade/add";
    }

    /**
     * Endpoint to validate for adding Trade
     * @param trade Trade object to be added
     * @param result technical result
     * @param model
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        log.debug("validate");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "trade/add";
        }
        tradeRepository.save(trade);
        log.info("trades valmidated for add");
        return "redirect:/trade/list";
    }

    /**
     * Endpoint to display Trade updating form
     * @param id Trade id to be updated
     * @param model
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.debug("showUpdateForm");
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        log.info("trades get for update");
        return "trade/update";
    }

    /**
     * Endpoint to validate for update
     * @param id is the Trade id
     * @param trade is the Trade object to be updated
     * @param result technical result
     * @param model
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        log.debug("updateTrade");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "trade/update";
        }
        Trade tradeToUpdate = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeToUpdate.setAccount(trade.getAccount());
        tradeToUpdate.setType(trade.getType());
        tradeToUpdate.setBuyQuantity(trade.getBuyQuantity());
        tradeRepository.save(tradeToUpdate);
        model.addAttribute("trades", tradeRepository.findAll());
        log.info("trades updated");
        return "redirect:/trade/list";
    }

    /**
     * Endpoint to delete a Trade object
     * @param id is the Trade id
     * @param model
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        log.debug("deleteTrade");
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeRepository.delete(trade);
        model.addAttribute("trades", tradeRepository.findAll());
        log.info("trades deleted");
        return "redirect:/trade/list";
    }
}
