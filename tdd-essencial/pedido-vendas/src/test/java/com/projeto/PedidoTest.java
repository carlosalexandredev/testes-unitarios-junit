package com.projeto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste na classe de Pedido")
class PedidoTest {

    Pedido pedido;

    @BeforeEach
    void init(){
        pedido = new Pedido();
    }

    @Test
    @DisplayName("Deve permitir adicionar um item no pedido")
    void devePermitirAdicionarItemPedido(){
        pedido.adicionarItem(new Item("Sabonete", 3.0, 10));
    }

    @Test
    @DisplayName("Deve calcular valor total para pedido vazio")
    void calcularTotalPedidoVazio(){
        assertEquals(0.0, pedido.valorTotal(), 0.0001);
    }

    @Test
    @DisplayName("Deve calcular valor total de desconto para pedido vazio")
    void deveCalcularValorTotalEDescontoPedidoVazio(){
        assertEquals(0.0, pedido.valorTotalDesconto(), 0.0001);
    }

    @Test
    @DisplayName("Deve calcular resumo para um item sem desconto")
    void deveCalcularResumoparaItemSemDesconto(){
        pedido.adicionarItem(new Item("Sabonete", 5.0, 5));
        assertResumoPedido(25.0, 0.0);
    }

    private void assertResumoPedido(double total, double desconto) {
        assertEquals(total, pedido.valorTotal(), 0.0001);
        assertEquals(desconto, pedido.valorTotalDesconto(), 0.0001);
    }

}