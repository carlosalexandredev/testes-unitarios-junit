package org.passagem.service;

import org.junit.jupiter.api.*;
import org.passagem.model.Passageiro;
import org.passagem.model.Voo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.passagem.model.TipoPassageiro.GOLD;
import static org.passagem.model.TipoPassageiro.SILVER;

@DisplayName("Testes do Preço da Passagem")
@TestMethodOrder(MethodOrderer.MethodName.class)
class PrecoPassagemServiceTest {
    PrecoPassagemService precoPassagemService;
    Passageiro passageiro;
    Voo voo;

    @BeforeEach
    void init() {
        precoPassagemService = new PrecoPassagemService();
    }

    @Test
    @DisplayName("Dado um passageiro Gold com valor da passagem abaixo do limite, então deve retornar o valor com 10% de desconto.")
    void deveCalcularValorPassagemParaPassageiroGoldComValorAbaixoDoLimite() {
        passageiro = new Passageiro("João", GOLD);
        voo = new Voo("São Paulo", "Rio de Janeiro", 100.0);
        assertValorPassagem(90.00);
    }

    @Test
    @DisplayName("Dado um passageiro Gold com valor da passagem acima do limite, então deve retornar o valor com 15% de desconto.")
    void deveCalcularValorPassagemParaPassageiroGoldComValorAcimaDoLimite() {
        passageiro = new Passageiro("João", GOLD);
        voo = new Voo("São Paulo", "Rio de Janeiro", 600.00);
        assertValorPassagem(510.00);
    }

    @Test
    @DisplayName("Dado um passageiro Silver com valor da passagem abaixo do limite, então deve retornar o valor com 6% de desconto.")
    void devealcularValoPassagemParaPassageiroSilverComValorAbaixoDoLimite() {
        passageiro = new Passageiro("Pedro", SILVER);
        voo = new Voo("São Paulo", "Rio de Janeiro", 100.00);
        assertValorPassagem(94.00);
    }

    @Test
    @DisplayName("Dado um passageiro Silver com valor da passagem acima do limite, então deve retornar o valor com 10% de desconto.")
    void devealcularValoPassagemParaPassageiroSilverComValorAcimaDoLimite() {
        passageiro = new Passageiro("Pedro", SILVER);
        voo = new Voo("São Paulo", "Rio de Janeiro", 800.00);
        assertValorPassagem(720.00);
    }

    private void assertValorPassagem(double esperado) {
        double valor = precoPassagemService.calcular(passageiro, voo);
        assertEquals(esperado, valor, 0.0001);
    }
  
}