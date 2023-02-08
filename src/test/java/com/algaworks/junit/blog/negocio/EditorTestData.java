package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.modelo.Editor;

import java.math.BigDecimal;

public class EditorTestData {

    private EditorTestData() {
    }

    public static Editor umEditorNovo() {
        return new Editor(null, "Alex Silva", "alex@gmail.com", BigDecimal.ZERO, false);
    }

    public static Editor umEditorExistente() {
        return new Editor(1L, "Alex Silva", "alex.silva@gmail.com", BigDecimal.ZERO, false);
    }

    public static Editor umEditorComIdInexistente() {
        return new Editor(99L, "Alex Silva", "alex.silva@gmail.com", BigDecimal.ZERO, false);
    }
}
