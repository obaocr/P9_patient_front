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

@EnableFeignClients("com.ocr.p9front")
@SpringBootApplication
@EnableEncryptableProperties
public class MedicalFrontApplication {

    private static final Logger log = LogManager.getLogger(MedicalFrontApplication.class);

    public static void main(String[] args) {
        log.info("MedicalFrontApplication");
        SpringApplication.run(MedicalFrontApplication.class, args);
    }
}