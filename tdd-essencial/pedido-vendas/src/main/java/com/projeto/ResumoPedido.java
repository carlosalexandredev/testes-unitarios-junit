package com.projeto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumoPedido {
    private double valorTotal;
    private double desconto;
}
