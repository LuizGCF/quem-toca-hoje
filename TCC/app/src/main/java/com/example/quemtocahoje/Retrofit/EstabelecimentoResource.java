package com.example.quemtocahoje.Retrofit;

import com.example.quemtocahoje.POJO.Estabelecimento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EstabelecimentoResource {
    @GET("estabelecimento")
    Call<List<Estabelecimento>> getAllEstabelecimentos();
    @GET("estabelecimento/{id}")
    Call<Estabelecimento> getEstabelecimento(@Path("id") int id);
    @GET("estabelecimento/aut")
    Call<Estabelecimento> getEstabelecimentoByIdAutenticacao(@Query("id") int id);
}
