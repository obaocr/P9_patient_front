package com.ocr.poseidon.repositories;

import com.ocr.poseidon.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
