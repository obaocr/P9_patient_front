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

@Controller
public class CurveController {

    private static final Logger log = LogManager.getLogger(CurveController.class);

    @Autowired
    CurvePointRepository curvePointRepository;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TO-DO: find all Curve Point, add to model
        log.debug("home");
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    // !!!! j'ai modifie et mis (Model model) avant il y avait (CurvePoint bid) :
    public String addBidForm(Model model)
    {
        log.debug("add curvePoint Form");
        CurvePoint curvePoint = new CurvePoint();
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TO-DO: check data valid and save to db, after saving return Curve list
        log.debug("validate");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "curvePoint/add";
        }
        curvePointRepository.save(curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TO-DO: get CurvePoint by Id and to model then show to the form
        log.debug("showUpdateForm");
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // TO-DO: check required fields, if valid call service to update Curve and return Curve list
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
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        // TO-DO: Find Curve by Id and delete the Curve, return to Curve list
        log.debug("curvePoint");
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
        curvePointRepository.delete(curvePoint);
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        return "redirect:/curvePoint/list";
    }
}
