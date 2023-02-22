package com.projeto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Conversor CamelCase")
public class CamelCaseConverterTest {

    private CamelCaseConverter camelCase;

    @BeforeEach
    void init(){
        camelCase = new CamelCaseConverter();
    }

    @Test
    @DisplayName("Cria objeto CamelCase")
    void deveCriarObjetoCamelCaseCanverter() throws Exception {
    }

    @Test
    @DisplayName("Aplica CamelCase em nome inicial")
    void aplicarCamelCaseEmNomeInico() throws Exception {
        assertEquals("Lionel", camelCase.converter("lionel"));
    }

    @Test
    @DisplayName("Converte nome simples misturado maiusculo e minusculo")
    void deveConverterNomeSimplesMisturadoMaiusculoMinusculo(){
        assertEquals("Lionel", camelCase.converter("LIOnel"));
    }
}