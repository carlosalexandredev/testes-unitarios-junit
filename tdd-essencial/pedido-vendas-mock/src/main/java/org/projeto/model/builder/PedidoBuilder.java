package org.projeto.model.builder;

import org.projeto.model.Cliente;
import org.projeto.model.Pedido;

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
}
