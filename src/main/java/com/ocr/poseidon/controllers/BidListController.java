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

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        // TO-DO call service find all bids to show to the view
        log.debug("home");
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    // !!!! j'ai modifie et mis (Model model) avant il y avait (BidList bid) :
    public String addBidForm(Model model) {
        log.debug("addBidForm");
        BidList bidList = new BidList();
        model.addAttribute("bidList", bidList);
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        log.debug("validate");
        //  TO-DO check data valid and save to db, after saving return bid list
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "bidList/add";
        }
        bidListRepository.save(bid);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        //  TO-DO get Bid by Id and to model then show to the form
        log.debug("showUpdateForm");
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        //  TO-DO check required fields, if valid call service to update Bid and return list Bid
        log.debug("updateBid");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            // TODO à voir si nécessaire bidList.setBidListId(id);
            return "bidList/update";
        }
        BidList bidListToUpdate = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        bidListToUpdate.setAccount(bidList.getAccount());
        bidListToUpdate.setType(bidList.getType());
        bidListToUpdate.setBidQuantity(bidList.getBidQuantity());
        bidListRepository.save(bidListToUpdate);
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        //  TO-DO Find Bid by Id and delete the bid, return to Bid list
        log.debug("deleteBid");
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        bidListRepository.delete(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }

}
