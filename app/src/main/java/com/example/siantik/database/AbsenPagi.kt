package com.example.siantik.database

import okhttp3.RequestBody
import okhttp3.MultipartBody

data class AbsenPagi(
    val nrpNip: RequestBody,
    val nama: RequestBody,
    val lokasiId: RequestBody,
    val keterangan: RequestBody,
    val waktu: RequestBody,
    val bukti: MultipartBody.Part // Parameter untuk file gambar
)
