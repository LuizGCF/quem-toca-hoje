package com.example.quemtocahoje.Persistencia.Entity;


import java.io.Serializable;
import java.util.Date;

public class PropostaEntity implements Serializable {

    private String idProposta;
    private String idBanda;
    private String idEstabelecimento;
    private String statusProposta;
    private String horarioInicio;
    private String horarioFim;
    private String local;
    private String descricao;
    private String dataEvento;
    private String dataEnvioProposta;
    private Double cache;

    public PropostaEntity(){}

    public PropostaEntity(String idBanda, String idEstabelecimento, String statusProposta, String horarioInicio, String horarioFim, String local, String descricao, Double cache, String dataEvento, String dataEnvioProposta) {
        this.idBanda = idBanda;
        this.idEstabelecimento = idEstabelecimento;
        this.statusProposta = statusProposta;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.local = local;
        this.descricao = descricao;
        this.cache = cache;
        this.dataEvento = dataEvento;
        this.dataEnvioProposta = dataEnvioProposta;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }

    public String getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(String idBanda) {
        this.idBanda = idBanda;
    }

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getStatusProposta() {
        return statusProposta;
    }

    public void setStatusProposta(String statusProposta) {
        this.statusProposta = statusProposta;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getCache() {
        return cache;
    }

    public void setCache(Double cache) {
        this.cache = cache;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDataEnvioProposta() {
        return dataEnvioProposta;
    }

    public void setDataEnvioProposta(String dataEnvioProposta) {
        this.dataEnvioProposta = dataEnvioProposta;
    }

    @Override
    public String toString() {
        return "PropostaEntity{" +
                "idProposta='" + idProposta + '\'' +
                ", idBanda='" + idBanda + '\'' +
                ", idEstabelecimento='" + idEstabelecimento + '\'' +
                ", statusProposta='" + statusProposta + '\'' +
                ", horarioInicio='" + horarioInicio + '\'' +
                ", horarioFim='" + horarioFim + '\'' +
                ", local='" + local + '\'' +
                ", descricao='" + descricao + '\'' +
                ", cache=" + cache +
                ", dataEvento=" + dataEvento +
                ", dataEnvioProposta=" + dataEnvioProposta +
                '}';
    }
}
