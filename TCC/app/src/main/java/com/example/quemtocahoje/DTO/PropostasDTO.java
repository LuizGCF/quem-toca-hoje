package com.example.quemtocahoje.DTO;

public class PropostasDTO {
    private String idProposta;
    private String estabelecimento;
    private String periodo;//"20/11/2019  11:50 às 13:47";

    public PropostasDTO(String idProposta, String estabelecimento, String periodo) {
        this.idProposta = idProposta;
        this.estabelecimento = estabelecimento;
        this.periodo = periodo;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
