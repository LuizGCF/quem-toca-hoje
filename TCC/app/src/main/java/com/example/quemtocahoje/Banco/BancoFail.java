package com.example.quemtocahoje.Banco;

import android.database.Cursor;
import android.database.sqlite.*;

import com.example.quemtocahoje.POJO.Espectador;

public class BancoFail {//NAO FUNCIONOU FEELSBAD
    private SQLiteDatabase banco;
    public BancoFail()
    {
         //banco = SQLiteDatabase.openOrCreateDatabase("BancoQuemTocaHoje",MODE_PRIVATE,null);
    }

    public void CriarTabelasBanco()
    {
        banco.execSQL("CREATE TABLE IF NOT EXISTS Espectador(idEspectador INTEGER PRIMARY KEY AUTOINCREMENT, NomeEspectador VARCHAR(200), DataCriacao DATETIME, TipoUsuario VARCHAR(50),idAutenticacao INTEGER)");
    }

    public void InserirDadosTest()
    {
        banco.execSQL("INSERT INTO Espectador values('Gabriel',null,'Espectador',0)");
        banco.execSQL("INSERT INTO Espectador values('Matheus',null,'Espectador',0)");
    }

    public Object Selecionar(int id)
    {
        Cursor cursor = banco.rawQuery("SELECT * FROM Espectador where idEspectador = " + id,null);

        cursor.moveToFirst();
        Espectador espectador = new Espectador();

        while(cursor != null)
        {
            espectador.setIdEspectador(Integer.parseInt(cursor.getString(cursor.getColumnIndex("idEspectador"))));
            espectador.setNomeEspectador(cursor.getString(cursor.getColumnIndex("NomeEspectador")));
            //espectador.setDataCriacao(cursor.getDate);//nao sei, passei reto
            espectador.setTipoUsuario(cursor.getString((cursor.getColumnIndex("TipoUsuario"))));
        }

        return espectador;
    }
}

