package com.ocr.p9front.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test Class for PatientRisk model
 */
public class PatientRiskDTOTest {

    @Test
    void testPatientRiskDTO() {
        PatientRiskDTO patientRiskDTO = new PatientRiskDTO();
        patientRiskDTO.setPatientId(1);
        patientRiskDTO.setPatientInfo("test info");
        patientRiskDTO.setRisk("None");
        patientRiskDTO.setNbrisk(1);
        assertTrue(patientRiskDTO.getPatientId() == 1);
        assertTrue(patientRiskDTO.getNbrisk() == 1);
        assertTrue(patientRiskDTO.getPatientInfo().equals("test info"));
        assertTrue(patientRiskDTO.getRisk().equals("None"));
    }
}
