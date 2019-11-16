package com.example.quemtocahoje.Persistencia.Entity;

import android.widget.EditText;
import android.widget.RatingBar;

import java.io.Serializable;

public class AvaliacaoEstabelecimentoEntity implements Serializable
{

    private String idEstabelecimento;
    private String idEvento;
    private String txtComentario;

    // Caras que vão receber os dados do Rating bar
    private float organizacao;
    private  float  estrutura;
    private  float  receptividade;

    public AvaliacaoEstabelecimentoEntity(){}

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getTxtComentario() {
        return txtComentario;
    }

    public void setTxtComentario(String txtComentario) {
        this.txtComentario = txtComentario;
    }

    public float getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(float organizacao) {
        this.organizacao = organizacao;
    }

    public float getEstrutura() {
        return estrutura;
    }

    public void setEstrutura(float estrutura) {
        this.estrutura = estrutura;
    }

    public float getReceptividade() {
        return receptividade;
    }

    public void setReceptividade(float receptividade) {
        this.receptividade = receptividade;
    }
}
