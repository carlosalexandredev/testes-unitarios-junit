package org.projeto.model;

import lombok.Data;
import org.projeto.service.StatusPedido;

@Data
public class Pedido {
    private double valor;
    private Cliente cliente;
    private StatusPedido status;
}
