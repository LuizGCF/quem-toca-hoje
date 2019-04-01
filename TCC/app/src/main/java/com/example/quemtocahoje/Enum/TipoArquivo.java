package com.example.quemtocahoje.Enum;

public enum TipoArquivo {
    FOTO_PERFIL(1)
    ,FOTO_DIVULGACAO(2)
    ,VIDEO_DIVULGACAO(3);

    private final int valor;

    TipoArquivo(int valor) { this.valor = valor;}

    public int getValor() { return valor; }
}
