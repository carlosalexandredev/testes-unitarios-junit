package com.projeto;

import com.projeto.desconto.*;

public class PedidoBuilder {
    private Pedido instancia;

    public PedidoBuilder(){
        CalculadoraFaixaDesconto calculadoraFaixaDesconto =
                new CalculadoraDescontoTerceiraFaixa(
                        new CalculadoraDescontoSegundaFaixa(
                                new CalculadoraDescontoPrimeiraFaixa(
                                        new FaixaSemDesconto(null))));

        instancia = new Pedido(calculadoraFaixaDesconto);
    }

    public PedidoBuilder comItem(String descricao,double valorUnitario, int quantidade){
        instancia.adicionarItem(new Item(descricao, valorUnitario, quantidade));
        return this;
    }

    public Pedido construir() {
        return instancia;
    }
}
