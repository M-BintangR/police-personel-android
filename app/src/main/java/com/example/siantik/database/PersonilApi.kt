package com.example.siantik.database

import retrofit2.Call
import retrofit2.http.*

interface PersonilApi {
    @POST("personil/tambah")
    fun tambahPersonil(@Body personil: Personil): Call<Void>

    @PUT("personil/edit/{id}")
    fun editPersonil(@Path("id") id: String, @Body personil: Personil): Call<Void>

    @DELETE("personil/hapus/{id}")
    fun hapusPersonil(@Path("id") id: String): Call<Void>




}