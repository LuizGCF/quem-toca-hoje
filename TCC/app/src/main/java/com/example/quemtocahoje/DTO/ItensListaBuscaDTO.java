package com.example.quemtocahoje.DTO;

import android.graphics.Bitmap;

public class
ItensListaBuscaDTO {
    String urlimagem;
    String nome;
    String descricao;

    public ItensListaBuscaDTO(String urlimagem, String nome, String descricao) {
        this.urlimagem = urlimagem;
        this.nome = nome;
        this.descricao = descricao;
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
