package com.projeto;

import com.projeto.desconto.*;
import org.junit.jupiter.api.*;

import static org.apache.commons.lang3.math.NumberUtils.DOUBLE_ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
@DisplayName("Teste do Pedido")
class PedidoTest {

    Pedido pedido;

    CalculadoraFaixaDesconto calculadoraFaixaDesconto;

    @BeforeEach
    void init(){
        calculadoraFaixaDesconto =
                new CalculadoraDescontoTerceiraFaixa(
                        new CalculadoraDescontoSegundaFaixa(
                                new CalculadoraDescontoPrimeiraFaixa(
                                        new FaixaSemDesconto(null))));

        pedido = new Pedido(calculadoraFaixaDesconto);
    }

    @Test
    @DisplayName("Dado um pedido em aberto, o sistema deve permitir a adição de um novo item ao pedido.")
    void devePermitirAdicionarItemPedido(){
        pedido.adicionarItem(new Item("Sabonete", 3.00, 10));
    }

    @Test
    @DisplayName("Dado um pedido vazio ao buscar valor total, Então deve retornar zero")
    void calcularTotalPedidoVazio(){
        assertEquals(DOUBLE_ZERO, pedido.resumo().getValorTotal(), 0.0001);
    }

    @Test
    @DisplayName("Dado um pedido vazio ao buscar valor total de desconto, Então deve retornar zero")
    void deveCalcularValorTotalEDescontoPedidoVazio(){
        Pedido pedidoVazio = new Pedido(calculadoraFaixaDesconto);
        assertEquals(DOUBLE_ZERO, pedidoVazio.resumo().getDesconto(), 0.0001);
    }

    @Test
    @DisplayName("Dado um pedido sem desconto, ao buscar o valor total de desconto, Então deve retornar zero.")
    void deveCalcularResumoparaItemSemDesconto(){
        pedido.adicionarItem(new Item("Sabonete", 5.00, 5));
        assertResumoPedido(25.00, DOUBLE_ZERO);
    }

    @Test
    @DisplayName("Dado dois pedidos sem desconto, ao buscar o valor total de desconto, Então deve retornar zero.")
    void devecalcularResumoParaDoisItensSemDesconto(){
        pedido.adicionarItem(new Item("Sabonete", 3.00, 3));
        pedido.adicionarItem(new Item("Teclado", 7.00, 3));

        assertResumoPedido(30.00, DOUBLE_ZERO);
    }

    @Test
    @DisplayName("Dado um pedido com valor total acima de R$300, ao aplicar o desconto de 4%, Então deve retornar o total de desconto.")
    void deveAplicarDescontoParaPedidosAcimaTrezentosReais(){
        pedido.adicionarItem(new Item("HeadSet", 180, 2));
        assertResumoPedido(360, 14.4);
    }

    @Test
    @DisplayName("Dado um pedido com valor total acima de R$800, ao aplicar o desconto de 6%, Então deve retornar o total de desconto.")
    void deveAplicarDescontoParaPedidosAcimaOitocentosReais(){
        pedido.adicionarItem(new Item("Shampoo", 15.00, 30));
        pedido.adicionarItem(new Item("Óleo", 15.00, 30));

        assertResumoPedido(900.00, 54);
    }

    @Test
    @DisplayName("Dado um pedido com valor total acima de R$1000, ao aplicar o desconto de 8%, Então deve retornar o total de desconto.")
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