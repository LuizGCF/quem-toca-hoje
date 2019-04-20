package com.example.quemtocahoje.Persistencia.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "espectador"
        ,foreignKeys = @ForeignKey(entity = AutenticacaoEntity.class, parentColumns = "idAutenticacao", childColumns ="autenticacao_id"))
public class EspectadorEntity implements Serializable {

    @PrimaryKey
    private Long idEspectador;
    private Long autenticacao_id;

    private String nomeEspectador;
    private String emailEspectador;
    private String dataCriacao;

    public EspectadorEntity(Long autenticacao_id, String nomeEspectador, String emailEspectador, String dataCriacao) {
        this.autenticacao_id = autenticacao_id;
        this.nomeEspectador = nomeEspectador;
        this.emailEspectador = emailEspectador;
        this.dataCriacao = dataCriacao;
    }

    public String getEmailEspectador() {
        return emailEspectador;
    }

    public void setEmailEspectador(String emailEspectador) {
        this.emailEspectador = emailEspectador;
    }

    public Long getIdEspectador() {
        return idEspectador;
    }

    public void setIdEspectador(Long idEspectador) {
        this.idEspectador = idEspectador;
    }

    public Long getAutenticacao_id() {
        return autenticacao_id;
    }

    public void setAutenticacao_id(Long autenticacao_id) {
        this.autenticacao_id = autenticacao_id;
    }

    public String getNomeEspectador() {
        return nomeEspectador;
    }

    public void setNomeEspectador(String nomeEspectador) {
        this.nomeEspectador = nomeEspectador;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
