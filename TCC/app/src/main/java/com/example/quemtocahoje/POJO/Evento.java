package com.example.quemtocahoje.POJO;

import com.example.quemtocahoje.Enum.StatusProposta;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Evento implements Serializable {

    private Integer idBanda;
    private Date inicioEvento;
    private Date fimEvento;

    private StatusProposta statusProposta;
    private Proposta proposta;
    private List<Avaliacao> avaliacoes;

    public Evento(Integer idBanda, Date inicioEvento, Date fimEvento, StatusProposta statusProposta, Proposta proposta, List<Avaliacao> avaliacoes) {
        this.idBanda = idBanda;
        this.inicioEvento = inicioEvento;
        this.fimEvento = fimEvento;
        this.statusProposta = statusProposta;
        this.proposta = proposta;
        this.avaliacoes = avaliacoes;
    }

    public Integer getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(Integer idBanda) {
        this.idBanda = idBanda;
    }

    public Date getInicioEvento() {
        return inicioEvento;
    }

    public void setInicioEvento(Date inicioEvento) {
        this.inicioEvento = inicioEvento;
    }

    public Date getFimEvento() {
        return fimEvento;
    }

    public void setFimEvento(Date fimEvento) {
        this.fimEvento = fimEvento;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
