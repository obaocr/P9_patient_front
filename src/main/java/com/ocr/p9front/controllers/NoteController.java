package com.ocr.p9front.controllers;

import com.ocr.p9front.domain.NoteDTO;
import com.ocr.p9front.domain.PatientDTO;
import com.ocr.p9front.services.NoteProxyService;
import com.ocr.p9front.services.PatientProxyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Class NoteController for Patients Notes
 */

@Controller
public class NoteController {

    private static final Logger log = LogManager.getLogger(PatientController.class);

    @Autowired
    PatientProxyService patientProxyService;

    @Autowired
    NoteProxyService noteProxyService;

    /**
     * Endpoint for Patients Notes list
     *
     * @return Patients Notes list
     */
    @RequestMapping("/notes/{id}")
    public String patientNotes(@PathVariable("id") Integer id, Model model) {
        log.debug("ctrl patient notes list");
        PatientDTO patientDTO = patientProxyService.getPatientById(id);
        List<NoteDTO> notes = noteProxyService.getNoteByPatientId(id);
        model.addAttribute("patient", patientDTO);
        model.addAttribute("notes", notes);
        log.info("patients notes displayed");
        return "/notes";
    }

}
