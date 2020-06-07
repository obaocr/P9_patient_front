package com.ocr.poseidon.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RuleNameTest {

    @Test
    void RuleNameConstructorTest () {
        RuleName ruleName = new RuleName("name","description","json","template","sqlStr","sqlPart");
        assertTrue(ruleName.getName().equals("name"));
        assertTrue(ruleName.getDescription().equals("description"));
        assertTrue(ruleName.getJson().equals("json"));
        assertTrue(ruleName.getTemplate().equals("template"));
        assertTrue(ruleName.getSqlStr().equals("sqlStr"));
        assertTrue(ruleName.getSqlPart().equals("sqlPart"));
    }
}
