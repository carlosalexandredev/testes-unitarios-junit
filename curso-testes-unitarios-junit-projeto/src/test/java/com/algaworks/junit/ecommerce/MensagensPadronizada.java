package com.algaworks.junit.ecommerce;

public enum MensagensPadronizada {
    MSG_PRODUTO_NULO("Produto não pode ser nulo"),
    MSG_QUANTIDADE_INVALIDA("Quantidade não pode ser menor que 1"),
    MSG_PRODUTO_NAO_ENCONTRADO("O produto com o ID %d não está presente na lista de itens.");

    private final String mensagem;

    MensagensPadronizada(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
