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
    void TradeInstanceTest() {
        Trade trade = new Trade("Account","Type");
        trade.setBuyQuantity(1.0);
        trade.setTradeId(1);
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBenchmark("Benchmark");
        trade.setBook("Book");
        trade.setBuyPrice(10.2);
        trade.setDealName("DealName");
        trade.setRevisionName("Revision");
        trade.setSecurity("SSL");
        trade.setSellPrice(11.0);
        trade.setSellQuantity(50.21);
        trade.setSide("Side");
        trade.setSourceListId("Source");
        trade.setStatus("Close");
        trade.setTrader("Trader");
        assertTrue(trade.getAccount().equals("Account"));
        assertTrue(trade.getType().equals("Type"));
        assertTrue(trade.getBenchmark().equals("Benchmark"));
        assertTrue(trade.getBook().equals("Book"));
        assertTrue(trade.getBuyPrice().equals(10.2));
        assertTrue(trade.getDealName().equals("DealName"));
        assertTrue(trade.getRevisionName().equals("Revision"));
        assertTrue(trade.getSecurity().equals("SSL"));
        assertTrue(trade.getSellPrice().equals(11.0));
        assertTrue(trade.getSellQuantity().equals(50.21));
        assertTrue(trade.getSide().equals("Side"));
        assertTrue(trade.getSourceListId().equals("Source"));
        assertTrue(trade.getStatus().equals("Close"));
        assertTrue(trade.getTrader().equals("Trader"));
    }

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
