package com.example.quemtocahoje.Enum;

public enum StatusToken {

    ABERTO(1)
    ,INATIVO(2)
    ,EXPIRADO(3);

    private final int valor;

    StatusToken (int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
