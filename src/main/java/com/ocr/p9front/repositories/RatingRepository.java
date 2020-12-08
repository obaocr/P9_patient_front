package com.ocr.p9front.repositories;

import com.ocr.p9front.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class for Repository Rating
 */

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
