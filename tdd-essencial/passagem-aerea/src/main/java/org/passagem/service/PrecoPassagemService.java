package org.passagem.service;

import org.passagem.model.Passageiro;
import org.passagem.model.Voo;

public class PrecoPassagemService {
    public double calcular(Passageiro passageiro, Voo voo) {
        return passageiro.getTipo().getCalculadora().calcular(voo);
    }
}
