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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
    @GetMapping("/notes/{id}")
    public String patientNotes(@PathVariable("id") Integer id, Model model) {
        log.debug("ctrl patient notes list");
        PatientDTO patient = patientProxyService.getPatientById(id);
        List<NoteDTO> notes = noteProxyService.getNoteByPatientId(id);
        model.addAttribute("patient", patient);
        model.addAttribute("notes", notes);
        log.info("patients notes displayed");
        return "note/list";
    }

    /**
     * Endpoint to display Note add IHM
     *
     * @param model
     */
    @GetMapping("/note/add/{id}")
    public String addNote(@PathVariable("id") Integer id,Model model) {
        log.debug("addNote");
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(id);
        model.addAttribute("note", noteDTO);
        log.info("note get for add");
        return "note/add";
    }

    /**
     * Endpoint to validate for adding Note
     *
     * @param noteDTO Note object to be added
     * @param result     technical result
     * @param model
     */
    @PostMapping("/note/validate/{id}")
    public String validate(@PathVariable("id") Integer id, @Valid @ModelAttribute(value="note") NoteDTO noteDTO, BindingResult result, Model model) {
        log.debug("validate");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "note/add";
        }
        noteDTO.setPatientId(id);
        NoteDTO response =  noteProxyService.addNote(noteDTO);
        log.info("patient validated for add:" + response.toString());
        return "redirect:/notes/"+id;
    }

    /**
     * Endpoint to delete a note object
     * @param id is the note id
     * @param model
     */
    @GetMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable("id") String id, Model model) {
        log.debug("deleteNote :"+id);
        NoteDTO noteDTO = noteProxyService.getNoteByNoteId(id);
        noteProxyService.deleteNoteByNoteId(noteDTO.getNoteId());
        PatientDTO patient = patientProxyService.getPatientById(noteDTO.getPatientId());
        model.addAttribute("patient", patient);
        model.addAttribute("notes", noteProxyService.getNoteByPatientId(noteDTO.getPatientId()));
        log.info("note deleted");
        return "note/list";
    }

    /**
     * Endpoint to display note updating form
     * @param id noteId to be updated
     * @param model
     */
    @GetMapping("/note/update/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        log.debug("showUpdateForm");
        NoteDTO noteDTO = noteProxyService.getNoteByNoteId(id);
        model.addAttribute("note", noteDTO);
        log.info("note get for update");
        return "note/update";
    }

    /**
     * Endpoint to validate for update
     *
     * @param id       is the note id
     * @param noteDTO is the note object to be updated
     * @param result  technical result
     * @param model
     */
    @PostMapping("/note/update/{id}")
    public String updateNote(@PathVariable("id") String id, @Valid @ModelAttribute(value="note") NoteDTO noteDTO,
                                BindingResult result, Model model) {
        log.debug("updateNote");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "note/update";
        }
        NoteDTO note = noteProxyService.getNoteByNoteId(id);
        note.setTitle(noteDTO.getTitle());
        note.setNote(noteDTO.getNote());
        noteProxyService.updateNote(note);
        PatientDTO patient = patientProxyService.getPatientById(note.getPatientId());
        model.addAttribute("patient", patient);
        model.addAttribute("notes", noteProxyService.getNoteByPatientId(note.getPatientId()));
        log.info("note updated");
        return "redirect:/notes/"+note.getPatientId();
    }

}
