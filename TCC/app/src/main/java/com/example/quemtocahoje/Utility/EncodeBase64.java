package com.example.quemtocahoje.Utility;

import android.util.Base64;

public class EncodeBase64 {

    public static String toBase64(String texto){
        return Base64.encodeToString(texto.getBytes(),Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String fromBase64(String codificado){
        return new String (Base64.decode(codificado, Base64.DEFAULT));
    }
}
