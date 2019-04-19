package com.example.quemtocahoje.Persistencia.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;

@Dao
public interface EspectadorDao {

    @Insert
    public Long insertEspectador(EspectadorEntity espectadorEntity);

    @Update
    public int updateEspectador(EspectadorEntity espectadorEntity);

    @Delete
    public int deleteEspectador(EspectadorEntity espectadorEntity);

    //QUERIES
    @Query("SELECT * FROM espectador")
    public LiveData<EspectadorEntity[]> findAllEspectadores();

    //para parâmetros, usar : seguido do nome do parâmetro
    @Query("SELECT * FROM espectador WHERE idEspectador = :id")
    public LiveData<EspectadorEntity> findEspectadorById(Long id);
}
