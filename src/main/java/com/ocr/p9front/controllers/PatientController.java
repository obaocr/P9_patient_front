package com.ocr.p9front.controllers;

import com.ocr.p9front.repositories.RatingRepository;
import com.ocr.p9front.services.PatientProxyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class PatientController for Patients
 */

@Controller
public class PatientController {

    private static final Logger log = LogManager.getLogger(PatientController.class);

    @Autowired
    PatientProxyService patientProxyService;

    /**
     * Endpoint for Patients list
     * @return Patients list
     */
    @RequestMapping("/patient/list")
    public String home(Model model)
    {
        log.debug("home");
        model.addAttribute("patients", patientProxyService.getAllPatients());
        log.info("patients displayed");
        return "patient/list";
    }

}
