package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SaudacaoUtilTest {

    /**
     * @Method assertEquals(expected, actual)
     * Verifica se o valor esperado é igual ao valor atual e lança uma exceção AssertionError se não for.
     * Podemos usar este método, por exemplo, para garantir que o resultado de uma operação matemática seja o esperado.
     */
    @Test
    public void saudarBomdia() {
        for (int hora : TipoSaudacao.BOM_DIA.getHoras()) {
            String saudacao = SaudacaoUtil.saudar(hora);
            assertEquals(TipoSaudacao.BOM_DIA.getDescricao(), saudacao, String.format("Saudacao incorreta | HORA: %s", hora));
        }
    }

    @Test
    public void saudarBoaTarde() {
        for (int hora : TipoSaudacao.BOA_TARDE.getHoras()) {
            String saudacao = SaudacaoUtil.saudar(hora);
            assertEquals("Boa tarde", saudacao, String.format("Saudacao incorreta | HORA: %s", hora));
        }
    }

    @Test
    public void saudarBoaNoite() {
        for (int hora : TipoSaudacao.BOA_NOITE.getHoras()) {
            String saudacao = SaudacaoUtil.saudar(hora);
            assertEquals("Boa noite", saudacao, String.format("Saudacao incorreta | HORA: %s", hora));
        }
    }

    @Test
    public void deveLancarException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> SaudacaoUtil.saudar(-10));
        assertEquals("Hora inválida", illegalArgumentException.getMessage());
    }

    /**
     * @Method assertDoesNotThrow é usado para garantir que um determinado bloco de código não lança uma exceção.
     * Ele é usado como um mecanismo de teste para garantir que o código esteja funcionando corretamente e não está gerando exceções inesperadas.
     */
    @Test
    public void naoDeveLancarException() {
        assertDoesNotThrow(() -> SaudacaoUtil.saudar(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10, 11})
    public void Dado_horario_matinal_quando_saudar_entao_deve_retornar_bom_dia(int hora) {
        String saudacao = SaudacaoUtil.saudar(hora);
        assertEquals(TipoSaudacao.BOM_DIA.getDescricao(), saudacao, String.format("Saudacao incorreta | HORA: %s", hora));
    }
}