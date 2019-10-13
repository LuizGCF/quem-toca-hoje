package com.example.quemtocahoje.Persistencia.Entity;


import java.io.Serializable;
import java.util.List;

public class MusicoEntity implements Serializable {

    //private Long idqualquer;

    private String idMusico;
    private String autenticacao_id;

    private String nome;
    private String nomeArtistico;
    private String telefone;
    private String dataCriacao;
    private String descricao;
    private String cidade;

    //usado só pra carregar os convites que ele possui, a tabela de convite possui o id fk do musico
    private List<String> convites;

    /*
    @Ignore
    private List<BandaEntity> bandas;
    */

    public MusicoEntity(String autenticacao_id, String nome, String nomeArtistico, String telefone, String dataCriacao, String descricao, String cidade) {
        this.autenticacao_id = autenticacao_id;
        this.nome = nome;
        this.nomeArtistico = nomeArtistico;
        this.telefone = telefone;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.cidade = cidade;
    }

    public MusicoEntity(){}

    public String getAutenticacao_id() {
        return autenticacao_id;
    }

    public void setAutenticacao_id(String autenticacao_id) {
        this.autenticacao_id = autenticacao_id;
    }

    /*public Long getIdqualquer() {
        return idqualquer;
    }

    public void setIdqualquer(Long idqualquer) {
        this.idqualquer = idqualquer;
    }*/

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

    public String getIdMusico() {
        return idMusico;
    }

    public void setIdMusico(String idMusico) {
        this.idMusico = idMusico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
