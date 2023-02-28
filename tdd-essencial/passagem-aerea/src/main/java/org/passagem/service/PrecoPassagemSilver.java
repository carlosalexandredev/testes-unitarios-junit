package org.passagem.service;

import org.passagem.model.Voo;
public class PrecoPassagemSilver implements CalculadoraPrecoPassagem {
    private static final double VALOR_LIMITE = 700;
    private static final double DEZ_PORCENTO = 0.10;
    private static final double SEIS_PORCENTO = 0.06;

    @Override
    public double calcular(Voo voo) {
        double preco = voo.getPreco();
            if (preco > VALOR_LIMITE)
                return calculaValorAcimaDoLimite(preco);
            return calculaValorAbaixoDoLimite(preco);
        }

    private static double calculaValorAcimaDoLimite(double preco) {
        return preco - (preco * DEZ_PORCENTO);
    }

    private static double calculaValorAbaixoDoLimite(double preco) {
        return preco - (preco * SEIS_PORCENTO);
    }
}
