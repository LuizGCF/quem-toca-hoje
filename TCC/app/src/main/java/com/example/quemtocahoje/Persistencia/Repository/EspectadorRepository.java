//package com.example.quemtocahoje.Persistencia.Repository;
//
//import android.content.Context;
//
//import com.example.quemtocahoje.Persistencia.Banco;
//import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
//
//public class EspectadorRepository {
//
//    private Banco db;
//
//    public EspectadorRepository(Context context) {
//        db = Banco.getDatabase(context);
//    }
//
//    public Long insertEspectador(EspectadorEntity e){
//        return db.espectadorDao().insertEspectador(e);
//    }
//
//    public EspectadorEntity findEspectadorById(Long id){
//        return db.espectadorDao().findEspectadorById(id);
//    }
//
//    public String toString(EspectadorEntity e){
//        return "ID: " + e.getIdEspectador()
//                +"\nNome: "+ e.getNomeEspectador()
//                +"\nAutenticação: "+ e.getAutenticacao_id()
//                +"\nData Criação: " + e.getDataCriacao();
//    }
//}
