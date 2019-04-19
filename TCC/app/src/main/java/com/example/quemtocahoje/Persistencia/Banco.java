package com.example.quemtocahoje.Persistencia;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.quemtocahoje.Persistencia.Dao.AutenticacaoDao;
import com.example.quemtocahoje.Persistencia.Dao.EspectadorDao;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;

@Database(entities = {EspectadorEntity.class, AutenticacaoEntity.class}, version = 1)
public abstract class Banco extends RoomDatabase {

    private static Banco INSTANCIA;

    public abstract EspectadorDao espectadorDao();
    public abstract AutenticacaoDao autenticacaoDao();

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
