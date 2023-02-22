package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.modelo.Editor;
import com.algaworks.junit.blog.modelo.Ganhos;
import com.algaworks.junit.blog.modelo.Post;
import com.algaworks.junit.blog.utilidade.ProcessadorTextoSimples;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CalculadoraGanhosTest {

    static CalculadoraGanhos calculadora;
    Editor autor;

    Post post;

    @BeforeAll
    static void initAll() {
        calculadora = new CalculadoraGanhos(new ProcessadorTextoSimples(), BigDecimal.TEN);
    }

    @BeforeEach
    void init() {
        autor = new Editor(1L, "Alex", "alex@email.com", new BigDecimal(5), true);
        post = new Post(1L, "Ecossitema Java", "O ecossistema do Java, é muito maduro...", autor,
                "ecossistema-java-abc123", null, false, false);
    }


    @Test
    public void Dado_post_e_autor_premio_Quando_calcular_ganhos_Entao_deve_retornar_valor_com_bonus() {
        Ganhos ganhos = calculadora.calcular(post);

        assertEquals(new BigDecimal(45), ganhos.getTotalGanho());
    }

    @Test
    public void Dado_post_e_autor_premio_Quando_calcular_ganhos_Entao_deve_retornar_quantidade_palavras_no_post() {
        Ganhos ganhos = calculadora.calcular(post);

        assertEquals(7, ganhos.getQuantidadePalavras());
    }

    @Test
    public void Dado_post_e_autor_premio_Quando_calcular_ganhos_Entao_deve_retonar_valor_pago_por_palavra_do_autor() {
        Ganhos ganhos = calculadora.calcular(post);

        assertEquals(autor.getValorPagoPorPalavra(), ganhos.getValorPagoPorPalavra());
    }


    @Test
    public void Dado_post_e_autor_nao_premio_Quando_calcular_ganhos_Entao_deve_retornar_valor_com_bonus() {
        autor.setPremium(false);
        Ganhos ganhos = calculadora.calcular(post);

        assertEquals(new BigDecimal(35), ganhos.getTotalGanho());
    }

}