package com.ocr.poseidon.repositories;

import com.ocr.poseidon.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
