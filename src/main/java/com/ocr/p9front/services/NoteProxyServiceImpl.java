package com.ocr.p9front.services;

import com.ocr.p9front.domain.NoteDTO;
import com.ocr.p9front.proxies.NoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO gérer les CTRL et les erreurs

@Service
public class NoteProxyServiceImpl implements NoteProxyService {

    @Autowired
    private NoteProxy noteProxy;

    public NoteProxyServiceImpl() {
    }

    public List<NoteDTO> getNoteByPatientId(Integer patientId) {
        return noteProxy.getNotesByPatientId(patientId);
    }

    public NoteDTO getNoteByNoteId(String noteId) {
        return noteProxy.getNoteByNoteId(noteId);
    }

    public Integer addNote(NoteDTO note) {
        return noteProxy.addNote(note);
    }

    public Boolean updateNote(NoteDTO note) {
        return noteProxy.updateNote(note);
    }

    public Boolean deleteNoteByNoteId(String NoteId) {
        return noteProxy.deleteNote(NoteId);
    }

}