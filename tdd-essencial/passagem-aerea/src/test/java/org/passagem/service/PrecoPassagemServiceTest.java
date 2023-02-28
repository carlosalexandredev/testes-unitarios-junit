package org.passagem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.passagem.exception.TipoPassageiroInvalidoException;
import org.passagem.model.Passageiro;
import org.passagem.model.Voo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.passagem.model.TipoPassageiro.GOLD;
import static org.passagem.model.TipoPassageiro.SILVER;

class PrecoPassagemServiceTest {
    PrecoPassagemService precoPassagemService;
    Passageiro passageiro;
    Voo voo;

    @BeforeEach
    void init() throws TipoPassageiroInvalidoException{
        precoPassagemService = new PrecoPassagemService();
    }

    @Test
    void deveCalcularValorPassagemParaPassageiroGoldComValorAbaixoDoLimite() throws TipoPassageiroInvalidoException {
        passageiro = new Passageiro("João", GOLD);
        voo = new Voo("São Paulo", "Rio de Janeiro", 100.0);
        assertValorPassagem(90.00);
    }

    @Test
    void deveCalcularValorPassagemParaPassageiroGoldComValorAcimaDoLimite() throws TipoPassageiroInvalidoException {
        passageiro = new Passageiro("João", GOLD);
        voo = new Voo("São Paulo", "Rio de Janeiro", 600.00);
        assertValorPassagem(510.00);
    }

    @Test
    void devealcularValoPassagemParaPassageiroSilverComValorAbaixoDoLimite() throws TipoPassageiroInvalidoException {
        passageiro = new Passageiro("Pedro", SILVER);
        voo = new Voo("São Paulo", "Rio de Janeiro", 100.00);
        assertValorPassagem(94.00);
    }

    @Test
    void devealcularValoPassagemParaPassageiroSilverComValorAcimaDoLimite() throws TipoPassageiroInvalidoException {
        passageiro = new Passageiro("Pedro", SILVER);
        voo = new Voo("São Paulo", "Rio de Janeiro", 800.00);
        assertValorPassagem(720.00);
    }

    private void assertValorPassagem(double esperado) throws TipoPassageiroInvalidoException {
        double valor = precoPassagemService.calcular(passageiro, voo);
        assertEquals(esperado, valor, 0.0001);
    }
  
}