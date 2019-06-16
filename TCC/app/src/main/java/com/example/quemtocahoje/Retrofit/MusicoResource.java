package com.example.quemtocahoje.Retrofit;

import com.example.quemtocahoje.POJO.Musico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MusicoResource {
    @GET("musico")
    Call<List<Musico>> getAllMusicos();
    @GET("musico/{id}")
    Call<Musico> getMusico(@Path("id") int id);
    @GET("musico/aut")
    Call<Musico> getMusicoByIdAutenticacao(@Query("id") int id);
}
