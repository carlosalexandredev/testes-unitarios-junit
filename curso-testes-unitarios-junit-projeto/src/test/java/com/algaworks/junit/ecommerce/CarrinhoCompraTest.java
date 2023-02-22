package com.algaworks.junit.ecommerce;

import com.algaworks.junit.blog.exception.RegraNegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.algaworks.junit.ecommerce.MensagensPadronizada.*;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Testes Carrinho de Compra:")
class CarrinhoCompraTest {
    Cliente cliente = new Cliente(1L, "Carlos Alexandre");
    CarrinhoCompra carrinhoCompra = new CarrinhoCompra(cliente);
    Produto produto;

    @BeforeEach
    void criaProduto() {
        produto = new Produto(1L,
                "Computador",
                "Computador completo 8GB Ram, 500GB SSD",
                new BigDecimal(4000));
    }

    @Nested
    @DisplayName("Adição de produto no carrinho:")
    class AdicionaProdutoCarrinho {

        @Test
        @DisplayName("Dado produto nulo ao adicionar no carrinho de compras, Então deve retornar uma excessão")
        void produtoNuloDeveRetornarExcessao() {
            RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> carrinhoCompra.adicionarProduto(null, 1));
            assertEquals(MSG_PRODUTO_NULO.getMensagem(), exception.getMessage());
        }

        @Test
        @DisplayName("Dado uma quantidade de produto menor que 1 ao adicionar no carrinho de compras, Então deve retornar uma excessão")
        void quantidadeProdutoMenorQueUmRetornarExcessao() {
            RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> carrinhoCompra.adicionarProduto(produto, -2));
            assertEquals(MSG_QUANTIDADE_INVALIDA.getMensagem(), exception.getMessage());
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

        @BeforeEach
        void criaProduto() {
            carrinhoCompra.adicionarProduto(produto, 2);
        }

        @Test
        @DisplayName("Dado produto nulo ao remover do carrinho de compras, Então deve retornar uma excessão")
        void produtoNuloDeveRetornarExcessao() {
            RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> carrinhoCompra.removerProduto(null));
            assertEquals(MSG_PRODUTO_NULO.getMensagem(), exception.getMessage());
        }

        @Test
        @DisplayName("Dado um produto que não exista no carrinho de compras, Então deve retornar uma excessão")
        void produtoNaoExistenteRetornarExcesao() {
            Produto produtoNovo = new Produto(2L, "Teclado", "Teclado mecânido com Led", new BigDecimal(200));
            RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> carrinhoCompra.removerProduto(produtoNovo));
            assertEquals(String.format(MSG_PRODUTO_NAO_ENCONTRADO.getMensagem(), produtoNovo.getId()), exception.getMessage());
        }

        @Test
        @DisplayName("Dado um porduto que já existe no carrinho de compras, Então deve remove-lo independente da quantidade")
        void removeProdutoExistente() {
            carrinhoCompra.removerProduto(produto);
            assertFalse(carrinhoCompra.getItens().stream().anyMatch(p -> p.equals(produto)));
        }
    }

    @Nested
    @DisplayName("Aumentar quantidade de produto:")
    class AumentarQuantidadeProduto {

        @BeforeEach
        void criaProduto() {
            carrinhoCompra.adicionarProduto(produto, 2);
        }

        @Test
        @DisplayName("Dado produto nulo ao aumentar sua quantidade no carrinho de compras, Então deve retornar uma excessão")
        void produtoNuloDeveRetornarExcessao() {
            RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> carrinhoCompra.aumentarQuantidadeProduto(null));
            assertEquals(MSG_PRODUTO_NULO.getMensagem(), exception.getMessage());
        }

        @Test
        @DisplayName("Dado produto que não exista ao aumentar sua quantidade no carrinho de compras, Então deve retornar uma excessão")
        void produtoNaoExistenteRetornarExcesao() {
            Produto produtoNovo = new Produto(2L, "Teclado", "Teclado mecânido com Led", new BigDecimal(200));
            RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> carrinhoCompra.aumentarQuantidadeProduto(produtoNovo));
            assertEquals(String.format(MSG_PRODUTO_NAO_ENCONTRADO.getMensagem(), produtoNovo.getId()), exception.getMessage());
        }

        @Test
        @DisplayName("Dado um porduto que já existe no carrinho de compras, Então deve aumentar sua quantidade")
        void aumentaQuantidadeProdutoExistente() {
            carrinhoCompra.aumentarQuantidadeProduto(produto);
            assertEquals(3, carrinhoCompra.getItens().stream()
                    .filter(item -> item.getProduto().equals(produto))
                    .mapToInt(ItemCarrinhoCompra::getQuantidade).sum());
        }
    }

    @Nested
    @DisplayName("Diminuir quantidade de produto:")
    class DiminuirQuantidadeProduto {

        @BeforeEach
        void criaProduto() {
            carrinhoCompra.adicionarProduto(produto, 2);
        }

        @Test
        @DisplayName("Dado produto nulo ao diminuir sua quantidade no carrinho de compras, Então deve retornar uma excessão")
        void produtoNuloDeveRetornarExcessao() {
            RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> carrinhoCompra.diminuirQuantidadeProduto(null));
            assertEquals(MSG_PRODUTO_NULO.getMensagem(), exception.getMessage());
        }

        @Test
        @DisplayName("Dado produto que não exista ao diminuir sua quantidade no carrinho de compras, Então deve retornar uma excessão")
        void produtoNaoExistenteRetornarExcesao() {
            Produto produtoNovo = new Produto(2L, "Teclado", "Teclado mecânido com Led", new BigDecimal(200));
            RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> carrinhoCompra.diminuirQuantidadeProduto(produtoNovo));
            assertEquals(String.format(MSG_PRODUTO_NAO_ENCONTRADO.getMensagem(), produtoNovo.getId()), exception.getMessage());
        }

        @Test
        @DisplayName("Dado um porduto que já existe no carrinho de compras, Então deve diminuir sua quantidade")
        void diminuiQuantidadeProdutoExistente() {
            carrinhoCompra.diminuirQuantidadeProduto(produto);
            assertEquals(1, carrinhoCompra.getItens().stream()
                    .filter(item -> item.getProduto().equals(produto))
                    .mapToInt(ItemCarrinhoCompra::getQuantidade).sum());
        }
    }

    @Nested
    @DisplayName("Totalização de produtos do carrinho:")
    class SomarItens {

        @Test
        @DisplayName("Dado um carrinho de compras com produtos, Então deve retornar seu valor total")
        void retornaValorTotalCarrinhoCompras() {
            carrinhoCompra.adicionarProduto(produto, 2);
            carrinhoCompra.adicionarProduto(produto, 2);

            BigDecimal valorTotal = carrinhoCompra.getValorTotal();
            BigDecimal valorEsperado = new BigDecimal(16000);

            assertEquals(valorEsperado, valorTotal);
        }

        @Test
        @DisplayName("Dado um carrinho de compras com produtos, Então deve retornar sua quantidade total de produtos")
        void retornaQunatidadeTotalCarrinhoCompras() {
            carrinhoCompra.adicionarProduto(produto, 2);
            carrinhoCompra.adicionarProduto(produto, 3);

            Integer quantidadeTotal = carrinhoCompra.getQuantidadeTotalDeProdutos();
            Integer qunatidadeEsperada = 5;

            assertEquals(quantidadeTotal, qunatidadeEsperada);
        }
    }

    @Nested
    @DisplayName("Esvaziar o carrinho:")
    class Lixeira {

        @Test
        @DisplayName("Dado um carrinho de compras com produtos, Então deve remover todos os produtos")
        void retornaQunatidadeTotalCarrinhoCompras() {
            carrinhoCompra.adicionarProduto(produto, 2);
            carrinhoCompra.adicionarProduto(produto, 3);

            Integer quantidadeTotal = carrinhoCompra.getQuantidadeTotalDeProdutos();
            Integer qunatidadeEsperada = 5;

            assertEquals(quantidadeTotal, qunatidadeEsperada);
        }
    }
}