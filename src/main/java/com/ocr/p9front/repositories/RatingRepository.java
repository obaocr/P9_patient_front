package com.ocr.medicalcare.repositories;

import com.ocr.medicalcare.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class for Repository Rating
 */

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
