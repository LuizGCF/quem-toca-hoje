package com.example.quemtocahoje.Enums;

public enum TipoUsuario {
    ESPECTADOR(1)
    ,ESTABELECIMENTO(2)
    ,MUSICO(3)
    ,ADMIN(4);

    private final int valor;

    TipoUsuario(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
