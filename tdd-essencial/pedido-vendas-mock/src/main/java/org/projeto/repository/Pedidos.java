package org.projeto.repository;

import org.projeto.model.Pedido;
import org.projeto.service.AcaoLancamentoPedido;

public class Pedidos implements AcaoLancamentoPedido {
    @Override
    public void executar(Pedido pedido) {
        System.out.println("Salvando no banco de dados...");
    }

    public Pedido buscarPedidoPeloCodigo(Long codigo) {
        return new Pedido();
    }
}
