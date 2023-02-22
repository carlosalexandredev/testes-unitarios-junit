package com.algaworks.junit.utilidade;

import java.math.BigDecimal;
import java.util.Objects;

public class ContaBancaria {

    private BigDecimal saldo;

    /**
     * 1 - validar saldo: não pode ser nulo, caso seja, deve lançar uma IllegalArgumentException
     * 2 - pode ser zero ou negativo
     */
    public ContaBancaria(BigDecimal saldo) {
        if (Objects.isNull(saldo)) {
            throw new IllegalArgumentException("Valor do saldo não pode ser nulo");
        }
        this.saldo = saldo;
    }

    /**
     * 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
     * 2 - Deve subtrair o valor do saldo
     * 3 - Se o saldo for insuficiente deve lançar uma RuntimeException
     */
    public void saque(BigDecimal valor) {
        if (validaValor(valor)) {
            subtraiSaldo(valor);
        }
    }

    /**
     * 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
     * 2 - Deve adicionar o valor ao saldo
     */
    public void deposito(BigDecimal valor) {
        if (validaValor(valor)) {
            SomaSaldo(valor);
        }

    }

    /**
     * 1 - retornar saldo
     */
    public BigDecimal saldo() {
        return this.saldo;
    }

    private void SomaSaldo(BigDecimal valor) {
        this.saldo = saldo.add(valor);
    }

    private void subtraiSaldo(BigDecimal valor) {
        if (saldo.compareTo(valor) == -1) {
            throw new RuntimeException("Saldo insuficiente");
        }
        this.saldo = saldo.subtract(valor);
    }

    /**
     * 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
     */
    private boolean validaValor(BigDecimal valor) {
        if (Objects.isNull(valor) || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        return true;
    }
}
