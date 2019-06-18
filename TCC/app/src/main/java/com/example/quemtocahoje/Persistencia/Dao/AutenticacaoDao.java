package com.example.quemtocahoje.Persistencia.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;

@Dao
public interface AutenticacaoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
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

    @Query("SELECT * FROM autenticacao WHERE idAutenticacao = :id")
    public AutenticacaoEntity findAutenticacaoId(Long id);

    @Query("SELECT idAutenticacao FROM autenticacao WHERE email = :emailEspec OR login = :loginEspec")
    public AutenticacaoEntity findAutenticacaoByEmailLogin(String emailEspec, String loginEspec);

    @Query("SELECT idAutenticacao FROM autenticacao WHERE email = :emailEspec")
    public Long findAutenticacaoByEmail(String emailEspec);

    @Query("UPDATE autenticacao SET dataUltimoLogin = :data WHERE idAutenticacao = :id")
    public int updateDataUltimoLogin(String data, Long id);

    @Query("UPDATE autenticacao SET senha = :senha WHERE idAutenticacao = :id")
    public void updateSenhaById(String senha, Long id);

    @Query("SELECT idAutenticacao, tipoUsuario FROM autenticacao WHERE (email = :email OR login = :login) AND senha = :senha")
    public AutenticacaoDTO findAutenticacaoByLoginOuEmailESenha(String email, String login, String senha);
}
