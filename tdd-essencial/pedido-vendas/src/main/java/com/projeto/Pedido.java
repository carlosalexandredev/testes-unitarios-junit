package com.projeto;

import java.util.Objects;

public class Pedido {

    private Item item;

    public void adicionarItem(Item item) {
        this.item = item;
    }

    public double valorTotal() {
        if(Objects.isNull(item)){
            return 0.0;
        }
        return item.getValor() * item.getQuantidade();
    }

    public double valorTotalDesconto() {
        return 0.0;
    }
}
