package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.algaworks.junit.utilidade.SaudacaoUtil.saudar;
import static com.algaworks.junit.utilidade.SaudacaoUtilConditions.igualBomDia;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SaudacaoUtilTest {

    /**
     * @Method assertEquals(expected, actual)
     * Verifica se o valor esperado é igual ao valor atual e lança uma exceção AssertionError se não for.
     * Podemos usar este método, por exemplo, para garantir que o resultado de uma operação matemática seja o esperado.
     */
    @Test
    @DisplayName("Deve saudar com 'Bom dia'")
    public void Dado_um_horario_matutino_Quando_saudar_Entao_deve_retornar_boa_dia() {
        for (int hora : TipoSaudacao.BOM_DIA.getHoras()) {
            String saudacao = saudar(hora);
            assertThat(saudacao).is(igualBomDia());
        }
    }

    @Test
    @DisplayName("Deve saudar com 'Boa tarde'")
    public void Dado_um_horario_vespertino_Quando_saudar_Entao_deve_retornar_boa_tarde() {
        for (int hora : TipoSaudacao.BOA_TARDE.getHoras()) {
            String saudacao = saudar(hora);
            assertEquals("Boa tarde", saudacao, String.format("Saudacao incorreta | HORA: %s", hora));
        }
    }

    @Test
    @DisplayName("Deve saudar com 'Boa noite'")
    public void Dado_um_horario_noturno_Quando_saudar_Entao_deve_retornar_boa_noite() {
        for (int hora : TipoSaudacao.BOA_NOITE.getHoras()) {
            String saudacao = saudar(hora);
            assertEquals("Boa noite", saudacao, String.format("Saudacao incorreta | HORA: %s", hora));
        }
    }

    @Test
    public void Dado_uma_hora_invalida_Quando_saudar_Entao_deve_lancar_excessao() {
        int horaInvalida = -10;
        assertThatThrownBy(() -> saudar(horaInvalida))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Hora inválida");
    }

    /**
     * @Method assertDoesNotThrow é usado para garantir que um determinado bloco de código não lança uma exceção.
     * Ele é usado como um mecanismo de teste para garantir que o código esteja funcionando corretamente e não está gerando exceções inesperadas.
     */
    @Test
    public void Dado_uma_hora_valida_Quando_saudar_entao_nao_deve_lancar_excessao() {
        int horaValida = 0;
        Executable chamadaValidaDeMetodo = () -> saudar(horaValida);
        assertDoesNotThrow(chamadaValidaDeMetodo);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10, 11})
    public void Dado_horario_matinal_Quando_saudar_Entao_deve_retornar_bom_dia(int hora) {
        String saudacao = saudar(hora);
        assertEquals(TipoSaudacao.BOM_DIA.getDescricao(), saudacao, String.format("Saudacao incorreta | HORA: %s", hora));
    }
}