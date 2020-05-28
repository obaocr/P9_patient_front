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
import java.util.Optional;


@Controller
public class BidListController {

    private static final Logger log = LogManager.getLogger(BidListController.class);

    @Autowired
    BidListRepository bidListRepository;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // call service find all bids to show to the view
        model.addAttribute("bidlists", bidListRepository.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    // !!!! j'ai modifie et mis (Model lodel)
    //public String addBidForm(BidList bid) {
    public String addBidForm(Model model) {
        BidList bidList = new BidList();
        model.addAttribute("bidList", bidList);
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        //  check data valid and save to db, after saving return bid list
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "bidList/add";
        }
        bidListRepository.save(bid);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        //  get Bid by Id and to model then show to the form
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        //  check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "bidList/update";
        }
        // TODO celà génère un INSERT au lieu de update ....
        bidListRepository.save(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        //  Find Bid by Id and delete the bid, return to Bid list
        bidListRepository.deleteById(id);
        return "redirect:/bidList/list";
    }

}
