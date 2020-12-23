package com.ocr.p9front.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Model tests
 */
public class PatientTest {

    @Test
    void patientModelTest () {
        LocalDate birth = LocalDate.of(2000,1,15);
        LocalDateTime dtTest = LocalDateTime.now();
        PatientDTO patient = new PatientDTO();
        patient.setId(1);
        patient.setAddress("12 rue des oliviers");
        patient.setFamilly("TestFamille");
        patient.setGiven("Test");
        patient.setSex("M");
        patient.setBirthDate(birth);
        patient.setCreateDate(dtTest);
        patient.setUpdateDate(dtTest);
        assertTrue(patient != null);
        assertTrue(patient.getSex() == "M");
        assertTrue(patient.getAddress() == "12 rue des oliviers");
        assertTrue(patient.getFamilly() == "TestFamille");
        assertTrue(patient.getGiven() == "Test");
        assertTrue(patient.getId() != null);
        assertTrue(patient.getBirthDate() != null);
    }

    @Test
    void patientContructorTest () {
        LocalDate birth = LocalDate.of(2000,1,15);
        PatientDTO patient = new PatientDTO("Martin","Olivier","25 rue des pinsons","0102030405","M",birth);
        assertTrue(patient != null);
        assertTrue(patient.getSex() == "M");
        assertTrue(patient.getAddress() == "25 rue des pinsons");
        assertTrue(patient.getFamilly() == "Martin");
        assertTrue(patient.getGiven() == "Olivier");
        assertTrue(patient.getBirthDate() != null);
    }
}
