package com.example.quemtocahoje.Enum;

public enum StatusProposta {
    ABERTO(1)
    ,RECUSADO(2)
    ,EXPIRADO(3)
    ,CANCELADO(4)
    ,ACEITO(5);

    private final int valor;

    StatusProposta(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
