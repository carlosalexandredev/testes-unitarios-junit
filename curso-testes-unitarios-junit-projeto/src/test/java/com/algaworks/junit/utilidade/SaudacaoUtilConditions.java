package com.algaworks.junit.utilidade;


import org.assertj.core.api.Condition;

public class SaudacaoUtilConditions {

    private SaudacaoUtilConditions() {

    }

    public static Condition<String> igual(String saudacaoEsperada) {
        return new Condition<>((str) -> str.equals(saudacaoEsperada),
                "igual a %s",
                saudacaoEsperada);
    }

    public static Condition<String> igualBomDia() {
        return igual(TipoSaudacao.BOM_DIA.getDescricao());
    }
}
