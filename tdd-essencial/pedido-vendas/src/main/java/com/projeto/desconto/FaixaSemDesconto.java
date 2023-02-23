package com.projeto.desconto;

import static com.projeto.TaxaDesconto.OITO_PORCENTO;
import static java.lang.Integer.MAX_VALUE;

public class FaixaSemDesconto extends CalculadoraFaixaDesconto{
    public FaixaSemDesconto(CalculadoraFaixaDesconto proximo) {
        super(proximo);
    }

    @Override
    protected double calcular(double valorTotalPedido) {
        return 0;
    }
}
