package com.ocr.poseidon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PoseidonApplication {

    // TODO FK pour les tables... ?
    // TODO securiser le mot de passe de la BDD ? https://medium.com/@surabhijaiswal/using-jasypt-with-spring-boot-9cfabfa747e4 ...

    private static final Logger log = LogManager.getLogger(PoseidonApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(PoseidonApplication.class, args);
    }
}