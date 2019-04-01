package com.example.quemtocahoje.POJO;

import com.example.quemtocahoje.Enum.TipoUsuario;

import java.io.Serializable;
import java.util.Date;

public class Avaliacao implements Serializable {

    private Integer id;
    private Integer idAvaliador;
    private Integer idAnalisado;
    private Double notaAvaliacao;
    private String descricao;

    private Date dataAvaliacao;
    private Date dataApresentacao;

    private TipoUsuario tipoUsuario;

    public Avaliacao(Integer id, Integer idAvaliador, Integer idAnalisado, Double notaAvaliacao, String descricao, Date dataAvaliacao, Date dataApresentacao, TipoUsuario tipoUsuario) {
        this.id = id;
        this.idAvaliador = idAvaliador;
        this.idAnalisado = idAnalisado;
        this.notaAvaliacao = notaAvaliacao;
        this.descricao = descricao;
        this.dataAvaliacao = dataAvaliacao;
        this.dataApresentacao = dataApresentacao;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAvaliador() {
        return idAvaliador;
    }

    public void setIdAvaliador(Integer idAvaliador) {
        this.idAvaliador = idAvaliador;
    }

    public Integer getIdAnalisado() {
        return idAnalisado;
    }

    public void setIdAnalisado(Integer idAnalisado) {
        this.idAnalisado = idAnalisado;
    }

    public Double getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(Double notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Date getDataApresentacao() {
        return dataApresentacao;
    }

    public void setDataApresentacao(Date dataApresentacao) {
        this.dataApresentacao = dataApresentacao;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
