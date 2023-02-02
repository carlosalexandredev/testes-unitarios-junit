package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.exception.RegraNegocioException;
import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) é uma anotação do JUnit 5,
 * usada para personalizar a exibição de nomes de testes em relação ao nome do método de teste. Quando a anotação é aplicada a um método de teste,
 * o nome exibido é gerado substituindo os sublinhados (_) por espaços na string do nome do método. Esta anotação é útil quando você deseja que os nomes de teste exibidos sejam mais legíveis para humanos.
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CadastroEditorTest {

    CadastroEditor cadastroEditor;
    ArmazenamentoEditorEmMemoria armazenamentoEditor;
    Editor editor;

    //    @BeforeAll
    @EnabledIf("false")
    public void AntesDeTudo() {
        armazenamentoEditor = new ArmazenamentoEditorEmMemoria();
        cadastroEditor = new CadastroEditor(
                armazenamentoEditor,
                new GerenciadorEnvioEmail() {
                    @Override
                    void enviarEmail(Mensagem mensagem) {
                        System.out.println("Enviando mensagem para: " + mensagem.getDestinatario());
                    }
                });
    }

    @BeforeEach
    public void antesDeCada() {
        editor = new Editor(null, "Alex", "alex@gmail.com", BigDecimal.TEN, true);
        armazenamentoEditor = new ArmazenamentoEditorEmMemoria();

        cadastroEditor = new CadastroEditor(
                armazenamentoEditor,
                new GerenciadorEnvioEmail() {
                    @Override
                    void enviarEmail(Mensagem mensagem) {
                        System.out.println("Enviando mensagem para: " + mensagem.getDestinatario());
                    }
                });
    }

    @Test
    public void Dado_um_editor_valido_retornar_id_cadastro() {
        Editor editorSalvo = cadastroEditor.criar(editor);
        assertEquals(1L, editor.getId());
        assertTrue(armazenamentoEditor.chamouSalvar);
    }

    @Test
    public void Dado_editor_null__deve_lancar_excessao() {
        assertThrows(NullPointerException.class, () -> cadastroEditor.criar(null));
        assertFalse(armazenamentoEditor.chamouSalvar);
    }

    @Test
    public void Dado_editor_email_existente_ao_criar_deve_lancar_excessao() {
        editor.setEmail("alex.existe@gmail.com");
        assertThrows(RegraNegocioException.class, () -> cadastroEditor.criar(editor));
        assertFalse(armazenamentoEditor.chamouSalvar);
    }

    @Test
    public void Dado_editor_email_existente_ao_criar_nao_deve_salvar() {
        editor.setEmail("alex.existe@gmail.com");
        try {
            cadastroEditor.criar(editor);
        } catch (RegraNegocioException exception) {
            assertFalse(armazenamentoEditor.chamouSalvar);
        }
    }
}