package com.projeto.desconto;

import static com.projeto.TaxaDesconto.QUATRO_PORCENTO;

public class CalculadoraDescontoPrimeiraFaixa extends CalculadoraFaixaDesconto{
    public CalculadoraDescontoPrimeiraFaixa(CalculadoraFaixaDesconto proximo) {
        super(proximo);
    }

    @Override
    protected double calcular(double valorTotalPedido) {
        if(isBetween(valorTotalPedido, 300, 800))
            return (valorTotalPedido * QUATRO_PORCENTO.getValor());
        return -1;
    }
}
