package com.projeto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste do Pedido")
class PedidoTest {

    Pedido pedido;

    @BeforeEach
    void init(){
        pedido = new Pedido();
    }

    @Test
    @DisplayName("Deve permitir adicionar um item no pedido")
    void devePermitirAdicionarItemPedido(){
        pedido.adicionarItem(new Item("Sabonete", 3.00, 10));
    }

    @Test
    @DisplayName("Deve calcular valor total para pedido vazio")
    void calcularTotalPedidoVazio(){
        assertEquals(0.00, pedido.resumo().getValorTotal(), 0.0001);
    }

    @Test
    @DisplayName("Deve calcular valor total de desconto para pedido vazio")
    void deveCalcularValorTotalEDescontoPedidoVazio(){
        Pedido pedidoVazio = new Pedido();
        assertEquals(0.00, pedidoVazio.resumo().getDesconto(), 0.0001);
    }

    @Test
    @DisplayName("Deve calcular resumo para um item sem desconto")
    void deveCalcularResumoparaItemSemDesconto(){
        pedido.adicionarItem(new Item("Sabonete", 5.00, 5));
        assertResumoPedido(25.00, 0.00);
    }

    @Test
    @DisplayName("Deve calcular resumo para dois itens sem desconto")
    void devecalcularResumoParaDoisItensSemDesconto(){
        pedido.adicionarItem(new Item("Sabonete", 3.00, 3));
        pedido.adicionarItem(new Item("Teclado", 7.00, 3));

        assertResumoPedido(30.00, 0.00);
    }

    @Test
    @DisplayName("Deve aplicar 4% desconto na para pedidos acima de R$300")
    void deveAplicarDescontoParaPedidosAcimaTrezentosReais(){
        pedido.adicionarItem(new Item("HeadSet", 180, 2));
        assertResumoPedido(360, 14.4);
    }

    @Test
    @DisplayName("Deve aplicar 6% desconto na para pedidos acima de R$800")
    void deveAplicarDescontoParaPedidosAcimaOitocentosReais(){
        pedido.adicionarItem(new Item("Shampoo", 15.00, 30));
        pedido.adicionarItem(new Item("Óleo", 15.00, 30));

        assertResumoPedido(900.00, 54);
    }

    @Test
    @DisplayName("Deve aplicar 8% desconto na para pedidos acima de R$1000")
    void deveAplicarDescontoParaPedidosAcimaMilReais(){
        pedido.adicionarItem(new Item("Creme", 15.00, 30));
        pedido.adicionarItem(new Item("Shampoo", 10.00, 30));
        pedido.adicionarItem(new Item("Óleo", 15.00, 30));

        assertResumoPedido(1200.00, 96.00);

    }

    void assertResumoPedido(double total, double desconto) {
        ResumoPedido resumo = pedido.resumo();
        assertEquals(total, resumo.getValorTotal(), 0.0001);
        assertEquals(desconto, resumo.getDesconto(), 0.0001);
    }

}