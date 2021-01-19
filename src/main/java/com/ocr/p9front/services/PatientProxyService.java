package com.ocr.p9front.services;

import com.ocr.p9front.domain.PatientDTO;

import java.util.List;

public interface PatientProxyService {
    List<PatientDTO> getAllPatients();
    Integer addPatient(PatientDTO patient);
    Boolean updatePatient(PatientDTO patient);
    PatientDTO getPatientById(Integer Id);
    Boolean deletePatientById(Integer Id);
}
