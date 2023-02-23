package com.projeto;

import com.projeto.desconto.CalculadoraFaixaDesconto;

import java.util.ArrayList;
import java.util.List;

import static com.projeto.TaxaDesconto.*;
import static java.lang.Integer.MAX_VALUE;

public class Pedido {

    private CalculadoraFaixaDesconto calculadoraFaixaDesconto;

    public Pedido(CalculadoraFaixaDesconto calculadoraFaixaDesconto) {
        this.calculadoraFaixaDesconto = calculadoraFaixaDesconto;
    }

    private List<Item> itens = new ArrayList<>();

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public ResumoPedido resumo(){
        double valorTotalPedido = itens.stream().mapToDouble(item -> item.getValor() * item.getQuantidade()).sum();
        double valorTotalDesconto = calculadoraFaixaDesconto.desconto(valorTotalPedido);

        return new ResumoPedido(valorTotalPedido, valorTotalDesconto);
    }

    private boolean isBetween(double valorTotal, double minimo, double maximo) {
        return valorTotal > minimo && valorTotal <= maximo;
    }
}
