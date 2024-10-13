package com.example.siantik.database

import Lokasi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitLokasi {

    private const val BASE_URL = "http://172.16.1.171/absensi/api-editlokasi.php/" // Ganti dengan URL API Anda

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: LokasiApi by lazy {
        retrofit.create(LokasiApi::class.java)
    }
}
