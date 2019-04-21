package com.example.quemtocahoje.Persistencia.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.quemtocahoje.Persistencia.Entity.EnderecoEntity;

import java.util.List;

@Dao
public interface EnderecoDao {

    @Insert
    public Long insertEndereco(EnderecoEntity enderecoEntity);

    @Update
    public int updateEndereco(EnderecoEntity enderecoEntity);

    @Delete
    public int deleteEndereco(EnderecoEntity enderecoEntity);

    //QUERIES
    @Query("SELECT * FROM endereco")
    public List<EnderecoEntity> findAllEnderecos();

    @Query("SELECT * FROM endereco WHERE idEndereco = :idEndereco")
    public EnderecoEntity findEnderecoById(Long idEndereco);
}
