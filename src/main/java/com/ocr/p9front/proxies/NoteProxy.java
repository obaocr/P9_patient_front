package com.ocr.p9front.proxies;

import com.ocr.p9front.domain.NoteDTO;
import com.ocr.p9front.domain.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO getNotesByPatientId : voir pour route notes uniquement

@FeignClient(name = "microservice-note", url = "http://localhost:8049")
public interface NoteProxy {

    @RequestMapping(method = RequestMethod.GET, value = "/notes/{Id}")
    NoteDTO getNoteByNoteId(@PathVariable("Id") String Id);

    @RequestMapping(method = RequestMethod.GET, value = "/notes/patient/{Id}")
    List<NoteDTO> getNotesByPatientId(@PathVariable("Id") Integer Id);

    @RequestMapping(method = RequestMethod.POST, value = "/notes")
    NoteDTO addNote(@RequestBody NoteDTO note);

    @RequestMapping(method = RequestMethod.PUT, value = "/notes")
    Boolean updateNote(@RequestBody NoteDTO note);

    @RequestMapping(method = RequestMethod.DELETE, value = "/notes/{Id}")
    Boolean deleteNote(@PathVariable String Id);
}


