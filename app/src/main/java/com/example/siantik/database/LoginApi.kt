package com.example.siantik.database

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api-login.php")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
