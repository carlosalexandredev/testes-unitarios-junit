package com.algaworks.junit.utilidade;

import java.math.BigDecimal;
import java.util.Objects;

public class ContaBancaria {

    private final BigDecimal saldo;

    //TODO 1 - validar saldo: não pode ser nulo, caso seja, deve lançar uma IllegalArgumentException
    //TODO 2 - pode ser zero ou negativo
    public ContaBancaria(BigDecimal saldo) {
        if (Objects.isNull(saldo)) {
            throw new IllegalArgumentException("Valor do saldo não pode ser nulo");
        }
        this.saldo = saldo;
    }

    public void saque(BigDecimal valor) {
        //TODO 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
        //TODO 2 - Deve subtrair o valor do saldo
        //TODO 3 - Se o saldo for insuficiente deve lançar uma RuntimeException
    }

    public void deposito(BigDecimal valor) {
        //TODO 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
        //TODO 2 - Deve adicionar o valor ao saldo
    }

    //TODO 1 - retornar saldo
    public BigDecimal saldo() {
        return this.saldo;
    }
}
