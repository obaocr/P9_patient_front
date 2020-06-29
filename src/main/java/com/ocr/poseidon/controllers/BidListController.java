package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.BidList;
import com.ocr.poseidon.repositories.BidListRepository;
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
 * Class BidListController for Bidlist
 */

@Controller
public class BidListController {

    private static final Logger log = LogManager.getLogger(BidListController.class);

    @Autowired
    BidListRepository bidListRepository;

    /**
     * Endpoint for bidList list
     * @param model
     * @return bidlist list
     */
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        log.debug("home");
        model.addAttribute("bidLists", bidListRepository.findAll());
        log.info("bidList displayed");
        return "bidList/list";
    }

    /**
     * Endpoint to display BidList add IHM
     * @param model
     */
    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        log.debug("addBidForm");
        BidList bidList = new BidList();
        model.addAttribute("bidList", bidList);
        log.info("bidList get for add");
        return "bidList/add";
    }

    /**
     * Endpoint to validate for adding BidList
     * @param bid BidList object to be added
     * @param result technical result
     * @param model
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        log.debug("validate");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "bidList/add";
        }
        bidListRepository.save(bid);
        log.info("bidList validated");
        return "redirect:/bidList/list";
    }

    /**
     * Endpoint to display BidList updating form
     * @param id BidList id to be updated
     * @param model
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.debug("showUpdateForm");
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bidList);
        log.info("bidList get for update");
        return "bidList/update";
    }

    /**
     * Endpoint to validate for update
     * @param id is the BidList id
     * @param bidList is the BidList object to be updated
     * @param result technical result
     * @param model
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        log.debug("updateBid");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "bidList/update";
        }
        BidList bidListToUpdate = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        bidListToUpdate.setAccount(bidList.getAccount());
        bidListToUpdate.setType(bidList.getType());
        bidListToUpdate.setBidQuantity(bidList.getBidQuantity());
        bidListRepository.save(bidListToUpdate);
        model.addAttribute("bidLists", bidListRepository.findAll());
        log.info("bidList updated");
        return "redirect:/bidList/list";
    }

    /**
     * Endpoint to delete a BidList object
     * @param id is the BidList id
     * @param model
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        log.debug("deleteBid");
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        bidListRepository.delete(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
        log.info("bidList deleted");
        return "redirect:/bidList/list";
    }

}
