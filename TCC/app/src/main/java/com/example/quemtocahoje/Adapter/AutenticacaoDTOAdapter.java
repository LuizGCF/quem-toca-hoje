package com.example.quemtocahoje.Adapter;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;

public class AutenticacaoDTOAdapter {

    public static AutenticacaoDTO espectadorToAutenticacaoDTO(EspectadorEntity e, String email){
        AutenticacaoDTO dto = new AutenticacaoDTO();
        dto.setNome(e.getNomeEspectador());
        dto.setEmail(email);
        dto.setTipoUsuario(TipoUsuario.ESPECTADOR.name());
        dto.setIdAutenticacao(e.getAutenticacao_id());

        return dto;
    }

    public static AutenticacaoDTO musicoToAutenticacaoDTO(MusicoEntity e, String email){
        AutenticacaoDTO dto = new AutenticacaoDTO();
        dto.setNome(e.getNome());//nome musico ou artistico?
        dto.setEmail(email);
        dto.setTipoUsuario(TipoUsuario.MUSICO.name());
        dto.setIdAutenticacao(e.getAutenticacao_id());

        return dto;
    }

    public static AutenticacaoDTO estabelecimentoToAutenticacaoDTO(EstabelecimentoEntity e, String email){
        AutenticacaoDTO dto = new AutenticacaoDTO();
        dto.setNome(e.getNomeFantasia());//nome dono ou nome do estab?
        dto.setEmail(email);
        dto.setTipoUsuario(TipoUsuario.ESTABELECIMENTO.name());
        dto.setIdAutenticacao(e.getAutenticacao_id());

        return dto;
    }
}
