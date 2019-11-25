package com.example.quemtocahoje.DTO;

import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoEstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoMusicoEntity;
import com.example.quemtocahoje.Persistencia.Entity.PropostaEntity;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class EventoDTO implements Serializable {
    String idEvento;
    AvaliacaoEstabelecimentoEntity avaliacaoEstab;
    AvaliacaoMusicoEntity avaliacaoMusico;
    PropostaEntity proposta;

    public EventoDTO(){}

    public EventoDTO(String idEvento, AvaliacaoEstabelecimentoEntity avaliacaoEstab, AvaliacaoMusicoEntity avaliacaoMusico, PropostaEntity proposta) {
        this.idEvento = idEvento;
        this.avaliacaoEstab = avaliacaoEstab;
        this.avaliacaoMusico = avaliacaoMusico;
        this.proposta = proposta;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public AvaliacaoEstabelecimentoEntity getAvaliacaoEstab() {
        return avaliacaoEstab;
    }

    public void setAvaliacaoEstab(AvaliacaoEstabelecimentoEntity avaliacaoEstab) {
        this.avaliacaoEstab = avaliacaoEstab;
    }

    public AvaliacaoMusicoEntity getAvaliacaoMusico() {
        return avaliacaoMusico;
    }

    public void setAvaliacaoMusico(AvaliacaoMusicoEntity avaliacaoMusico) {
        this.avaliacaoMusico = avaliacaoMusico;
    }

    public PropostaEntity getProposta() {
        return proposta;
    }

    public void setProposta(PropostaEntity proposta) {
        this.proposta = proposta;
    }

    @Override
    public String toString() {
        return "EventoDTO{" +
                "idEvento='" + idEvento + '\'' +
                ", avaliacaoEstab=" + avaliacaoEstab +
                ", avaliacaoMusico=" + avaliacaoMusico +
                ", proposta=" + proposta +
                '}';
    }
}
