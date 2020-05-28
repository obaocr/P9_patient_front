package com.ocr.poseidon;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class PoseidonApplication {

    // TODO FK pour les tables... ?
    // TODO annottaions Hibenate : https://docs.jboss.org/hibernate/validator/5.0/reference/en-US/html/chapter-bean-constraints.html#section-builtin-constraints
    // TODO Le mot de passe pour déchiffrer le mdp de la BDD devra être mis en dehors, par exemple dans une variable d' environnement
        // https://www.it-swarm.dev/fr/javascript/les-expressions-rationnelles-pour-mot-de-passe-doivent-contenir-au-moins-huit-caracteres-au-moins-un-chiffre-ainsi-que-des-lettres-majuscules-et-minuscules-et-des-caracteres-speciaux./1043325838/
    // TODO contole de surface pour saisie de numerique uniquement...
    // TODO celà génère un INSERT au lieu de update ....
    // TODO : message d'ereur en rouge !

    private static final Logger log = LogManager.getLogger(PoseidonApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(PoseidonApplication.class, args);
    }
}