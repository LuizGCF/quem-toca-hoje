package com.example.quemtocahoje.Persistencia.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;

@Dao
public interface AutenticacaoDao {

    @Insert
    public Long insertAutenticacao(AutenticacaoEntity autenticacaoEntity);

    @Update
    public int updateAutenticacao(AutenticacaoEntity autenticacaoEntity);

    @Delete
    public int deleteAutenticacao(AutenticacaoEntity autenticacaoEntity);

    //QUERIES
    @Query("SELECT * FROM autenticacao")
    public LiveData<AutenticacaoEntity[]> findAllAutenticacoes();

    @Query("SELECT * FROM autenticacao WHERE idAutenticacao = :id")
    public LiveData<AutenticacaoEntity> findAutenticacaoById(Long id);

    @Query("SELECT idAutenticacao FROM autenticacao WHERE login = :loginEspec")
    public AutenticacaoEntity findAutenticacaoByLogin(String loginEspec);

    @Query("SELECT idAutenticacao, tipoUsuario FROM autenticacao WHERE login = :login AND senha = :senha")
    public AutenticacaoDTO findAutenticacaoByLoginSenha(String login, String senha);

    @Query("UPDATE autenticacao SET dataUltimoLogin = :data WHERE idAutenticacao = :id")
    public int updateDataUltimoLogin(String data, Long id);
}
