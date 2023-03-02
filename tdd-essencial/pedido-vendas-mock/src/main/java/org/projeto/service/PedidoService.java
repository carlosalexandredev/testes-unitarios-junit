package org.projeto.service;

import lombok.AllArgsConstructor;
import org.projeto.email.NotificadorEmail;
import org.projeto.exception.StatusPedidoInvalidoException;
import org.projeto.model.Pedido;
import org.projeto.repository.Pedidos;
import org.projeto.sms.NotificadorSms;

import java.util.List;

import static org.projeto.service.StatusPedido.PAGO;
import static org.projeto.service.StatusPedido.PENDENTE;

@AllArgsConstructor
public class PedidoService {
    private static final double DEZ_PORCENTO = 0.10;
    private Pedidos pedidos;
    private List<AcaoLancamentoPedido> acoesPedidos;

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

    public Pedido pagar(Long codigo) throws StatusPedidoInvalidoException {
        Pedido pedido = pedidos.buscarPedidoPeloCodigo(codigo);
        if(!pedido.getStatus().equals(PENDENTE))
            throw new StatusPedidoInvalidoException();
        pedido.setStatus(PAGO);
        return pedido;
    }
}
