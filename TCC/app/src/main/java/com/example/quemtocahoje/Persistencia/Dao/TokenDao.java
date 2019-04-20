package com.example.quemtocahoje.Persistencia.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.quemtocahoje.Persistencia.Entity.TokenEntity;

@Dao
public interface TokenDao {

    @Insert
    public Long insertToken(TokenEntity tokenEntity);

    @Update
    public int updateToken(TokenEntity tokenEntity);

    @Delete
    public int deleteToken(TokenEntity tokenEntity);

    //QUERIES
    @Query("SELECT autenticacao_id from token where token = :token")
    public Long findAutenticacaoIdByToken(String token);

    @Query("UPDATE token SET status = :status WHERE token = :token AND status='ABERTO'")
    public int updateTokenValido(String status, String token);

    @Query("UPDATE token set token = :token WHERE email = :email")
    public int updateTokenReenviado(String token, String email);

}
