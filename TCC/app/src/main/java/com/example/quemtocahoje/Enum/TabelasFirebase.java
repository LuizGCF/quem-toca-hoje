package com.example.quemtocahoje.Enum;

public enum TabelasFirebase {

     Usuarios(1)
    ,Endereco(2)
    ,Convite(3)
    ,Token(4)
    ,Proposta(5)
    ,Evento(6)
    ,Banda(8)
    ,Autenticacao(9)
    ,Avaliacao(7)

    ;
    private final int valor;

    TabelasFirebase(int valor) { this.valor = valor;}

    public int getValor() { return valor; }
}
