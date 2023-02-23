package com.projeto;

public enum TaxaDesconto {
    QUATRO_PORCENTO(0.04),
    SEIS_PORCENTO(0.06),
    OITO_PORCENTO(0.08);

    private double valor;

    TaxaDesconto(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
