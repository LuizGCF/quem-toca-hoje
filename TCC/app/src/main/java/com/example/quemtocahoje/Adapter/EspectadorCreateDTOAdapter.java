package com.example.quemtocahoje.Adapter;

import com.example.quemtocahoje.DTO.EspectadorCreateDTO;
import com.example.quemtocahoje.POJO.Espectador;

public class EspectadorCreateDTOAdapter {

    public static EspectadorCreateDTO toEspectadorCreateDTO(Espectador espectador){
        return new EspectadorCreateDTO(
                espectador.getIdAutenticacao()
                ,espectador.getNomeEspectador()
        );
    }
}
