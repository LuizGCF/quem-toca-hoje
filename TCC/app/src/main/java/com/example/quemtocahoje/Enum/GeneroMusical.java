package com.example.quemtocahoje.Enum;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GeneroMusical {
    Rock(1)
    ,MPB(2)
    ,Gospel(3)
    ,Samba(4)
    ,Pop(5)
    ,Pagode(6)
    ,Country(7)
    ,Sertanejo(8);

    private final int valor;

    GeneroMusical (int valor) {this.valor = valor;}

    public int getValor() {return valor;}

    public static List<String> getGenerosMusicais(){
        return Stream.of(GeneroMusical.values())
                .map(GeneroMusical::name)
                .sorted()
                .collect(Collectors.toList());
    }

}
