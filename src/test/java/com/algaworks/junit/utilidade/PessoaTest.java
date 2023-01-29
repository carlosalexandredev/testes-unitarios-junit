package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    /**
     * @Method assertAll(executable, executable, ...)
     * Este método permite agrupar várias afirmações juntas e serão executadas todas.
     * Se alguma afirmação falhar, ele lançará uma exceção AssertionFailedError com todas as falhas de afirmação.
     */
    @Test
    void assercaoAgrupada() {
        Pessoa pessoa = new Pessoa("Alex", "Silva");
        assertAll("Asserções de pessoa",
                () -> assertEquals("Alex", pessoa.getNome()),
                () -> assertEquals("Silva", pessoa.getSobrenome()));
    }

}