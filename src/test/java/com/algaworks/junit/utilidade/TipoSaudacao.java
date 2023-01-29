package com.algaworks.junit.utilidade;

import java.util.stream.IntStream;

public enum TipoSaudacao {
    BOM_DIA("Bom dia", IntStream.range(5, 11).toArray()),
    BOA_TARDE("Boa Tarde", IntStream.range(12, 17).toArray()),
    BOA_NOITE("Boa Noite", IntStream.concat(IntStream.range(18, 23), IntStream.range(0, 5)).toArray());

    private final String descricao;
    private final int[] horas;

    TipoSaudacao(String descricao, int[] horas) {
        this.descricao = descricao;
        this.horas = horas;
    }

    public String getDescricao() {
        return descricao;
    }

    public int[] getHoras() {
        return horas;
    }

}
