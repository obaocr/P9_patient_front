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

    // MDP Test !: 123456
    // anotations Hibernate : https://docs.jboss.org/hibernate/validator/5.0/reference/en-US/html/chapter-bean-constraints.html#section-builtin-constraints
    // https://www.it-swarm.dev/fr/javascript/les-expressions-rationnelles-pour-mot-de-passe-doivent-contenir-au-moins-huit-caracteres-au-moins-un-chiffre-ainsi-que-des-lettres-majuscules-et-minuscules-et-des-caracteres-speciaux./1043325838/
    private static final Logger log = LogManager.getLogger(PoseidonApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PoseidonApplication.class, args);
    }
}