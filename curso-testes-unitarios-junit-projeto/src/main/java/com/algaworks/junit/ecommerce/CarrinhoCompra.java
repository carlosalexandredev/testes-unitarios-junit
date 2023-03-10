package com.algaworks.junit.ecommerce;

import com.algaworks.junit.blog.exception.RegraNegocioException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.math.BigDecimal.ZERO;

public class CarrinhoCompra {

	private final Cliente cliente;
	private final List<ItemCarrinhoCompra> itens;

	public CarrinhoCompra(Cliente cliente) {
		this(cliente, new ArrayList<>());
	}

	public CarrinhoCompra(Cliente cliente, List<ItemCarrinhoCompra> itens) {
		Objects.requireNonNull(cliente);
		Objects.requireNonNull(itens);
		this.cliente = cliente;
		this.itens = new ArrayList<>(itens); //Cria lista caso passem uma imutável
	}

	// Deve retornar uma nova lista para que a antiga não seja alterada
	public List<ItemCarrinhoCompra> getItens() {
		return new ArrayList<>(itens) {
		};
	}

	public Cliente getCliente() {
		return cliente;
	}

	// Parâmetros não podem ser nulos, deve retornar uma exception
	// Quantidade não pode ser menor que 1
	// Deve incrementar a quantidade caso o produto já exista
	public void adicionarProduto(Produto produto, int quantidade) {
		verificaProdutoNulo(produto);
		if (quantidade < 1)
			throw new RegraNegocioException("Quantidade não pode ser menor que 1");

		Optional<ItemCarrinhoCompra> itemExistente = itens.stream().filter(item -> item.getProduto().equals(produto)).findFirst();

		if (itemExistente.isPresent()) {
			itemExistente.get().adicionarQuantidade(quantidade);
		} else {
			itens.add(new ItemCarrinhoCompra(produto, quantidade));
		}
	}

	// Parâmetro não pode ser nulo, deve retornar uma exception
	// Caso o produto não exista, deve retornar uma exception
	// Deve remover o produto independente da quantidade
	public void removerProduto(Produto produto) {
		verificaProdutoNulo(produto);
		verificaSeProdutoExiste(produto);
		itens.removeIf(prod -> prod.equals(produto));
	}


	// Parâmetro não pode ser nulo, deve retornar uma exception
	// Caso o produto não exista, deve retornar uma exception
	// Deve aumentar em um quantidade do produto
	public void aumentarQuantidadeProduto(Produto produto) {
		verificaProdutoNulo(produto);
		verificaSeProdutoExiste(produto);

		itens.stream()
				.filter(item -> item.getProduto().equals(produto))
				.forEach(item -> item.adicionarQuantidade(1));
	}

	// Parâmetro não pode ser nulo, deve retornar uma exception
	// Caso o produto não exista, deve retornar uma exception
	// Deve diminuir a quantidade do produto, caso tenha apenas um produto, deve remover da lista
	public void diminuirQuantidadeProduto(Produto produto) {
		verificaProdutoNulo(produto);
		verificaSeProdutoExiste(produto);
		itens.stream()
				.filter(item -> item.getProduto().equals(produto))
				.forEach(item -> item.subtrairQuantidade(1));
	}

	// Implementar soma do valor total de todos itens
	public BigDecimal getValorTotal() {
		return itens.stream().map(ItemCarrinhoCompra::getValorTotal).reduce(ZERO, BigDecimal::add);
	}

	// Retorna quantidade total de itens no carrinho
	// Exemplo um carrinho com 2 itens, com a quantidade 2 e 3 para cada item respectivamente, deve retornar 5
	public int getQuantidadeTotalDeProdutos() {
		return itens.stream().map(ItemCarrinhoCompra::getQuantidade).reduce(0, Integer::sum);
	}

	// Deve remover todos os itens
	public void esvaziar() {
		itens.clear();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CarrinhoCompra that = (CarrinhoCompra) o;
		return Objects.equals(itens, that.itens) && Objects.equals(cliente, that.cliente);
	}

	private static void verificaProdutoNulo(Produto produto) {
		if (Objects.isNull(produto))
			throw new RegraNegocioException("Produto não pode ser nulo");
	}

	private void verificaSeProdutoExiste(Produto produto) {
		if (itens.stream().map(ItemCarrinhoCompra::getProduto).noneMatch(produto::equals)) {
			throw new RegraNegocioException(String.format("O produto com o ID %d não está presente na lista de itens.", produto.getId()));
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(itens, cliente);
	}
}