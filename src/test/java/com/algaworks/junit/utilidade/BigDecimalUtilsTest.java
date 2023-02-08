package com.algaworks.junit.utilidade;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BigDecimalUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "10.00, 10",
            "15.25, 15",
            "25, 50.00",
            "9.00,9.00",
            "15.25, 15.25"
    })
    public void iguais(BigDecimal a, BigDecimal b) {
        assertTrue(BigDecimalUtils.iguais(a, b));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/numeros.csv")
    public void diferentes(BigDecimal a, BigDecimal b) {
        assertFalse(BigDecimalUtils.iguais(a, b));
    }
}