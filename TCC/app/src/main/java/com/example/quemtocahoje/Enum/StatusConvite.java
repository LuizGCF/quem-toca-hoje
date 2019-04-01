package com.example.quemtocahoje.Enum;

public enum StatusConvite {
    ABERTO(1)
    ,ACEITO(2)
    ,RECUSADO(3)
    ,EXPIRADO(4)
    ,CANCELADO(5);

    private final int valor;

    StatusConvite (int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
