package com.ocr.p9front.services;

import com.ocr.p9front.domain.PatientDTO;
import com.ocr.p9front.proxies.PatientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PatientProxyServiceImpl implements  PatientProxyService {

    private Logger logger = LoggerFactory.getLogger(PatientProxyServiceImpl.class);

    @Autowired
    private PatientProxy patientProxy;

    public PatientProxyServiceImpl() {
    }

    public List<PatientDTO> getAllPatients() {

        return patientProxy.getPatients();
    };

    public PatientDTO getPatientById(Integer Id) {

        return patientProxy.getPatientById(Id);
    }

    public Integer addPatient(PatientDTO patient) {
        if (patient.getBirthDate() == null) {
            Date birth = new Date();
            patient.setBirthDate(birth);
        }
        patient.setGiven(patient.getGiven().substring(0,1).toUpperCase() + patient.getGiven().substring(1).toLowerCase());
        patient.setFamilly(patient.getFamilly().toUpperCase());
        patient.setAddress(patient.getAddress().toUpperCase());
        patient.setSex(patient.getSex().toUpperCase());
        return patientProxy.addPatient(patient);
    }

    public Boolean updatePatient(PatientDTO patient) {
        if (patient.getBirthDate() == null) {
            Date birth = new Date();
            patient.setBirthDate(birth);
        }
        patient.setGiven(patient.getGiven().substring(0,1).toUpperCase() + patient.getGiven().substring(1).toLowerCase());
        patient.setFamilly(patient.getFamilly().toUpperCase());
        patient.setAddress(patient.getAddress().toUpperCase());
        patient.setSex(patient.getSex().toUpperCase());
        return patientProxy.updatePatient(patient.getId(), patient);
    }

    public Boolean deletePatientById(Integer Id) {
        return patientProxy.deletePatient(Id);
    }

}
