package com.ocr.poseidon.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TradeTest {

    @Test
    void TradeConstructorTest () {
        Trade trade = new Trade("Account","Type");
        assertTrue(trade.getAccount().equals("Account"));
        assertTrue(trade.getType().equals("Type"));
    }

    @Test
    void TradeTestAccountShouldReturnKO () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Trade trade = new Trade();
        trade.setAccount("");
        trade.setType("Type");
        Set<ConstraintViolation<Trade>> constraintViolations = validator.validate( trade );
        assertTrue(constraintViolations.size() == 1);
        assertTrue(constraintViolations.iterator().next().getMessage().equals("Please enter an account"));
    }

    @Test
    void TradeTestTypeShouldReturnKO () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Trade trade = new Trade();
        trade.setAccount("Account");
        trade.setType("");
        Set<ConstraintViolation<Trade>> constraintViolations = validator.validate( trade );
        assertTrue(constraintViolations.size() == 1);
        assertTrue(constraintViolations.iterator().next().getMessage().equals("Please enter a type"));
    }


}
