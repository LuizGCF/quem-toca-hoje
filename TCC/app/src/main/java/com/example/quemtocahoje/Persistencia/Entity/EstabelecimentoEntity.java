package com.example.quemtocahoje.Persistencia.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


import java.io.Serializable;

@Entity(tableName = "estabelecimento"
        ,foreignKeys =
        {
         @ForeignKey(entity = AutenticacaoEntity.class, parentColumns = "idAutenticacao", childColumns = "autenticacao_id")
        ,@ForeignKey(entity = EnderecoEntity.class, parentColumns = "idEndereco", childColumns = "endereco_id")
        })
public class EstabelecimentoEntity implements Serializable {

    @PrimaryKey
    private Long idEstabelecimento;
    private Long autenticacao_id;
    private Long endereco_id;

    private String razaoSocial;
    private String cnpj;
    private String nomeFantasia;
    private String horaInicio;
    private String horaTermino;
    private String telefone;
    private String descricao;
    private String tipoUsuario;


    public EstabelecimentoEntity(Long autenticacao_id, Long endereco_id, String razaoSocial, String cnpj, String nomeFantasia, String horaInicio, String horaTermino, String telefone, String descricao, String tipoUsuario) {
        this.autenticacao_id = autenticacao_id;
        this.endereco_id = endereco_id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.telefone = telefone;
        this.descricao = descricao;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(Long idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public Long getAutenticacao_id() {
        return autenticacao_id;
    }

    public void setAutenticacao_id(Long autenticacao_id) {
        this.autenticacao_id = autenticacao_id;
    }

    public Long getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(Long endereco_id) {
        this.endereco_id = endereco_id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
