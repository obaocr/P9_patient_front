package com.ocr.poseidon;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application PoseidonApplication
 */

@SpringBootApplication
@EnableEncryptableProperties
public class PoseidonApplication {

    private static final Logger log = LogManager.getLogger(PoseidonApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PoseidonApplication.class, args);
    }
}