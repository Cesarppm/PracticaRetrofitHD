package com.example.practicaretrofithd.Interfaz;

import com.example.practicaretrofithd.Model.Juguetes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JuguestesApi {

    @GET("consultar.php")
    Call<List<Juguetes>> getJuguetes();

}
