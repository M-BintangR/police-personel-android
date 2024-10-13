package com.example.siantik.database

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAbsenPagi {

    private const val BASE_URL = "http://172.16.1.171/absensi/api-absenpagi.php/" // Ganti dengan URL API Anda

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val AbsenPagiApi: AbsenPagi by lazy {
        retrofit.create(AbsenPagiApi::class.java)
    }
}
