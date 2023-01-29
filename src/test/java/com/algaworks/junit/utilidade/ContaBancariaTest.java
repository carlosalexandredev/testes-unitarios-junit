package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTest {

    @Test
    public void validaSaldo() {
        ContaBancaria contaBancariaZero = new ContaBancaria(BigDecimal.ZERO);
        ContaBancaria contaBancariaNegativo = new ContaBancaria(new BigDecimal("-500"));
        ContaBancaria contaBancariaPositivo = new ContaBancaria(new BigDecimal("500"));

        assertAll("Validação de saldo",
                () -> assertEquals(BigDecimal.ZERO, contaBancariaZero.saldo()),
                () -> assertEquals(new BigDecimal("-500"), contaBancariaNegativo.saldo()),
                () -> assertEquals(new BigDecimal("500"), contaBancariaPositivo.saldo()));
    }

    @Test
    public void deveLancarException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new ContaBancaria(null));
        assertEquals("Valor do saldo não pode ser nulo", illegalArgumentException.getMessage());
    }

}