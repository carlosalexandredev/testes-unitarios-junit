package com.algaworks.junit.blog.utilidade;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ConversorSlugTest {

    /**
     * Um Slug é um termo usado para descrever uma string que é utilizada como identificador de URL amigável,
     * geralmente utilizado como parte da URL de uma página da web. Um Slug é geralmente formado pela remoção de espaços,
     * caracteres especiais e acentuação de uma string, substituindo-os por hifens ou sublinhados,
     * resultando em uma string legível que pode ser usada como identificador na URL.
     * Por exemplo, uma string como "Título da página" pode ser convertida em "titulo-da-pagina" como um Slug.
     */
    @Test
    void DeveConverterJuntoComCodigo() {
        try (MockedStatic<GeradorCodigo> mockedStatic = Mockito.mockStatic(GeradorCodigo.class)) {
            mockedStatic.when(GeradorCodigo::gerar).thenReturn("123456");
            String slug = ConversorSlug.converterJuntoComCodigo("olá mundo java");
            assertEquals("ola-mundo-java-123456", slug);
        }
    }

}