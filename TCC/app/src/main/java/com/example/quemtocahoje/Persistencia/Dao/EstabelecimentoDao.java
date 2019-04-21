package com.example.quemtocahoje.Persistencia.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;

import java.util.List;

@Dao
public interface EstabelecimentoDao {

    @Insert
    public Long insertEstabelecimento(EstabelecimentoEntity estabelecimentoEntity);

    @Update
    public int updateEstabelecimento(EstabelecimentoEntity estabelecimentoEntity);

    @Delete
    public int deleteEstabelecimento(EstabelecimentoEntity estabelecimentoEntity);

    //QUERIES
    @Query("SELECT * FROM estabelecimento")
    public List<EstabelecimentoEntity> findAllEstabelecimentos();

    @Query("SELECT * FROM estabelecimento WHERE idEstabelecimento = :idEstabelecimento")
    public EstabelecimentoEntity findEstabelecimentoById(Long idEstabelecimento);

    @Query("SELECT * FROM estabelecimento WHERE cnpj = :cnpj")
    public EstabelecimentoEntity findEstabelecimentoByCNPJ(String cnpj);

}
