package com.example.quemtocahoje.Enum;

public enum AcoesIntegrantesBanda {

    INCLUIR(1)
    ,REMOVER(2)
    ,ATUALIZAR(3);

    private final int valor;

    AcoesIntegrantesBanda (int valor) {this.valor = valor;}

    public int getValor() {return valor;}
}
