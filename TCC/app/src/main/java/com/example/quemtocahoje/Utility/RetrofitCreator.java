package com.example.quemtocahoje.Utility;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {
    private static Retrofit retrofit;

    private static final String BASE_URL = "http://192.168.1.149:55555/api/";//url do endereço local da sua maquina e a porta configuradas no iis
    public static Retrofit criarRetrofit(){//criacao
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl((BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }


}
