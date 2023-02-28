package org.passagem.service;

import org.passagem.model.Voo;

public class PrecoPassagemGold implements CalculadoraPrecoPassagem{
    private static final double VALOR_LIMITE = 500;
    private static final double DEZ_PORCENTO = 0.10;
    private static final double QUINZE_PORCENTO = 0.15;


    @Override
    public double calcular(Voo voo) {
        double preco = voo.getPreco();
        if (preco > VALOR_LIMITE)
            return calcularValorAcimaDoLimite(preco);
        return calcularValorAbaixoDoLimite(preco);
    }

    private static double calcularValorAbaixoDoLimite(double preco) {
        return preco - (preco * DEZ_PORCENTO);
    }

    private static double calcularValorAcimaDoLimite(double preco) {
        return preco - (preco * QUINZE_PORCENTO);
    }
}
