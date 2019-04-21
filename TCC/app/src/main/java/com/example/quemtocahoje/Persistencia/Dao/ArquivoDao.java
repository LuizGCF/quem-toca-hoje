package com.example.quemtocahoje.Persistencia.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.quemtocahoje.Persistencia.Entity.ArquivoEntity;

import java.sql.Blob;

@Dao
public interface ArquivoDao {

    @Insert
    public Long insertArquivo(ArquivoEntity arquivoEntity);

    @Update
    public int updateArquivo(ArquivoEntity arquivoEntity);

    @Delete
    public  int deleteteArquivo(ArquivoEntity arquivoEntity);

    //QUERIES
    @Query("UPDATE arquivo SET anexoArquivo = :anexoArquivo WHERE autenticacao_id = :autenticacao_id AND tipoArquivo = :tipoArquivo")
    public int updateArquivoByAutenticacaoId(byte[] anexoArquivo, Long autenticacao_id, String tipoArquivo);

    @Query("SELECT anexoArquivo FROM arquivo WHERE autenticacao_id = :idUser AND tipoArquivo = :tipoArquivo")
    public byte[] findAnexoArquivoById(Long idUser, String tipoArquivo);
}
