package com.ocr.poseidon.repositories;

import com.ocr.poseidon.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class for Repository CurvePoint
 */

@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
