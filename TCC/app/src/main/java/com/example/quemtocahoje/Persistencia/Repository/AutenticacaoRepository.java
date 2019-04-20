//package com.example.quemtocahoje.Persistencia.Repository;
//
//import android.app.Application;
//import android.os.AsyncTask;
//
//import com.example.quemtocahoje.Persistencia.Banco;
//import com.example.quemtocahoje.Persistencia.Dao.AutenticacaoDao;
//import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
//
//public class AutenticacaoRepository {
//    private Banco db;
//    private AutenticacaoDao dao;
//
//    public AutenticacaoRepository(Application application){
//        db = Banco.getDatabase(application);
//    }
//
//    public void insert(AutenticacaoEntity autenticacaoEntity){
//        new insertAsyncTask(dao).execute(autenticacaoEntity);
//    }
//
//    public String toString(AutenticacaoEntity a){
//        return "ID: "+a.getIdAutenticacao()
//            +"\nLogin: " + a.getLogin()
//            +"\nSenha: " + a.getSenha()
//            +"\nTipo Usuário: " + a.getTipoUsuario()
//            +"\nData Criação: " + a.getDataCriacao()
//            +"\nÚltimo Login:" + a.getDataUltimoLogin();
//    }
//
//
//    private static class insertAsyncTask extends AsyncTask<AutenticacaoEntity, Void, Void> {
//
//        private AutenticacaoDao mAsyncTaskDao;
//
//        insertAsyncTask(AutenticacaoDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final AutenticacaoEntity... params) {
//            mAsyncTaskDao.insertAutenticacao(params[0]);
//            return null;
//        }
//    }
//}
