package com.ocr.p9front.services;

import com.ocr.p9front.domain.NoteDTO;

import java.util.List;

public interface NoteProxyService {
    List<NoteDTO> getNoteByPatientId(Integer patientId);
    NoteDTO getNoteByNoteId(String noteId);
    Integer addNote(NoteDTO note);
    Boolean updateNote(NoteDTO note);
    Boolean deleteNoteByNoteId(String Id);
}
