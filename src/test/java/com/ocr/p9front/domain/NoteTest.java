package com.ocr.p9front.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoteTest {

    @Test
    void noteModelTest() {
        LocalDateTime dtTest = LocalDateTime.now();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setNoteId("1");
        noteDTO.setPatientId(1);
        noteDTO.setTitle("Test title");
        noteDTO.setNote("Test note");
        noteDTO.setCreateDate(dtTest);
        noteDTO.setUpdateDate(dtTest);
        assertTrue(noteDTO != null);
        assertTrue(noteDTO.getNoteId() == "1");
        assertTrue(noteDTO.getPatientId() == 1);
        assertTrue(noteDTO.getTitle() == "Test title");
        assertTrue(noteDTO.getNote() == "Test note");
        assertTrue(noteDTO.getUpdateDate() != null);
        assertTrue(noteDTO.getCreateDate() != null);
    }

}
