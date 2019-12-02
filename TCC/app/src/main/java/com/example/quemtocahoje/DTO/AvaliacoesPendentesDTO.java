package com.example.quemtocahoje.DTO;

import java.io.Serializable;

public class AvaliacoesPendentesDTO implements Serializable {
    private String id;
    private String nome;
    private String data;

    public AvaliacoesPendentesDTO(String id, String nome, String data) {
        this.id = id;
        this.nome = nome;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AvaliacoesPendentesDTO{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
