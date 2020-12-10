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
        // TODO verrue  à enlever ... rendre obligatoire la saisie de la date
        if (patient.getBirthDate() == null) {
            Date birth = new Date();
            patient.setBirthDate(birth);
        }
        return patientProxy.addPatient(patient);
    }

    public Boolean updatePatient(PatientDTO patient) {
        // TODO verrue  à enlever ... rendre obligatoire la saisie de la date
        if (patient.getBirthDate() == null) {
            Date birth = new Date();
            patient.setBirthDate(birth);
        }
        return patientProxy.updatePatient(patient.getId(), patient);
    }

}
