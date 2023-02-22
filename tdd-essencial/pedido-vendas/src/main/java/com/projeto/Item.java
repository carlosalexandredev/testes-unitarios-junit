package com.projeto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private String produto;
    private double valor;
    private int quantidade;
}
