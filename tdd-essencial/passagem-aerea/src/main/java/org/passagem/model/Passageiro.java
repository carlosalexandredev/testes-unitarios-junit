package org.passagem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Passageiro {
    private String nome;
    private TipoPassageiro tipo;
}
