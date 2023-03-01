package org.projeto.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.projeto.email.NotificadorEmail;
import org.projeto.model.Pedido;
import org.projeto.model.builder.PedidoBuilder;
import org.projeto.repository.Pedidos;
import org.projeto.sms.NotificadorSms;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do Pedido")
class PedidoServiceTest {
    private PedidoService pedidoService;

    private Pedido pedido;
    @Mock
    private Pedidos pedidos;
    @Mock
    private NotificadorEmail notificadorEmail;
    @Mock
    private NotificadorSms notificadorSms;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        List<AcaoLancamentoPedido> acoesPedido = Arrays.asList(pedidos, notificadorEmail, notificadorSms);
        pedidoService = new PedidoService(acoesPedido);
        pedido = new PedidoBuilder()
                .comValor(100)
                .para("Pedro", "pedro-goiva@email.com", "(61) 9547-6571")
                .construir();
    }

    @Test
    @DisplayName("Dado um novo pedido, então deve calcular e retornar valor de 10% de imposto.")
    void deveCalcularImposto(){
        double imposto = pedidoService.lancar(pedido);
        assertEquals(10.00, imposto, 0.0001);
    }

    @Test
    @DisplayName("Dado um novo pedido, então deve salva no banco de dados.")
    void deveSalvarPedidosBancoDados(){
        pedidoService.lancar(pedido);
        Mockito.verify(pedidos).executar(pedido);
    }

    @Test
    @DisplayName("Dado um novo pedido, então deve enviar por E-mail.")
    void deveEnviarEmail(){
        pedidoService.lancar(pedido);
        Mockito.verify(notificadorEmail).executar(pedido);
    }

    @Test
    @DisplayName("Dado um novo pedido, então deve enviar por SMS.")
    void deveNotificarSms(){
        pedidoService.lancar(pedido);
        Mockito.verify(notificadorSms).executar(pedido);
    }
}