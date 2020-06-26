package com.ocr.poseidon.repositories;

import com.ocr.poseidon.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class for Repository BidList
 */

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {
}
