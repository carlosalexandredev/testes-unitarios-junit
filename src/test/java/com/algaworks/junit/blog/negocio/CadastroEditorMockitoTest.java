package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.exception.RegraNegocioException;
import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) é uma anotação do JUnit 5,
 * usada para personalizar a exibição de nomes de testes em relação ao nome do método de teste. Quando a anotação é aplicada a um método de teste,
 * o nome exibido é gerado substituindo os sublinhados (_) por espaços na string do nome do método. Esta anotação é útil quando você deseja que os nomes de teste exibidos sejam mais legíveis para humanos.
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CadastroEditorMockitoTest {

    CadastroEditor cadastroEditor;
    Editor editor;

    @BeforeEach
    public void init() {
        editor = new Editor(null, "Alex", "alex@gmail.com", BigDecimal.TEN, true);

        ArmazenamentoEditor armazenamentoEditor = Mockito.mock(ArmazenamentoEditor.class);
        Mockito.when(armazenamentoEditor.salvar(editor))
                .thenReturn(new Editor(1L, "Alex", "alex@gmail.com", BigDecimal.TEN, true));

        GerenciadorEnvioEmail gerenciadorEnvioEmail = Mockito.mock(GerenciadorEnvioEmail.class);

        cadastroEditor = new CadastroEditor(armazenamentoEditor, gerenciadorEnvioEmail);
    }

    @Test
    public void Dado_um_editor_valido_quando_criar_retornar_id_cadastro() {
        Editor editorsalvo = cadastroEditor.criar(editor);
        assertEquals(1L, editorsalvo.getId());
    }
}