package com.ocr.p9front;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application MedicalFrontApplication
 */

@SpringBootApplication
@EnableEncryptableProperties
public class MedicalFrontApplication {

    private static final Logger log = LogManager.getLogger(MedicalFrontApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(MedicalFrontApplication.class, args);
    }

}