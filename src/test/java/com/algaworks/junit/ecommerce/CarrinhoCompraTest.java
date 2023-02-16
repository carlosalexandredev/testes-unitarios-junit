package com.algaworks.junit.ecommerce;

import com.algaworks.junit.blog.exception.RegraNegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Testes Carrinho de Compra:")
class CarrinhoCompraTest {
    Cliente cliente = new Cliente(1L, "Carlos Alexandre");
    CarrinhoCompra carrinhoCompra = new CarrinhoCompra(cliente);

    @Nested
    @DisplayName("Adição de produto no carrinho:")
    class AdicionaProdutoCarrinho {
        Produto produto;

        @BeforeEach
        void criaProduto() {
            produto = new Produto(1L,
                    "Computador",
                    "Computador completo 8GB Ram, 500GB SSD",
                    new BigDecimal(4000));
        }

        @Test
        @DisplayName("Dado produto nulo ao adicionar no carrinho de compras, Então deve retornar uma excessão")
        void produtoNuloDeveRetornarExcessao() {
            RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> carrinhoCompra.adicionarProduto(null, 1));
            assertEquals("Produto não pode ser nulo", exception.getMessage());
        }

        @Test
        @DisplayName("Dado uma quantidade de produto menor que 1 ao adicionar no carrinho de compras, Então deve retornar uma excessão")
        void quantidadeProdutoMenorQueUmRetornarExcessao() {
            RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> carrinhoCompra.adicionarProduto(produto, -2));
            assertEquals("Quantidade não pode ser menor que 1", exception.getMessage());
        }

        @Test
        @DisplayName("Dado um produto que não exista no carrinho de compras, Então deve adiciona-lo")
        void produtoNaoExistenteDeveAdicionar() {
            Produto produtoNovo = new Produto(2L, "Teclado", "Teclado mecânido com Led", new BigDecimal(200));
            carrinhoCompra.adicionarProduto(produtoNovo, 12);
            assertTrue(carrinhoCompra.getItens().stream().anyMatch(item -> item.getProduto().equals(produtoNovo)));
        }

        @Test
        @DisplayName("Dado um produto que já exista no carrinho de compras, Então deve incrementar sua quantidade")
        void produtoExistenteIncrementarQuantidade() {
            int quantidadeA = 5;
            int quantidadeB = 8;
            carrinhoCompra.adicionarProduto(produto, quantidadeA);
            carrinhoCompra.adicionarProduto(produto, quantidadeB);

            int quantidadeEsperada = quantidadeA + quantidadeB;
            int quantidadeRetornada = carrinhoCompra.getItens().stream()
                    .filter(item -> item.getProduto().equals(produto))
                    .mapToInt(ItemCarrinhoCompra::getQuantidade)
                    .sum();

            assertEquals(quantidadeEsperada, quantidadeRetornada);
        }
    }

    @Nested
    @DisplayName("Remoção produto do carrinho:")
    class RemoveProdutoCarrinho {
        Produto produto;

        @BeforeEach
        void criaProduto() {
            produto = new Produto(1L,
                    "Computador",
                    "Computador completo 8GB Ram, 500GB SSD",
                    new BigDecimal(4000));

            carrinhoCompra.adicionarProduto(produto, 2);
        }

        @Test
        @DisplayName("Dado produto nulo ao remover do carrinho de compras, Então deve retornar uma excessão")
        void produtoNuloDeveRetornarExcessao() {
            RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> carrinhoCompra.removerProduto(null));
            assertEquals("Produto não pode ser nulo", exception.getMessage());
        }

        @Test
        @DisplayName("Dado um produto que não exista no carrinho de compras, Então deve retornar uma excessão")
        void produtoNaoExistenteRemover() {
            Produto produtoNovo = new Produto(2L, "Teclado", "Teclado mecânido com Led", new BigDecimal(200));
            RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> carrinhoCompra.removerProduto(produtoNovo));
            assertEquals(String.format("O produto com o ID %d não está presente na lista de itens.", produtoNovo.getId()), exception.getMessage());
        }

        @Test
        @DisplayName("Dado um porduto que já existe no carrinho de compras, Então deve remove-lo independente da quantidade")
        void removeProdutoExistente() {
            carrinhoCompra.removerProduto(produto);
            assertFalse(carrinhoCompra.getItens().stream().anyMatch(p -> p.equals(produto)));
        }
    }
}