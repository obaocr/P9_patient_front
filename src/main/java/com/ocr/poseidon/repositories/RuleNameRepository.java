package com.ocr.poseidon.repositories;

import com.ocr.poseidon.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class for Repository RuleName
 */

@Repository
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
