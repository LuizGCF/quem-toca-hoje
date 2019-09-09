package com.example.quemtocahoje.Persistencia.Entity;



import java.io.Serializable;

public class EstabelecimentoEntity implements Serializable {

    private String idEstabelecimento;
    private String autenticacao_id;
    private String endereco_id;

    private String nomeDono;
    private String razaoSocial;
    private String cnpj;
    private String nomeFantasia;
    private String horaInicio;
    private String horaTermino;
    private String telefone;
    private String descricao;

    private String dataCriacao;


    public EstabelecimentoEntity(String autenticacao_id, String endereco_id, String nomeDono, String razaoSocial, String cnpj, String nomeFantasia, String horaInicio, String horaTermino, String telefone, String descricao, String dataCriacao) {
        this.autenticacao_id = autenticacao_id;
        this.endereco_id = endereco_id;
        this.nomeDono = nomeDono;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.telefone = telefone;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
    }

    public EstabelecimentoEntity(){}

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public String getAutenticacao_id() {
        return autenticacao_id;
    }

    public void setAutenticacao_id(String autenticacao_id) {
        this.autenticacao_id = autenticacao_id;
    }

    public String getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(String endereco_id) {
        this.endereco_id = endereco_id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
