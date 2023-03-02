package org.projeto.model.builder;

import org.projeto.model.Cliente;
import org.projeto.model.Pedido;
import org.projeto.service.StatusPedido;

public class PedidoBuilder {
    private Pedido instancia;

    public PedidoBuilder(){
        instancia = new Pedido();
    }

    public PedidoBuilder para(String nome, String email, String telefone){
        Cliente cliente = new Cliente(nome, email, telefone);
        instancia.setCliente(cliente);
        return this;
    }

    public PedidoBuilder comValor(double valor) {
        instancia.setValor(valor);
        return this;
    }

    public PedidoBuilder comStatus(StatusPedido status){
        instancia.setStatus(status);
        return this;
    }

    public Pedido construir() {
        return instancia;
    }
}
