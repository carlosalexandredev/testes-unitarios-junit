package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.modelo.Editor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ArmazenamentoEditorEmMemoria implements ArmazenamentoEditor {

    public boolean chamouSalvar;

    @Override
    public Editor salvar(Editor editor) {
        this.chamouSalvar = true;
        if (Objects.isNull(editor.getId())) {
            editor.setId(1L);
        }
        return editor;
    }

    @Override
    public Optional<Editor> encontrarPorId(Long editor) {
        return Optional.empty();
    }

    @Override
    public Optional<Editor> encontrarPorEmail(String email) {
        if (email.equals("alex.existe@gmail.com")) {
            return Optional.of(new Editor(2L, "Alex", "alex@gmail.com", BigDecimal.TEN, true));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Editor> encontrarPorEmailComIdDiferenteDe(String email, Long id) {
        return Optional.empty();
    }

    @Override
    public void remover(Long editorId) {

    }

    @Override
    public List<Editor> encontrarTodos() {
        return null;
    }
}
