package com.example.quemtocahoje.Persistencia.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Blob;

@Entity(tableName = "arquivo",
        foreignKeys = @ForeignKey(entity = AutenticacaoEntity.class, parentColumns = "idAutenticacao", childColumns = "autenticacao_id"))
public class ArquivoEntity implements Serializable {

    @PrimaryKey
    private Long idArquivo;
    private Long autenticacao_id;

    private byte[] anexoArquivo;
    private String tipoArquivo;
    private String dataCriacao;

    public ArquivoEntity(Long autenticacao_id, byte[] anexoArquivo, String tipoArquivo, String dataCriacao) {
        this.autenticacao_id = autenticacao_id;
        this.anexoArquivo = anexoArquivo;
        this.tipoArquivo = tipoArquivo;
        this.dataCriacao = dataCriacao;
    }

    public Long getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(Long idArquivo) {
        this.idArquivo = idArquivo;
    }

    public Long getAutenticacao_id() {
        return autenticacao_id;
    }

    public void setAutenticacao_id(Long autenticacao_id) {
        this.autenticacao_id = autenticacao_id;
    }

    public byte[] getAnexoArquivo() {
        return anexoArquivo;
    }

    public void setAnexoArquivo(byte[] anexoArquivo) {
        this.anexoArquivo = anexoArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
