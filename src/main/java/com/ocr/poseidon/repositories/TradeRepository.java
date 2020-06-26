package com.ocr.poseidon.repositories;

import com.ocr.poseidon.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class for Repository Trade
 */

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
