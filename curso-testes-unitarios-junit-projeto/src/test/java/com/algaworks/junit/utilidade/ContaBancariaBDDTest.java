package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Conta Bancária")
class ContaBancariaBDDTest {

    @Nested
    @DisplayName("Dado uma conta bancária com um saldo de R$ 10,00")
    class ContaBancariaComSaldo {
        private ContaBancaria contaBancaria;

        @BeforeEach
        void beforeEach() {
            contaBancaria = new ContaBancaria(BigDecimal.TEN);
        }

        @Nested
        @DisplayName("Quando efetuar o saque com valor menor")
        class SaqueValorMenor {
            private final BigDecimal valorSaque = new BigDecimal("9.0");

            @Test
            @DisplayName("Então não deve lançar excessão")
            void deveLancarSaqueSemExcessao() {
                assertDoesNotThrow(() -> contaBancaria.saque(valorSaque));
            }

            @Test
            @DisplayName("E deve subtrair do saldo")
            void deveSubtrairDoSaldo() {
                contaBancaria.saque(valorSaque);

                assertEquals(new BigDecimal("1.0"), contaBancaria.saldo());
            }
        }

        @Nested
        @DisplayName("Quando efetuar o saque com valor maior")
        class SaqueComValorMaior {
            private final BigDecimal valorSaque = new BigDecimal("20.0");

            @Test
            @DisplayName("Então deve lançar excessão")
            void deveFalhar() {
                assertThrows(RuntimeException.class, () -> contaBancaria.saque(valorSaque));
            }

            @Test
            @DisplayName("Não deve alterar saldo ao tentar saque inválido")
            void naoDeveAlterarSaldo() {
                try {
                    contaBancaria.saque(valorSaque);
                } catch (Exception e) {
                }
                assertEquals(BigDecimal.TEN, contaBancaria.saldo());
            }
        }


    }

    @Nested
    @DisplayName("Dado uma conta bancária com um saldo de R$ 00,00")
    class ContaBancariaComSaldoZerado {
        private ContaBancaria contaBancaria;

        @BeforeEach
        void beforeEach() {
            contaBancaria = new ContaBancaria(BigDecimal.ZERO);
        }

        @Nested
        @DisplayName("Quando efetuar o saque com valor menor")
        class SaqueValorMenor {
            private final BigDecimal valorSaque = new BigDecimal("1.0");

            @Test
            @DisplayName("Então não deve lançar excessão")
            void deveLancarSaqueSemExcessao() {
                assertThrows(RuntimeException.class, () -> contaBancaria.saque(valorSaque));
            }
        }

        @Nested
        @DisplayName("Quando efetuar o saque com valor maior")
        class SaqueComValorMaior {
            private final BigDecimal valorSaque = new BigDecimal("20.0");

            @Test
            @DisplayName("Então deve lançar excessão")
            void deveFalhar() {
                assertThrows(RuntimeException.class, () -> contaBancaria.saque(valorSaque));
            }
        }
    }

    @Nested
    @DisplayName("Quando efetuar um deposito de R$ 8,00")
    class DepositoComOitoReais {

        private ContaBancaria contaBancaria;
        private final BigDecimal valorDeposito = new BigDecimal("8.00");

        @BeforeEach
        void beforeEach() {
            contaBancaria = new ContaBancaria(BigDecimal.ZERO);
        }

        @Test
        @DisplayName("Então deve domar ao saldo")
        void deveSomarAoSaldo() {
            contaBancaria.deposito(valorDeposito);

            assertEquals(new BigDecimal("8.00"), contaBancaria.saldo());
        }
    }


}