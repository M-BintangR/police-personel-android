package com.example.siantik.database

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface LokasiApi {

    @GET("lokasi/{id}")
    fun getLokasiById(@Path("id") id: Int): Call<LokasiResponse>

    @PUT("lokasi/{id}")
    fun updateLokasi(
        @Path("id") id: Int,
        @Query("nama") nama: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("radius") radius: Int
    ): Call<ResponseMessage>
}
