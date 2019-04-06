package com.example.quemtocahoje.Utility;

import java.util.Random;

public class Token {

    private static final Random RANDOM = new Random();
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    //Retorna uma String de 6 caracteres aleatórios da constante CHARS
    public static String gerarToken(){
        StringBuilder token = new StringBuilder(6);
        for(int i = 0; i < 6; i++){
            token.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return token.toString();

    }
}
