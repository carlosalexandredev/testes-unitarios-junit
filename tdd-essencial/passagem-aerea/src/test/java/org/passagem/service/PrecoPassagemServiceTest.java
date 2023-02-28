package org.passagem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.passagem.model.Passageiro;
import org.passagem.model.TipoPassageiro;
import org.passagem.model.Voo;

import static org.junit.jupiter.api.Assertions.*;

class PrecoPassagemServiceTest {
    PrecoPassagemService precoPassagemService;

    @BeforeEach
    void init(){
        precoPassagemService = new PrecoPassagemService();
    }

    @Test
    void devePermitirChamarCalculoValor(){
        Passageiro passageiro = new Passageiro("João", TipoPassageiro.GOLD);
        Voo voo = new Voo("São Paulo", "Rio de Janeiro", 100);
        double valor = precoPassagemService.calcular(passageiro, voo);
    }
  
}