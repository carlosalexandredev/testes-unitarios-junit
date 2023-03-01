package org.projeto.service;

import lombok.AllArgsConstructor;
import org.projeto.email.NotificadorEmail;
import org.projeto.model.Pedido;
import org.projeto.repository.Pedidos;
import org.projeto.sms.NotificadorSms;

import java.util.List;

@AllArgsConstructor
public class PedidoService {
    public static final double DEZ_PORCENTO = 0.10;
    List<AcaoLancamentoPedido> acoesPedidos;

    public double lancar(Pedido pedido) {
        double imposto = calculaImposto(pedido);
        enviaNotificoes(pedido);
        return imposto;
    }

    private void enviaNotificoes(Pedido pedido) {
        acoesPedidos.stream().forEach(a -> a.executar(pedido));
    }

    private static double calculaImposto(Pedido pedido) {
        return pedido.getValor() * DEZ_PORCENTO;
    }
}
