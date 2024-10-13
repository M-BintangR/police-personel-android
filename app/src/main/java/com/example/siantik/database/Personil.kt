package com.example.siantik.database

import com.google.gson.annotations.SerializedName

data class Personil(
    @SerializedName("nrp_nip") val nrpNip: String,
    @SerializedName("role") val role: String,
    val toString: String,
    val toString1: String,
    val toString2: String,
    val toString3: String,
    val toString4: String,
    val toString5: String,
    val toString6: String
)

//data class Personil(
//    val nrp_nip: String,
//    val nama_lengkap: String,
//    val password: String,
//    val jenis_kelamin: String,
//    val pangkat: String,
//    val jabatan: String,
//    val nomor_telepon: String,
//    val email: String,
//    val role: String
//)