package com.projeto.desconto;

import static com.projeto.TaxaDesconto.OITO_PORCENTO;
import static java.lang.Integer.MAX_VALUE;

public class CalculadoraDescontoTerceiraFaixa extends CalculadoraFaixaDesconto{
    public CalculadoraDescontoTerceiraFaixa(CalculadoraFaixaDesconto proximo) {
        super(proximo);
    }

    @Override
    protected double calcular(double valorTotalPedido) {
        if (isBetween(valorTotalPedido, 1000, MAX_VALUE))
            return (valorTotalPedido * OITO_PORCENTO.getValor());
        return -1;
    }
}
