package com.example.quemtocahoje.POJO;

import com.example.quemtocahoje.Enum.TipoArquivo;
import com.example.quemtocahoje.Enum.TipoUsuario;

import java.io.Serializable;
import java.util.Date;

public class Arquivo implements Serializable {

    private Integer id;
    private Integer idUsuario;
    private TipoUsuario tipoUsuario;
    private Byte[] arquivo;
    private TipoArquivo tipoArquivo;
    private Date dataCriacao;

    public Arquivo(Integer idUsuario, TipoUsuario tipoUsuario, Byte[] arquivo, TipoArquivo tipoArquivo, Date dataCriacao) {
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.arquivo = arquivo;
        this.tipoArquivo = tipoArquivo;
        this.dataCriacao = dataCriacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(Byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public TipoArquivo getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(TipoArquivo tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
