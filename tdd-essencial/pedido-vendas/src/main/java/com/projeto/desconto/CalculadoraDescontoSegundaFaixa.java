package com.projeto.desconto;

import static com.projeto.TaxaDesconto.SEIS_PORCENTO;

public class CalculadoraDescontoSegundaFaixa extends CalculadoraFaixaDesconto{
    public CalculadoraDescontoSegundaFaixa(CalculadoraFaixaDesconto proximo) {
        super(proximo);
    }

    @Override
    protected double calcular(double valorTotalPedido) {
        if (isBetween(valorTotalPedido, 800, 1000))
            return (valorTotalPedido * SEIS_PORCENTO.getValor());
        return -1;
    }
}
