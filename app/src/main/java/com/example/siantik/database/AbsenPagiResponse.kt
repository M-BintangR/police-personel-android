package com.example.siantik.database

data class AbsenPagiResponse(
    val status: String,
    val message: String? = null,
    val data: AbsenPagi? = null
)
