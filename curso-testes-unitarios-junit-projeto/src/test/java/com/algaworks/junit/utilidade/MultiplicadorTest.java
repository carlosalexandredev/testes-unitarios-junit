package com.algaworks.junit.utilidade;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicadorTest {

    @ParameterizedTest
    @EnumSource(value = Multiplicador.class, names = {"DOBRO", "TRIPLO"})
    public void aplicarMultiplicador(Multiplicador multiplicador) {
        assertNotNull(multiplicador.aplicarMultiplicador(10.0));
    }

    @ParameterizedTest
    @EnumSource(value = Multiplicador.class)
    public void aplicarMultiplicadorTodos(Multiplicador multiplicador) {
        assertNotNull(multiplicador.aplicarMultiplicador(10.0));
    }
}