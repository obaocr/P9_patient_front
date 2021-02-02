package com.ocr.p9front;

import com.ocr.p9front.domain.PatientDTO;
import com.ocr.p9front.proxies.PatientProxy;
import com.ocr.p9front.services.PatientProxyService;
import com.ocr.p9front.services.PatientProxyServiceImpl;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Arrays;
import java.util.List;

/**
 * Application MedicalFrontApplication
 */

// TODO erreur liste des notes d'1 patient si pas de notes
// TODO bouton cancel note / revenir à la liste note d'un patient
// TODO / note / lien vers liste d'un patient pour revenir
// TODO / saisie note / tester la saisie de saut de ligne

@EnableFeignClients("com.ocr.p9front")
@SpringBootApplication
@EnableEncryptableProperties
public class MedicalFrontApplication {

    private static final Logger log = LogManager.getLogger(MedicalFrontApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MedicalFrontApplication.class, args);
    }
}