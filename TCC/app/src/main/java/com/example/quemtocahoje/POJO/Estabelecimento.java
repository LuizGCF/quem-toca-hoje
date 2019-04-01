package com.example.quemtocahoje.POJO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Estabelecimento extends Usuario {

    private Integer id;
    private String cnpj;
    private String razaoSocial;
    private String celular;
    private String nomeFantasia;
    private String descricao;

    private Date horarioAbertura;
    private Date horarioFechamento;
    private Date dataCriacao;

    private Endereco endereco;
    private List<Avaliacao> avaliacoes;
    private List<Arquivo> arquivos;
    private List<Proposta> propostas;

    public Estabelecimento(String nomeUsuario, Autenticacao autenticacao, Arquivo arquivo, Integer id, String cnpj, String razaoSocial, String celular, String nomeFantasia, String descricao, Date horarioAbertura, Date horarioFechamento, Date dataCriacao, Endereco endereco, List<Avaliacao> avaliacoes, List<Arquivo> arquivos, List<Proposta> propostas) {
        super(nomeUsuario, autenticacao, arquivo);
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.celular = celular;
        this.nomeFantasia = nomeFantasia;
        this.descricao = descricao;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.dataCriacao = dataCriacao;
        this.endereco = endereco;
        this.avaliacoes = avaliacoes;
        this.arquivos = arquivos;
        this.propostas = propostas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(Date horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public Date getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setHorarioFechamento(Date horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public List<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(List<Proposta> propostas) {
        this.propostas = propostas;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
