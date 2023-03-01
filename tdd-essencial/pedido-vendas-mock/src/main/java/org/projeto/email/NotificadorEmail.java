package org.projeto.email;

import org.projeto.model.Pedido;
import org.projeto.service.AcaoLancamentoPedido;

public class NotificadorEmail implements AcaoLancamentoPedido {
    @Override
    public void executar(Pedido pedido) {
        System.out.println("Enviando o e-mail...");
    }
}