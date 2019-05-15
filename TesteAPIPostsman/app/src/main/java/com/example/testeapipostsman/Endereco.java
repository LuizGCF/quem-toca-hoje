package com.example.testeapipostsman;



public class Endereco {

    private String bairro;
    private String cidade;
    private String logradouro;
    private String estado;

    public Endereco(String bairro, String cidade, String logradouro) {
        this.bairro = bairro;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getEstado() {  return estado; }

    public void setEstado(String estado) {   this.estado = estado;}
}
