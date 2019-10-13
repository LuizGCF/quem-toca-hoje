package com.example.quemtocahoje.Persistencia.Entity;

import android.widget.EditText;
import android.widget.RatingBar;

import java.io.Serializable;

public class AvaliacaoEstabelecimentoEntity implements Serializable
{
    private String idAvaliacaoEstabelecimento;
    private RatingBar rbOrganizacao;
    private  RatingBar  rbEstrutura;
    private  RatingBar  rbReceptividade;
    private EditText txtComentario;

    public AvaliacaoEstabelecimentoEntity(RatingBar rbOrganizacao, RatingBar rbEstrutura, RatingBar rbReceptividade, EditText txtComentario) {
        this.rbOrganizacao = rbOrganizacao;
        this.rbEstrutura = rbEstrutura;
        this.rbReceptividade = rbReceptividade;
        this.txtComentario = txtComentario;
    }

    public RatingBar getRbOrganizacao() {
        return rbOrganizacao;
    }

    public void setRbOrganizacao(RatingBar rbOrganizacao) {
        this.rbOrganizacao = rbOrganizacao;
    }

    public RatingBar getRbEstrutura() {
        return rbEstrutura;
    }

    public void setRbEstrutura(RatingBar rbEstrutura) {
        this.rbEstrutura = rbEstrutura;
    }

    public RatingBar getRbReceptividade() {
        return rbReceptividade;
    }

    public void setRbReceptividade(RatingBar rbReceptividade) {
        this.rbReceptividade = rbReceptividade;
    }

    public EditText getTxtComentario() {
        return txtComentario;
    }

    public void setTxtComentario(EditText txtComentario) {
        this.txtComentario = txtComentario;
    }
}
