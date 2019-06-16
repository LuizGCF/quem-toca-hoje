package com.example.quemtocahoje.Adapter;

import com.example.quemtocahoje.DTO.AutenticacaoCreateDTO;
import com.example.quemtocahoje.POJO.Autenticacao;

public class AutenticacaoCreateDTOAdapter {

    public static AutenticacaoCreateDTO toAutenticacaoCreateDTO(Autenticacao autenticacao) {
            AutenticacaoCreateDTO retorno = new AutenticacaoCreateDTO(
                    autenticacao.getEmailAutenticacao(),
                    autenticacao.getRegistro(),
                    autenticacao.getLoginAutenticacao(),
                    autenticacao.getSenhaAutenticacao(),
                    autenticacao.getTipoUsuarioAutenticacao()
            );

            return retorno;
        }
}
