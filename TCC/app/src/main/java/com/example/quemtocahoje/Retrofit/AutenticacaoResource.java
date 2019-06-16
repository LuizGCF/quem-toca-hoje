package com.example.quemtocahoje.Retrofit;

import com.example.quemtocahoje.DTO.AutenticacaoCreateDTO;
import com.example.quemtocahoje.POJO.Autenticacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AutenticacaoResource {
    @GET("autenticacao")
    Call<List<Autenticacao>> getAllAutenticacoes();
    @GET("autenticacao/{id}")
    Call<Autenticacao> getAutenticacao(@Path("id") int id);
    @GET("autenticacao/login")
    Call<Autenticacao> getAutenticaoLogin(@Query("login") String login, @Query("senha") String senha);
    @POST("autenticacao")
    Call<Autenticacao> createAutenticacao(@Body AutenticacaoCreateDTO autenticacao);
}
