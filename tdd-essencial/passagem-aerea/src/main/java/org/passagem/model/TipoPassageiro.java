package org.passagem.model;

import org.passagem.service.CalculadoraPrecoPassagem;
import org.passagem.service.PrecoPassagemGold;
import org.passagem.service.PrecoPassagemSilver;

public enum TipoPassageiro {
    GOLD(new PrecoPassagemGold()),
    SILVER(new PrecoPassagemSilver());


    CalculadoraPrecoPassagem calculadoraPrecoPassagem;

    TipoPassageiro(CalculadoraPrecoPassagem calculadoraPrecoPassagem){
        this.calculadoraPrecoPassagem = calculadoraPrecoPassagem;
    }

    public CalculadoraPrecoPassagem getCalculadora(){
        return this.calculadoraPrecoPassagem;
    }
}
