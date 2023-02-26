package com.projeto;

import com.projeto.desconto.CalculadoraFaixaDesconto;
import com.projeto.exception.QuantidadeItemInvalidaException;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

@NoArgsConstructor
public class Pedido {

    private CalculadoraFaixaDesconto calculadoraFaixaDesconto;

    public Pedido(CalculadoraFaixaDesconto calculadoraFaixaDesconto) {
        this.calculadoraFaixaDesconto = calculadoraFaixaDesconto;
    }

    private List<Item> itens = new ArrayList<>();

    public void adicionarItem(Item item) {
        validarQuantidadeItem(item);
        this.itens.add(item);
    }

    private static void validarQuantidadeItem(Item item) {
        if(item.getQuantidade() < INTEGER_ZERO)
            throw new QuantidadeItemInvalidaException();
    }

    public ResumoPedido resumo(){
        double valorTotalPedido = itens.stream().mapToDouble(item -> item.getValor() * item.getQuantidade()).sum();
        double valorTotalDesconto = calculadoraFaixaDesconto.desconto(valorTotalPedido);

        return new ResumoPedido(valorTotalPedido, valorTotalDesconto);
    }
}
