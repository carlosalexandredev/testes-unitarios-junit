package com.algaworks.junit.utilidade;

import java.util.stream.IntStream;

public enum TipoSaudacao {
    BOM_DIA("Bom dia", IntStream.range(5, 11).toArray()),
    BOA_TARDE("Boa Tarde", IntStream.range(12, 17).toArray()),
    BOA_NOITE("Boa Noite", IntStream.concat(IntStream.range(18, 23), IntStream.range(0, 5)).toArray());

    private final String valor;
    private final int[] horas;

    TipoSaudacao(String valor, int[] horas) {
        this.valor = valor;
        this.horas = horas;
    }

    public String getValor() {
        return valor;
    }

    public int[] getHoras() {
        return horas;
    }

}
