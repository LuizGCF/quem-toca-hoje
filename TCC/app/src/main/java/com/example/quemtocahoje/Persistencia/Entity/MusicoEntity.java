package com.example.quemtocahoje.Persistencia.Entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "musico"
        ,foreignKeys = @ForeignKey(entity = AutenticacaoEntity.class, parentColumns = "idAutenticacao", childColumns = "autenticacao_id"))
public class MusicoEntity implements Serializable {

    @PrimaryKey
    private Long idMusico;
    private Long autenticacao_id;

    private String nome;
    private String nomeArtistico;
    private String telefone;
    private String dataCriacao;

    //usado só pra carregar os convites que ele possui, a tabela de convite possui o id fk do musico
    @Ignore
    private List<String> convites;

    /*
    @Ignore
    private List<BandaEntity> bandas;
    */

    public MusicoEntity(Long autenticacao_id, String nome, String nomeArtistico, String telefone, String dataCriacao) {
        this.autenticacao_id = autenticacao_id;
        this.nome = nome;
        this.nomeArtistico = nomeArtistico;
        this.telefone = telefone;
        this.dataCriacao = dataCriacao;
    }

    public Long getAutenticacao_id() {
        return autenticacao_id;
    }

    public void setAutenticacao_id(Long autenticacao_id) {
        this.autenticacao_id = autenticacao_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<String> getConvites() {
        return convites;
    }

    public void setConvites(List<String> convites) {
        this.convites = convites;
    }

    public Long getIdMusico() {
        return idMusico;
    }

    public void setIdMusico(Long idMusico) {
        this.idMusico = idMusico;
    }
}
