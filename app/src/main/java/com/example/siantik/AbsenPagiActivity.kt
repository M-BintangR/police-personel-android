package com.example.siantik

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.siantik.database.AbsenPagi
import com.example.siantik.database.AbsenPagiResponse
import com.example.siantik.database.RetrofitAbsenPagi
import com.example.siantik.databinding.ActivityAbsenPagiBinding
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AbsenPagiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAbsenPagiBinding
    private var imageUri: Uri? = null // Untuk menyimpan URI gambar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbsenPagiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAbsenpagi.setOnClickListener {
            submitAbsenPagi()
        }

        binding.ivGambarpagi.setOnClickListener {
            // Mulai intent untuk memilih gambar
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            imageUri = data?.data
            binding.ivGambarpagi.setImageURI(imageUri) // Tampilkan gambar di ImageView
        }
    }

    private fun submitAbsenPagi() {
        val nrpNip = binding.etNrpabsensipagi.text.toString()
        val nama = binding.etNamapagi.text.toString()
        val lokasiId = binding.etLokasipagi.text.toString().toIntOrNull() ?: 0
        val keterangan = "hadir" // Keterangan otomatis diisi "hadir"

        // Mendapatkan waktu saat ini dalam format YYYY-MM-DD HH:MM:SS
        val currentDate: String = try {
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        } catch (e: Exception) {
            e.printStackTrace()
            "Unknown Date" // Default value in case of format error
        }

        // Buat RequestBody untuk data lainnya
        val nrpNipBody = RequestBody.create("text/plain".toMediaTypeOrNull(), nrpNip)
        val namaBody = RequestBody.create("text/plain".toMediaTypeOrNull(), nama)
        val lokasiIdBody = RequestBody.create("text/plain".toMediaTypeOrNull(), lokasiId.toString())
        val keteranganBody = RequestBody.create("text/plain".toMediaTypeOrNull(), keterangan)
        val waktuBody = RequestBody.create("text/plain".toMediaTypeOrNull(), currentDate)

        // Buat MultipartBody.Part untuk gambar
        val buktiPart: MultipartBody.Part? = imageUri?.let {
            val file = File(getRealPathFromURI(it))
            val requestFile = RequestBody.create((contentResolver.getType(it) ?: "image/jpeg").toMediaTypeOrNull(), file)
            MultipartBody.Part.createFormData("bukti", file.name, requestFile)
        }

        RetrofitAbsenPagi.AbsenPagiApi.submitAbsenPagi(nrpNipBody, namaBody, lokasiIdBody, keteranganBody, waktuBody, buktiPart ?: MultipartBody.Part.createFormData("bukti", "", RequestBody.create(null, "")))
            .enqueue(object : Callback<AbsenPagiResponse> {
                override fun onResponse(call: Call<AbsenPagiResponse>, response: Response<AbsenPagiResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AbsenPagiActivity, "Absen pagi berhasil.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@AbsenPagiActivity, "Gagal melakukan absen.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<AbsenPagiResponse>, t: Throwable) {
                    Toast.makeText(this@AbsenPagiActivity, "Terjadi kesalahan: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun getRealPathFromURI(contentUri: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri, proj, null, null, null)
        val column_index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        return cursor?.getString(column_index ?: 0) ?: ""
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 1
    }
}

private fun Any.enqueue(callback: Callback<AbsenPagiResponse>) {

}

private fun AbsenPagi.submitAbsenPagi(
    nrpNipBody: RequestBody,
    namaBody: RequestBody,
    lokasiIdBody: RequestBody,
    keteranganBody: RequestBody,
    waktuBody: RequestBody,
    part: MultipartBody.Part
): Any {    
    TODO("Not yet implemented")
}
