package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.BidList;
import com.ocr.poseidon.domain.Rating;
import com.ocr.poseidon.repositories.BidListRepository;
import com.ocr.poseidon.repositories.RatingRepository;
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
 * Class RatingController for Rating
 */

@Controller
public class RatingController {

    private static final Logger log = LogManager.getLogger(RatingController.class);

    @Autowired
    RatingRepository ratingRepository;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // TO-DO: find all Rating, add to model
        log.debug("home");
        model.addAttribute("ratings", ratingRepository.findAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    // !!!! j'ai modifie et mis (Model model) avant il y avait (Rating rating) :
    public String addRatingForm(Model model) {
        log.debug("addRatingForm");
        Rating rating = new Rating();
        model.addAttribute("rating", rating);
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // TO-DO: check data valid and save to db, after saving return Rating list
        log.debug("validate");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "rating/add";
        }
        ratingRepository.save(rating);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TO-DO: get Rating by Id and to model then show to the form
        log.debug("showUpdateForm");
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // TO-DO: check required fields, if valid call service to update Rating and return Rating list
        log.debug("updateRating");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "rating/update";
        }
        Rating ratingToUpdate = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingToUpdate.setOrderNumber(rating.getOrderNumber());
        ratingToUpdate.setFitchRating(rating.getFitchRating());
        ratingToUpdate.setMoodysRating(rating.getMoodysRating());
        ratingToUpdate.setSandPRating(rating.getSandPRating());
        ratingRepository.save(ratingToUpdate);
        model.addAttribute("ratings", ratingRepository.findAll());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TO-DO: Find Rating by Id and delete the Rating, return to Rating list
        log.debug("deleteRating");
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingRepository.delete(rating);
        model.addAttribute("ratings", ratingRepository.findAll());
        return "redirect:/rating/list";
    }
}
