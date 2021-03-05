package com.ocr.p9front.services;

import com.ocr.p9front.domain.PatientDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration Test Class
 */
@SpringBootTest
class PatientIntgTest {


    @Autowired
    private PatientProxyService patientProxyService;

    @Test
    @Disabled
    void getPatientsShouldReturnAllPatients() {
        List<PatientDTO> patientDTOList = patientProxyService.getAllPatients();
        System.out.println("Nb patientDTOList :" + patientDTOList.size());
        assertTrue(patientDTOList.size() > 0);
    }

}
