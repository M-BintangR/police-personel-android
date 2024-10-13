package com.example.siantik

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.siantik.database.LokasiResponse
import com.example.siantik.database.ResponseMessage
import com.example.siantik.database.RetrofitLokasi
import com.example.siantik.databinding.ActivityEditLokasiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditLokasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditLokasiBinding
    private var lokasiId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi ViewBinding
        binding = ActivityEditLokasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan lokasiId dari intent
        lokasiId = intent.getIntExtra("LOKASI_ID", 0)

        // Mengambil data lokasi berdasarkan lokasiId
        fetchLokasi()

        // Aksi ketika tombol Simpan diklik
        binding.btnSimpanLokasi.setOnClickListener {
            val namaLokasi = binding.etNamalokasi.text.toString().trim()
            val latitude = binding.etLatitude.text.toString().trim().toDoubleOrNull()
            val longitude = binding.etLongitude.text.toString().trim().toDoubleOrNull()
            val radius = binding.etRadius.text.toString().trim().toIntOrNull()

            if (namaLokasi.isEmpty() || latitude == null || longitude == null || radius == null) {
                Toast.makeText(this, "Semua field harus diisi dengan benar", Toast.LENGTH_SHORT).show()
            } else {
                updateLokasi(lokasiId, namaLokasi, latitude, longitude, radius)
            }
        }
    }

    // Mengambil data lokasi
    private fun fetchLokasi() {
        RetrofitLokasi.retrofitService.getLokasiById(lokasiId).enqueue(object : Callback<LokasiResponse> {
            override fun onResponse(call: Call<LokasiResponse>, response: Response<LokasiResponse>) {
                if (response.isSuccessful) {
                    val lokasi = response.body()?.data
                    if (lokasi != null) {
                        binding.etNamalokasi.setText(lokasi.nama)
                        binding.etLatitude.setText(lokasi.latitude.toString())
                        binding.etLongitude.setText(lokasi.longitude.toString())
                        binding.etRadius.setText(lokasi.radius.toString())
                    } else {
                        Toast.makeText(this@EditLokasiActivity, "Data lokasi tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@EditLokasiActivity, "Gagal mendapatkan data lokasi", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LokasiResponse>, t: Throwable) {
                Toast.makeText(this@EditLokasiActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Update lokasi
    private fun updateLokasi(lokasiId: Int, nama: String, latitude: Double, longitude: Double, radius: Int) {
        RetrofitLokasi.retrofitService.updateLokasi(lokasiId, nama, latitude, longitude, radius).enqueue(object : Callback<ResponseMessage> {
            override fun onResponse(call: Call<ResponseMessage>, response: Response<ResponseMessage>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@EditLokasiActivity, "Lokasi berhasil diperbarui", Toast.LENGTH_SHORT).show()
                    finish() // Kembali ke halaman sebelumnya
                } else {
                    Toast.makeText(this@EditLokasiActivity, "Gagal memperbarui lokasi", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                Toast.makeText(this@EditLokasiActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
