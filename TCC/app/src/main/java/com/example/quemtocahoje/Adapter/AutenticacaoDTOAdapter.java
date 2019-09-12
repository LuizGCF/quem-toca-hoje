package com.example.quemtocahoje.Adapter;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;

public class AutenticacaoDTOAdapter {

    public static AutenticacaoDTO espectadorToAutenticacaoDTO(EspectadorEntity e){
        AutenticacaoDTO dto = new AutenticacaoDTO();
        dto.setNome(e.getNomeEspectador());
        dto.setTipoUsuario(TipoUsuario.ESPECTADOR.name());
        dto.setIdAutenticacao(e.getAutenticacao_id());

        return dto;
    }
}
