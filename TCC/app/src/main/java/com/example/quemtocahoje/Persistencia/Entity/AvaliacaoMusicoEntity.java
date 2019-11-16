package com.example.quemtocahoje.Persistencia.Entity;



import java.io.Serializable;

public class AvaliacaoMusicoEntity implements Serializable
{
    private String idBanda;
    private String idEvento;
    private String txtComentario;

    // Caras que vão receber os dados do Rating bar
    private float estilo;
    private float musicalidade;
    private float performance;


    public AvaliacaoMusicoEntity(){}

    public String getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(String idBanda) {
        this.idBanda = idBanda;
    }


    public String getTxtComentario() {
        return txtComentario;
    }

    public void setTxtComentario(String txtComentario) {
        this.txtComentario = txtComentario;
    }

    public float getEstilo() {
        return estilo;
    }

    public void setEstilo(float estilo) {
        this.estilo = estilo;
    }

    public float getMusicalidade() {
        return musicalidade;
    }

    public void setMusicalidade(float musicalidade) {
        this.musicalidade = musicalidade;
    }

    public float getPerformance() {
        return performance;
    }

    public void setPerformance(float performance) {
        this.performance = performance;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }


}
