package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.CurvePoint;
import com.ocr.poseidon.repositories.CurvePointRepository;
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
 * Class CurveController for CurvePoint
 */

@Controller
public class CurveController {

    private static final Logger log = LogManager.getLogger(CurveController.class);

    @Autowired
    CurvePointRepository curvePointRepository;

    /**
     * Endpoint for curvePoint list
     * @param model
     * @return curvePoint list
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        log.debug("home");
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        log.info("curvePoint displayed");
        return "curvePoint/list";
    }

    /**
     * Endpoint to display CurvePoint add IHM
     * @param model
     */
    @GetMapping("/curvePoint/add")
    public String addBidForm(Model model)
    {
        log.debug("add curvePoint Form");
        CurvePoint curvePoint = new CurvePoint();
        model.addAttribute("curvePoint", curvePoint);
        log.info("curvePoint get for add");
        return "curvePoint/add";
    }

    /**
     * Endpoint to validate for adding CurvePoint
     * @param curvePoint CurvePoint object to be added
     * @param result technical result
     * @param model
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        log.debug("validate");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "curvePoint/add";
        }
        curvePointRepository.save(curvePoint);
        log.info("curvePoint validated");
        return "redirect:/curvePoint/list";
    }

    /**
     * Endpoint to display CurvePoint updating form
     * @param id CurvePoint id to be updated
     * @param model
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.debug("showUpdateForm");
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        log.info("curvePoint get for update");
        return "curvePoint/update";
    }

    /**
     * Endpoint to validate for update
     * @param id is the CurvePoint id
     * @param curvePoint is the CurvePoint object to be updated
     * @param result technical result
     * @param model
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        log.debug("update curvePoint");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "curvePoint/update";
        }
        CurvePoint curvePointToUpdate = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
        curvePointToUpdate.setCurveId(curvePoint.getCurveId());
        curvePointToUpdate.setValue(curvePoint.getValue());
        curvePointToUpdate.setTerm(curvePoint.getTerm());
        curvePointRepository.save(curvePointToUpdate);
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        log.info("curvePoint updated");
        return "redirect:/curvePoint/list";
    }

    /**
     * Endpoint to delete a CurvePoint object
     * @param id is the CurvePoint id
     * @param model
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        log.debug("curvePoint");
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
        curvePointRepository.delete(curvePoint);
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        log.info("curvePoint deleted");
        return "redirect:/curvePoint/list";
    }
}
