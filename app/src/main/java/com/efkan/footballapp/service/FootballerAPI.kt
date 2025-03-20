package com.efkan.footballapp.service

import com.efkan.footballapp.model.FootballerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballerAPI {
    //https://www.thesportsdb.com/api/v1/json/3/searchplayers.php?p=Raul_Meireles

    @GET("api/v1/json/3/searchplayers.php")
    suspend fun getData(@Query("p") playerName: String): FootballerResponse
}