package org.passagem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Voo {
    private String origem;
    private String detino;
    private double preco;
}
