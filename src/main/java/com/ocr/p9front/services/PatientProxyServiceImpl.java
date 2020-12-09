package com.ocr.p9front.services;

import com.ocr.p9front.domain.PatientDTO;
import com.ocr.p9front.proxies.PatientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
