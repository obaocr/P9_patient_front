package com.ocr.poseidon.domain;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BidListTest {

    @Test
    void bidListConstructor()  {
        BidList bidList = new BidList("Account","Type",1.0);
        assertTrue(bidList.getType().equals("Type"));
        assertTrue(bidList.getAccount().equals("Account"));
        assertTrue(bidList.getBidQuantity() == 1.0);
    }

    @Test
    void bidListShouldInstanciateBidlist() throws ParseException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = now.format(format);
        BidList bidList = new BidList();
        bidList.setBid(1.0);
        bidList.setBidQuantity(1.0);
        bidList.setType("Type");
        bidList.setAccount("Account");
        bidList.setAsk(1.0);
        bidList.setAskQuantity(1.0);
        bidList.setBenchmark("Benchmark");
        bidList.setBidListDate(now);
        bidList.setBook("Book");
        bidList.setCommentary("Commentary");
        bidList.setCreationDate(now);
        bidList.setCreationName("CreationName");
        bidList.setDealName("DealName");
        bidList.setDealType("DealType");
        bidList.setRevisionDate(now);
        bidList.setRevisionName("evisionName");
        bidList.setSecurity("High");
        bidList.setSide("Side");
        bidList.setSourceListId("");
        bidList.setStatus("Open");
        bidList.setTrader("John");
        assertTrue(bidList.getType().equals("Type"));
        assertTrue(bidList.getBidListDate() == now);
    }


    @Test
    void constraintTypeNotNull () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        BidList bidList = new BidList();
        bidList.setType("");
        bidList.setAccount("Account");
        Set<ConstraintViolation<BidList>> constraintViolations = validator.validate( bidList );
        assertTrue(constraintViolations.size() == 1);
        assertTrue(constraintViolations.iterator().next().getMessage().equals("Please enter an type"));
    }

    @Test
    void constraintAccountNotNull () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        BidList bidList = new BidList();
        bidList.setType("Type");
        bidList.setAccount("");
        Set<ConstraintViolation<BidList>> constraintViolations = validator.validate( bidList );
        assertTrue(constraintViolations.size() == 1);
        assertTrue(constraintViolations.iterator().next().getMessage().equals("Please enter an account"));
    }

    @Test
    void constraintbidQuantityShouldReturnKO () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        BidList bidList = new BidList();
        bidList.setType("Type");
        bidList.setAccount("Account");
        bidList.setBidQuantity(123456.0);
        Set<ConstraintViolation<BidList>> constraintViolations = validator.validate( bidList );
        assertTrue(constraintViolations.size() == 1);
        assertTrue(constraintViolations.iterator().next().getMessage() != null);
    }

    @Test
    void constraintbidQuantityShouldReturnOK () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        BidList bidList = new BidList();
        bidList.setType("Type");
        bidList.setAccount("Account");
        bidList.setBidQuantity(12345.0);
        Set<ConstraintViolation<BidList>> constraintViolations = validator.validate( bidList );
        assertTrue(constraintViolations.size() == 0);
    }


}
