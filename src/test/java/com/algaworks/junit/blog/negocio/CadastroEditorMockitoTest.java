package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.exception.RegraNegocioException;
import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) é uma anotação do JUnit 5,
 * usada para personalizar a exibição de nomes de testes em relação ao nome do método de teste. Quando a anotação é aplicada a um método de teste,
 * o nome exibido é gerado substituindo os sublinhados (_) por espaços na string do nome do método. Esta anotação é útil quando você deseja que os nomes de teste exibidos sejam mais legíveis para humanos.
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class CadastroEditorMockitoTest {
    Editor editor;

    @Mock
    ArmazenamentoEditor armazenamentoEditor;
    @Mock
    GerenciadorEnvioEmail gerenciadorEnvioEmail;

    @InjectMocks
    CadastroEditor cadastroEditor;

    /**
     * A biblioteca Mockito é usada para criar objetos simulados que imitam o comportamento de objetos reais.
     * Isso é útil para testar a classe CadastroEditor sem precisar depender das implementações reais das classes ArmazenamentoEditor e GerenciadorEnvioEmail.
     * <p>
     * O método when é usado para configurar o comportamento do mock armazenamentoEditor,
     * fazendo com que o método salvar retorne um objeto Editor específico.
     * Em seguida, o mock armazenamentoEditor é passado como argumento para a criação de uma nova instância da classe CadastroEditor.
     */

    @BeforeEach
    public void init() {
        editor = new Editor(null, "Alex", "alex@gmail.com", BigDecimal.TEN, true);
        Mockito.when(armazenamentoEditor.salvar(Mockito.any(Editor.class)))
                .thenAnswer(invocacao -> {
                    Editor editorPassado = invocacao.getArgument(0, Editor.class);
                    editorPassado.setId(1L);
                    return editorPassado;
                });
        cadastroEditor = new CadastroEditor(armazenamentoEditor, gerenciadorEnvioEmail);
    }

    @Test
    public void Dado_um_editor_valido_quando_criar_retornar_id_cadastro() {
        Editor editorsalvo = cadastroEditor.criar(editor);
        assertEquals(1L, editorsalvo.getId());
    }
}