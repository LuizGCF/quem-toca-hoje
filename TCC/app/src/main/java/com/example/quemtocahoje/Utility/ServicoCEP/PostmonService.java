package com.example.quemtocahoje.Utility.ServicoCEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostmonService
{
    @GET("{id}")
    Call<Endereco> getEndereco(@Path("id") String cep);
}
