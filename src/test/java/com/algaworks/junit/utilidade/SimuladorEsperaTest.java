package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class SimuladorEsperaTest {

    @Test
    @Disabled("Não é mais aplicável")
    void deveEsperarENaoDarTimeoutI() {
        assertTimeout(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofMillis(10)));
    }

    /**
     * A expressão Assumptions.assumeTrue("PROD".equals(System.getenv("ENV"))) é usada para assumir que a variável de ambiente "ENV" é igual a "PROD".
     * Isso significa que, se essa condição não for verdadeira, o código a seguir não será executado.
     */
    @Test
    void deveEsperarENaoDarTimeoutII() {
        Assumptions.assumeTrue("PROD".equals(System.getenv("ENV")), () -> "Abortando teste: Não deve ser  executado em PROD");
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofMillis(10)));
    }

}