package com.example.quemtocahoje.Persistencia.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;

import java.util.List;

@Dao
public interface MusicoDao {

    @Insert
    public Long insertMusico(MusicoEntity musicoEntity);

    @Update
    public int updateMusico(MusicoEntity musicoEntity);

    @Delete
    public int deleteMusico(MusicoEntity musicoEntity);

    //QUERIES
    @Query("SELECT * FROM musico")
    public List<MusicoEntity> findAllMusicos();

    @Query("SELECT * FROM musico  WHERE idMusico = :idMusico")
    public MusicoEntity findMusicoById(Long idMusico);

    @Query("SELECT * FROM musico WHERE autenticacao_id = :autenticacao_id")
    public MusicoEntity findMusicoByAutenticacao(Long autenticacao_id);
}
