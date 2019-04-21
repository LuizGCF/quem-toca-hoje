package com.example.quemtocahoje.Persistencia.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.quemtocahoje.Persistencia.Entity.ConviteEntity;

import java.util.List;

@Dao
public interface ConviteDao {

    @Insert
    public Long insertConvite(ConviteEntity conviteEntity);

    @Update
    public int updateConvite(ConviteEntity conviteEntity);

    @Delete
    public int deleteConvite(ConviteEntity conviteEntity);

    //QUERIES
    @Query("UPDATE convite SET statusConvite = :statusConvite WHERE idConvite = :idConvite")
    public int updateStatusConviteById(String statusConvite, Long idConvite);

    @Query("SELECT * FROM convite WHERE emailConvidado = :emailConvidado AND statusConvite = 'ABERTO'")
    public List<ConviteEntity> findAllConvitesAbertosByEmail(String emailConvidado);

}
