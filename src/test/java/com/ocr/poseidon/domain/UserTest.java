package com.ocr.poseidon.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    @Test
    void TradeTestPasswordMandatoryShouldReturnKO () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        user.setUsername("admin");
        user.setFullname("Administrator...");
        user.setRole("Administrator");
        user.setPassword("");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
        assertTrue(constraintViolations.size() == 2);
     }

    @Test
    void TradeTestPasswordShouldReturnOK () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        user.setUsername("user1");
        user.setFullname("utilisateur1");
        user.setRole("User");
        user.setPassword("Parach1!");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
        assertTrue(constraintViolations.size() == 0);
    }

    @Test
    void TradeTestPasswordShouldReturnKO () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        user.setUsername("admin");
        user.setFullname("Administrator...");
        user.setRole("Administrator");
        user.setPassword("simple password");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
        assertTrue(constraintViolations.size() == 1);
        assertTrue(constraintViolations.iterator().next().getMessage().equals("Password must be >= 8 characters, 1 UpperCase, 1 symbol, 1 digit"));
    }

    @Test
    void TradeTestUsernameShouldReturnKO () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        user.setUsername("");
        user.setFullname("Administrator...");
        user.setRole("Administrator");
        user.setPassword("Pass123456!");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
        assertTrue(constraintViolations.size() == 1);
        assertTrue(constraintViolations.iterator().next().getMessage().equals("Username is mandatory"));
    }

    @Test
    void TradeTestRoleShouldReturnKO () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        user.setUsername("Admin");
        user.setFullname("Administrator...");
        user.setRole("");
        user.setPassword("Pass123456!");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
        assertTrue(constraintViolations.size() == 1);
        assertTrue(constraintViolations.iterator().next().getMessage().equals("Role is mandatory"));
    }
}
