package com.example.quemtocahoje.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;

public class Mensagem {

    public static void notificar(Context contexto, String titulo, String mensagem){
        final AlertDialog.Builder caixa = new AlertDialog.Builder(contexto);
        caixa.setTitle(titulo);
        caixa.setMessage(mensagem);
        caixa.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        caixa.create().show();
    }

    public static void notificarFecharAtividade(Context contexto, String titulo, String mensagem){
        final AlertDialog.Builder caixa = new AlertDialog.Builder(contexto);
        caixa.setTitle(titulo);
        caixa.setMessage(mensagem);
        caixa.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Activity) contexto).finish();
            }
        });
        caixa.create().show();
    }
}
