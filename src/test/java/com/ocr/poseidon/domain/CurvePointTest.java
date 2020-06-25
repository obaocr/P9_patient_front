package com.ocr.poseidon.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CurvePointTest {

    @Test
    void survePointShouldInstanciateCurvePoint() throws ParseException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = now.format(format);
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setAsOfDate(now);
        curvePoint.setCreationDate(now);
        curvePoint.setCurveId(1);
        curvePoint.setTerm(1.0);
        curvePoint.setValue(1.0);
        assertTrue(curvePoint.getCurveId() == 1);
        assertTrue(curvePoint.getTerm() == 1.0);
        assertTrue(curvePoint.getValue() == 1.0);
        assertTrue(curvePoint.getAsOfDate() == now);
        assertTrue(curvePoint.getCreationDate() == now);
    }

    @Test
    void curvePointConstructor() {
        CurvePoint curvePoint = new CurvePoint(1,1.0,1.0);
        assertTrue(curvePoint.getCurveId() == 1);
        assertTrue(curvePoint.getTerm() == 1.0);
        assertTrue(curvePoint.getValue() == 1.0);
    }

    @Test
    void constraintTermShouldReturnKO () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(123456.0);
        Set<ConstraintViolation<CurvePoint>> constraintViolations = validator.validate( curvePoint );
        assertTrue(constraintViolations.size() == 1);
        assertTrue(constraintViolations.iterator().next().getMessage() != null);
    }

    @Test
    void constraintValueShouldReturnKO () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setValue(123456.0);
        Set<ConstraintViolation<CurvePoint>> constraintViolations = validator.validate( curvePoint );
        assertTrue(constraintViolations.size() == 1);
        assertTrue(constraintViolations.iterator().next().getMessage() != null);
    }
}
