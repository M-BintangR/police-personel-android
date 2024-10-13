package com.example.siantik.database

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitLogin {
    private const val BASE_URL = "http://172.16.1.171/absensi/api-login.php/"  // Sesuaikan URL sesuai dengan API Anda

    val instance: LoginApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(LoginApi::class.java)
    }
}