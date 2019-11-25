package com.example.quemtocahoje.DTO;

import android.graphics.Bitmap;

public class
ItensListaBuscaDTO {
    String id;
    String urlimagem;
    String nome;
    String descricao;
    String tipoUsuario;

    public ItensListaBuscaDTO(String urlimagem,String id, String nome, String descricao, String tipoUsuario) {
        this.urlimagem = urlimagem;
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipoUsuario = tipoUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getImagem() {
        return urlimagem;
    }

    public void setImagem(String imagem) {
        this.urlimagem = urlimagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
