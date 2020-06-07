package com.ocr.poseidon.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RatingTest {

    @Test
    void ratingContructorTest () {
        Rating rating = new Rating("Moody","Sand","Fitch",1);
        assertTrue(rating.getFitchRating().equals("Fitch"));
        assertTrue(rating.getMoodysRating().equals("Moody"));
        assertTrue(rating.getSandPRating().equals("Sand"));
        assertTrue(rating.getOrderNumber() == 1);
    }

}
