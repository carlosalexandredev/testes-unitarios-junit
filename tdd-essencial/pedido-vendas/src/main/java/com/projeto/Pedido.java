package com.projeto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private List<Item> itens = new ArrayList<>();

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public ResumoPedido resumo(){
        double valorTotal = itens.stream().mapToDouble(item -> item.getValor() * item.getQuantidade()).sum();
        double desconto = 0;
        if(isBetween(valorTotal, 300, 800)){
            desconto = (valorTotal * 0.04);
        } else if (isBetween(valorTotal, 800, 1000)) {
            desconto = (valorTotal * 0.06);
        }else if (isBetween(valorTotal, 1000, Integer.MAX_VALUE)){
            desconto = (valorTotal * 0.08);
        }

        return new ResumoPedido(valorTotal, desconto);
    }

    private boolean isBetween(double valorTotal, int minimo, int maximo) {
        return valorTotal > minimo && valorTotal <= maximo;
    }
}
