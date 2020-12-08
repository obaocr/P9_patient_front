package com.ocr.p9front.controllers;

import com.ocr.p9front.domain.Rating;
import com.ocr.p9front.repositories.RatingRepository;
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

    /**
     * Endpoint for rating list
     * @param model
     * @return rating list
     */
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        log.debug("home");
        model.addAttribute("ratings", ratingRepository.findAll());
        log.info("ratings displayed");
        return "rating/list";
    }

    /**
     * Endpoint to display Rating add IHM
     * @param model
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
        log.debug("addRatingForm");
        Rating rating = new Rating();
        model.addAttribute("rating", rating);
        log.info("rating get for add");
        return "rating/add";
    }

    /**
     * Endpoint to validate for adding Rating
     * @param rating Rating object to be added
     * @param result technical result
     * @param model
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        log.debug("validate");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "rating/add";
        }
        ratingRepository.save(rating);
        log.info("rating validated for add");
        return "redirect:/rating/list";
    }

    /**
     * Endpoint to display Rating updating form
     * @param id Rating id to be updated
     * @param model
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.debug("showUpdateForm");
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        log.info("rating get for update");
        return "rating/update";
    }

    /**
     * Endpoint to validate for update
     * @param id is the Rating id
     * @param rating is the Rating object to be updated
     * @param result technical result
     * @param model
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
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
        log.info("rating updated");
        return "redirect:/rating/list";
    }

    /**
     * Endpoint to delete a Rating object
     * @param id is the Rating id
     * @param model
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TO-DO: Find Rating by Id and delete the Rating, return to Rating list
        log.debug("deleteRating");
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingRepository.delete(rating);
        model.addAttribute("ratings", ratingRepository.findAll());
        log.info("rating deleted");
        return "redirect:/rating/list";
    }
}
