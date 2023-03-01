package org.projeto.model;

import lombok.Data;

@Data
public class Pedido {
    private double valor;
    private Cliente cliente;
}
