package com.example.quemtocahoje.POJO;

import com.example.quemtocahoje.Enum.StatusProposta;

import java.io.Serializable;
import java.util.Date;

public class Proposta implements Serializable {

    private Integer id;
    private Integer idEstabelecimento;
    private Integer idBanda;
    private String descricao;
    private Double valorProposta;
    private Endereco endereco;

    public Proposta(Integer id, Integer idEstabelecimento, Integer idBanda, String descricao, Double valorProposta, Endereco endereco) {
        this.id = id;
        this.idEstabelecimento = idEstabelecimento;
        this.idBanda = idBanda;
        this.descricao = descricao;
        this.valorProposta = valorProposta;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(Integer idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public Integer getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(Integer idBanda) {
        this.idBanda = idBanda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorProposta() {
        return valorProposta;
    }

    public void setValorProposta(Double valorProposta) {
        this.valorProposta = valorProposta;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}

