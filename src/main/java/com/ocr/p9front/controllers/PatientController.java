package com.ocr.p9front.controllers;

import com.ocr.p9front.domain.PatientDTO;
import com.ocr.p9front.services.PatientProxyService;
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
 * Class PatientController for Patients
 */

@Controller
public class PatientController {

    private static final Logger log = LogManager.getLogger(PatientController.class);

    @Autowired
    PatientProxyService patientProxyService;

    /**
     * Endpoint for Patients list
     *
     * @return Patients list
     */
    @RequestMapping("/patient/list")
    public String home(Model model) {
        log.debug("home");
        model.addAttribute("patients", patientProxyService.getAllPatients());
        log.info("patients displayed");
        return "patient/list";
    }

    /**
     * Endpoint to display Patient add IHM
     *
     * @param model
     */
    @GetMapping("/patient/add")
    public String addPatient(Model model) {
        log.debug("addPatient");
        PatientDTO patientDTO = new PatientDTO();
        model.addAttribute("patient", patientDTO);
        log.info("patient get for add");
        return "patient/add";
    }

    /**
     * Endpoint to validate for adding Patient
     *
     * @param patientDTO Patient object to be added
     * @param result     technical result
     * @param model
     */
    @PostMapping("/patient/validate")
    public String validate(@Valid PatientDTO patientDTO, BindingResult result, Model model) {
        log.debug("validate");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "patient/add";
        }
        patientProxyService.addPatient(patientDTO);
        log.info("patient validated for add");
        return "redirect:/patient/list";
    }

    /**
     * Endpoint to display patient updating form
     *
     * @param id    patient id to be updated
     * @param model
     */
    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.debug("showUpdateForm");
        PatientDTO patientDTO = patientProxyService.getPatientById(id);
        model.addAttribute("patient", patientDTO);
        log.info("patient get for update");
        return "patient/update";
    }

    /**
     * Endpoint to validate for update
     *
     * @param id         is the patient id
     * @param patientDTO is the patient object to be updated
     * @param result     technical result
     * @param model
     */
    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") Integer id, @Valid PatientDTO patientDTO,
                                BindingResult result, Model model) {
        log.debug("updatePatient");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "patient/update";
        }
        PatientDTO patient = patientProxyService.getPatientById(id);
        patient.setFamilly(patientDTO.getFamilly());
        patient.setGiven(patientDTO.getGiven());
        patient.setAddress(patientDTO.getAddress());
        patient.setPhone(patientDTO.getPhone());
        patient.setBirthDate(patientDTO.getBirthDate());
        patient.setSex(patientDTO.getSex());
        patientProxyService.updatePatient(patient);
        model.addAttribute("patients", patientProxyService.getAllPatients());
        log.info("patient updated");
        return "redirect:/patient/list";
    }

    /**
     * Endpoint to delete a patient object
     * @param id is the patient id
     * @param model
     */
    @GetMapping("/patient/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        log.debug("deleteRating");
        PatientDTO patientDTO = patientProxyService.getPatientById(id);
        patientProxyService.deletePatientById(patientDTO.getId());
        model.addAttribute("patients", patientProxyService.getAllPatients());
        log.info("patient deleted");
        return "redirect:/patient/list";
    }

}
