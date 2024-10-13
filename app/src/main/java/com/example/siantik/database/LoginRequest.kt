package com.example.siantik.database

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("nrp_nip") val nrpNip: String,
    @SerializedName("password") val password: String
)
