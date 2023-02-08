package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.exception.EditorNaoEncontradoException;
import com.algaworks.junit.blog.exception.RegraNegocioException;
import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * A @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) é uma anotação do JUnit 5,
 * usada para personalizar a exibição de nomes de testes em relação ao nome do método de teste. Quando a anotação é aplicada a um método de teste,
 * o nome exibido é gerado substituindo os sublinhados (_) por espaços na string do nome do método. Esta anotação é útil quando você deseja que os nomes de teste exibidos sejam mais legíveis para humanos.
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class CadastroEditorMockitoTest {

    @Mock
    ArmazenamentoEditor armazenamentoEditor;

    @Mock
    GerenciadorEnvioEmail gerenciadorEnvioEmail;

    @InjectMocks
    CadastroEditor cadastroEditor;

    @Captor
    ArgumentCaptor<Mensagem> mensagemArgumentCaptor;

    private static Editor criaEditor() {
        return EditorTestData.umEditorNovo();
    }

    @Nested
    class CadastroComEditorValido {

        @Spy
        Editor editor = criaEditor();

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

        @Test
        public void Dados_editor_valido_deve_chamar_metodo_saldo_do_armazenamento() {
            cadastroEditor.criar(editor);
            Mockito.verify(armazenamentoEditor, times(1))
                    .salvar(Mockito.eq(editor));
        }

        @Test
        public void Dados_editor_valido_ao_criar_excessao_ao_salvar_nao_enviar_email() {
            Mockito.when(armazenamentoEditor.salvar(editor)).thenThrow(new RuntimeException());
            assertAll("Não deve enviar e-mail, quando lançar Exception do armazenamento",
                    () -> assertThrows(RuntimeException.class, () -> cadastroEditor.criar(editor)),
                    () -> Mockito.verify(gerenciadorEnvioEmail, Mockito.never()).enviarEmail(Mockito.any()));
        }

        @Test
        public void Dado_editor_valido_qunado_cadastrar_entao_deve_enviar_email_com_destinario_destino_ao_editor() {
            Editor editoSalvo = cadastroEditor.criar(editor);
            Mockito.verify(gerenciadorEnvioEmail).enviarEmail(mensagemArgumentCaptor.capture());
            Mensagem mensagem = mensagemArgumentCaptor.getValue();
            assertEquals(editoSalvo.getEmail(), mensagem.getDestinatario());
        }

        @Test
        public void Dado_um_editor_valido_quando_cadastrar_entao_deve_verificar_o_email() {
            cadastroEditor.criar(editor);
            Mockito.verify(editor, Mockito.atLeast(1)).getEmail();
        }

        @Test
        public void Dado_um_editor_com_email_existente_logo_deve_lancar_exception() {
            Mockito.when(armazenamentoEditor.encontrarPorEmail("alex@gmail.com"))
                    .thenReturn(Optional.empty())
                    .thenReturn(Optional.of(editor));
            Editor editorComEmailExistente = criaEditor();
            cadastroEditor.criar(editor);
            assertThrows(RegraNegocioException.class,
                    () -> cadastroEditor.criar(editorComEmailExistente));
        }

        @Test
        public void Dado_editor_valido_quando_cadastar_entao_deve_enviar_email_apos_salvar() {
            cadastroEditor.criar(editor);
            InOrder inOrder = Mockito.inOrder(armazenamentoEditor, gerenciadorEnvioEmail);
            inOrder.verify(armazenamentoEditor, times(1)).salvar(editor);
            inOrder.verify(gerenciadorEnvioEmail, times(1)).enviarEmail(Mockito.any(Mensagem.class));
        }
    }

    @Nested
    class CadastroComEditorNull {
        @Test
        public void Dado_editor_null_quando_cadastrar_entao_deve_lancar_exception() {
            assertThrows(NullPointerException.class, () -> cadastroEditor.criar(null));
            Mockito.verify(armazenamentoEditor, Mockito.never()).salvar(Mockito.any());
            Mockito.verify(gerenciadorEnvioEmail, Mockito.never()).enviarEmail(Mockito.any());
        }
    }

    @Nested
    class EdicaoComEditorValido {
        @Spy
        Editor editor = criaEditor();

        @BeforeEach
        public void init() {
            Mockito.when(armazenamentoEditor.salvar(editor)).thenAnswer(invocaca -> invocaca.getArgument(0, Editor.class));
            Mockito.when(armazenamentoEditor.encontrarPorId(1L)).thenReturn(Optional.of(editor));
        }

        @Test
        public void Dado_editor_valido_quando_entao_deve_alterar_editor_salvo() {
            Editor editorAtualizado = EditorTestData.umEditorExistente();
            editorAtualizado.setEmail("alex.silva@email.com");
            editorAtualizado.setNome("Alex Silva");
            cadastroEditor.editar(editorAtualizado);

            Mockito.verify(editor, times(1)).atualizarComDados(editorAtualizado);

            InOrder inOrder = Mockito.inOrder(editor, armazenamentoEditor);
            inOrder.verify(editor).atualizarComDados(editorAtualizado);
            inOrder.verify(armazenamentoEditor).salvar(editor);
        }
    }

    @Nested
    class EdicaoComEditorInexistente {
        Editor editor = EditorTestData.umEditorComIdInexistente();

        @BeforeEach
        public void init() {
            Mockito.when(armazenamentoEditor.encontrarPorId(99L)).thenReturn(Optional.empty());
        }

        @Test
        public void Dado_editor_que_nao_existe_quando_editar_entao_deve_lancar_exception() {
            assertThrows(EditorNaoEncontradoException.class, () -> cadastroEditor.editar(editor));
            verify(armazenamentoEditor, never()).salvar(Mockito.any(Editor.class));
        }
    }
}