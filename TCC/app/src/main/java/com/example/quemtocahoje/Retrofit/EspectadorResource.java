package com.example.quemtocahoje.Retrofit;

import com.example.quemtocahoje.DTO.EspectadorCreateDTO;
import com.example.quemtocahoje.POJO.Espectador;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EspectadorResource {
    @GET("espectador")
    Call<List<Espectador>> getAllEspectadores();
    @GET("espectador/{id}")
    Call<Espectador> getEspectador(@Path("id") int id);
    @GET("espectador/aut")
    Call<Espectador> geEspectadorByIdAutenticacao(@Query("id") int id);
    @POST("espectador")
    Call<Espectador> createEspectador(@Body EspectadorCreateDTO espectador);
}
