package org.projeto.sms;

import org.projeto.model.Pedido;
import org.projeto.service.AcaoLancamentoPedido;

public class NotificadorSms implements AcaoLancamentoPedido {
    @Override
    public void executar(Pedido pedido) {
        System.out.println("Enviando o SMS...");
    }
}
