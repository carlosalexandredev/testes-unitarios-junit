package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTest {

    private static List<BigDecimal> getValoresInvalidos() {
        List<BigDecimal> valoresInvalidos = Arrays.asList(
                BigDecimal.ZERO, null, new BigDecimal(-100));
        return valoresInvalidos;
    }

    private void validaValorInvalido(ContaBancaria contaBancaria, BigDecimal valorInvalido, Consumer<BigDecimal> acao) {
        String mensagemEsperada = "Valor inválido";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> acao.accept(valorInvalido));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Nested
    class Deposito {
        @Test
        public void deveLancarExecessaoValidaDeposito() {
            ContaBancaria contaBancaria = new ContaBancaria(new BigDecimal(1000));
            List<BigDecimal> valoresInvalidos = getValoresInvalidos();

            for (BigDecimal valorInvalido : valoresInvalidos) {
                validaValorInvalido(contaBancaria, valorInvalido, contaBancaria::deposito);
            }
        }
    }

    @Nested
    class Saque {
        @Test
        public void validaSubtracaoSaque() {
            ContaBancaria contaBancaria = new ContaBancaria(new BigDecimal(1500));
            contaBancaria.saque(new BigDecimal(500));
            assertAll("Validação de Saque",
                    () -> assertEquals(new BigDecimal(1000), contaBancaria.saldo()));
        }

        @Test
        public void deveLancarExcessaoValidaValorSaque() {
            ContaBancaria contaBancaria = new ContaBancaria(new BigDecimal(1000));
            List<BigDecimal> valoresInvalidos = getValoresInvalidos();

            for (BigDecimal valorInvalido : valoresInvalidos) {
                validaValorInvalido(contaBancaria, valorInvalido, contaBancaria::saque);
            }
        }
    }

    @Nested
    class Saldo {
        @Test
        public void validaSaldo() {
            ContaBancaria contaBancariaZero = new ContaBancaria(BigDecimal.ZERO);
            ContaBancaria contaBancariaNegativo = new ContaBancaria(new BigDecimal(-500));
            ContaBancaria contaBancariaPositivo = new ContaBancaria(new BigDecimal(500));
            assertAll("Validação de saldo",
                    () -> assertEquals(BigDecimal.ZERO, contaBancariaZero.saldo()),
                    () -> assertEquals(new BigDecimal(-500), contaBancariaNegativo.saldo()),
                    () -> assertEquals(new BigDecimal(500), contaBancariaPositivo.saldo()));
        }

        @Test
        public void deveLacarExecessaoSaldoInsuficente() {
            ContaBancaria contaBancaria = new ContaBancaria(new BigDecimal(150));
            RuntimeException exception = assertThrows(RuntimeException.class,
                    () -> contaBancaria.saque(new BigDecimal(1000)));
            assertEquals("Saldo insuficiente", exception.getMessage());
        }

        @Test
        public void deveLancarExceptionSaldoNulo() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ContaBancaria(null));
            assertEquals("Valor do saldo não pode ser nulo", exception.getMessage());
        }
    }

}