package com.example.quemtocahoje.DTO;

import android.graphics.Bitmap;

public class ItensListaBuscaDTO {
    Bitmap imagem;
    String nome;
    String descricao;

    public ItensListaBuscaDTO(Bitmap imagem, String nome, String descricao) {
        this.imagem = imagem;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
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
