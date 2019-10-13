package com.example.quemtocahoje.Persistencia.Entity;

import android.widget.EditText;
import android.widget.RatingBar;

public class AvaliacaoMusicoEntity
{
    private String idAvaliacaoMusico;
    private String rbPerformance;
    private String rbEstilo;
    private String rbMusicalidade;
    private String txtComentario;
    private String estilo;
    private String musicalidade;
    private String performance;

    public AvaliacaoMusicoEntity(String idAvaliacaoMusico, String rbPerformance, String rbEstilo, String rbMusicalidade, String txtComentario, String estilo, String musicalidade, String performance) {
        this.idAvaliacaoMusico = idAvaliacaoMusico;
        this.rbPerformance = rbPerformance;
        this.rbEstilo = rbEstilo;
        this.rbMusicalidade = rbMusicalidade;
        this.txtComentario = txtComentario;
        this.estilo = estilo;
        this.musicalidade = musicalidade;
        this.performance = performance;
    }

    public String getIdAvaliacaoMusico() {
        return idAvaliacaoMusico;
    }

    public void setIdAvaliacaoMusico(String idAvaliacaoMusico) {
        this.idAvaliacaoMusico = idAvaliacaoMusico;
    }

    public String getRbPerformance() {
        return rbPerformance;
    }

    public void setRbPerformance(String rbPerformance) {
        this.rbPerformance = rbPerformance;
    }

    public String getRbEstilo() {
        return rbEstilo;
    }

    public void setRbEstilo(String rbEstilo) {
        this.rbEstilo = rbEstilo;
    }

    public String getRbMusicalidade() {
        return rbMusicalidade;
    }

    public void setRbMusicalidade(String rbMusicalidade) {
        this.rbMusicalidade = rbMusicalidade;
    }

    public String getTxtComentario() {
        return txtComentario;
    }

    public void setTxtComentario(String txtComentario) {
        this.txtComentario = txtComentario;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getMusicalidade() {
        return musicalidade;
    }

    public void setMusicalidade(String musicalidade) {
        this.musicalidade = musicalidade;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }
}
