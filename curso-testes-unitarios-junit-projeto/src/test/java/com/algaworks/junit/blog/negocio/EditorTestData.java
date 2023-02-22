package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.modelo.Editor;

import java.math.BigDecimal;

public class EditorTestData {

    private EditorTestData() {
    }

    public static Editor.Builder umEditorNovo() {
        return Editor.builder()
                .nome("Alex Silva")
                .email("alex@gmail.com")
                .valorPagoPorPalavra(BigDecimal.ZERO)
                .premium(false);
    }

    public static Editor.Builder umEditorExistente() {
        return umEditorNovo().id(1L);
    }

    public static Editor.Builder umEditorComIdInexistente() {
        return umEditorNovo()
                .id(99L)
                .email("alex.silva@gmail.com")
                .premium(false);
    }
}
