package com.projeto;

import org.junit.jupiter.api.*;

import static org.apache.commons.lang3.math.NumberUtils.DOUBLE_ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
@DisplayName("Teste do Pedido")
class PedidoTest {

    PedidoBuilder pedido;

    @BeforeEach
    void init(){
        pedido = new PedidoBuilder();
    }

    @Test
    @DisplayName("Dado um pedido em aberto, o sistema deve permitir a adição de um novo item ao pedido.")
    void devePermitirAdicionarItemPedido(){
        pedido.comItem("Sabonete", 3.00, 10);
    }

    @Test
    @DisplayName("Dado um pedido vazio ao buscar valor total, Então deve retornar zero")
    void calcularTotalPedidoVazio(){
        assertEquals(DOUBLE_ZERO, pedido.construir().resumo().getValorTotal(), 0.0001);
    }

    @Test
    @DisplayName("Dado um pedido sem desconto, ao buscar o valor total de desconto, Então deve retornar zero.")
    void deveCalcularResumoparaItemSemDesconto(){
        pedido.comItem("Sabonete", 5.00, 5);
        assertResumoPedido(25.00, DOUBLE_ZERO);
    }

    @Test
    @DisplayName("Dado dois pedidos sem desconto, ao buscar o valor total de desconto, Então deve retornar zero.")
    void devecalcularResumoParaDoisItensSemDesconto(){
        pedido.comItem("Sabonete", 3.00, 3)
              .comItem("Teclado", 7.00, 3);

        assertResumoPedido(30.00, DOUBLE_ZERO);
    }

    @Test
    @DisplayName("Dado um pedido com valor total acima de R$300, ao aplicar o desconto de 4%, Então deve retornar o total de desconto.")
    void deveAplicarDescontoParaPedidosAcimaTrezentosReais(){
        pedido.comItem("HeadSet", 180, 2);
        assertResumoPedido(360, 14.4);
    }

    @Test
    @DisplayName("Dado um pedido com valor total acima de R$800, ao aplicar o desconto de 6%, Então deve retornar o total de desconto.")
    void deveAplicarDescontoParaPedidosAcimaOitocentosReais(){
        pedido.comItem("Shampoo", 15.00, 30)
              .comItem("Óleo", 15.00, 30);

        assertResumoPedido(900.00, 54);
    }

    @Test
    @DisplayName("Dado um pedido com valor total acima de R$1000, ao aplicar o desconto de 8%, Então deve retornar o total de desconto.")
    void deveAplicarDescontoParaPedidosAcimaMilReais(){
        pedido.comItem("Creme", 15.00, 30)
              .comItem("Shampoo", 10.00, 30)
              .comItem("Óleo", 15.00, 30);

        assertResumoPedido(1200.00, 96.00);
    }

    void assertResumoPedido(double total, double desconto) {
        ResumoPedido resumo = pedido.construir().resumo();
        assertEquals(total, resumo.getValorTotal(), 0.0001);
        assertEquals(desconto, resumo.getDesconto(), 0.0001);
    }
}