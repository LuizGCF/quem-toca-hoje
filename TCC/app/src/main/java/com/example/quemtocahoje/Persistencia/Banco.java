package com.example.quemtocahoje.Persistencia;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.quemtocahoje.Persistencia.Dao.ArquivoDao;
import com.example.quemtocahoje.Persistencia.Dao.AutenticacaoDao;
import com.example.quemtocahoje.Persistencia.Dao.EnderecoDao;
import com.example.quemtocahoje.Persistencia.Dao.EspectadorDao;
import com.example.quemtocahoje.Persistencia.Dao.EstabelecimentoDao;
import com.example.quemtocahoje.Persistencia.Dao.MusicoDao;
import com.example.quemtocahoje.Persistencia.Dao.TokenDao;
import com.example.quemtocahoje.Persistencia.Entity.ArquivoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EnderecoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Persistencia.Entity.TokenEntity;

@Database(entities = {EspectadorEntity.class
        , AutenticacaoEntity.class
        , TokenEntity.class
        , ArquivoEntity.class
        , EnderecoEntity.class
        , EstabelecimentoEntity.class
        , MusicoEntity.class}
        , version = 1)
public abstract class Banco extends RoomDatabase {

    private static Banco INSTANCIA;

    public abstract EspectadorDao espectadorDao();
    public abstract AutenticacaoDao autenticacaoDao();
    public abstract TokenDao tokenDao();
    public abstract ArquivoDao arquivoDao();
    public abstract EnderecoDao enderecoDao();
    public abstract EstabelecimentoDao estabelecimentoDao();
    public abstract MusicoDao musicoDao();

    public static Banco getDatabase(Context context) {
        if (INSTANCIA == null) {
            INSTANCIA =
                    Room.databaseBuilder(context.getApplicationContext(), Banco.class, "tcc-database")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCIA;
    }

    public static void destruirInstancia() {
        INSTANCIA = null;
    }
}
