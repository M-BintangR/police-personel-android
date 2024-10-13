package com.example.siantik

import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.siantik.database.Personil
import com.example.siantik.database.RetrofitClient
import com.example.siantik.databinding.ActivityManajemenPersonilBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManajemenPersonilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManajemenPersonilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManajemenPersonilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etNrpNip.filters = arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!Character.isDigit(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        })
        binding.btnTambahpersonil.setOnClickListener {
            val personil = Personil(
                binding.etNrpNip.text.toString(),
                binding.etNamalengkap.text.toString(),
                binding.etPassword.text.toString(),
                binding.spinnerJenisKelamin.selectedItem.toString(),
                binding.spinnerPangkat.selectedItem.toString(),
                binding.spinnerJabatan.selectedItem.toString(),
                binding.etNomortelepon.text.toString(),
                binding.etEmail.text.toString(),
                binding.spinnerRole.selectedItem.toString()
            )
            tambahPersonil(personil)
        }


        binding.btnEditpersonil.setOnClickListener {
            val personilId = binding.etNrpNip.text.toString()
            val personil = Personil(
                binding.etNrpNip.text.toString(),
                binding.etNamalengkap.text.toString(),
                binding.etPassword.text.toString(),
                binding.spinnerJenisKelamin.selectedItem.toString(),
                binding.spinnerPangkat.selectedItem.toString(),
                binding.spinnerJabatan.selectedItem.toString(),
                binding.etNomortelepon.text.toString(),
                binding.etEmail.text.toString(),
                binding.spinnerRole.selectedItem.toString()
            )
            editPersonil(personilId, personil)
        }


        binding.btnHapuspersonil.setOnClickListener {
            val personilId = binding.etNrpNip.text.toString()
            hapusPersonil(personilId)
        }
    }

    private fun tambahPersonil(personil: Personil) {
        RetrofitClient.instance.tambahPersonil(personil)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ManajemenPersonilActivity, "Personil berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ManajemenPersonilActivity, "Gagal menambah personil", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@ManajemenPersonilActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun editPersonil(id: String, personil: Personil) {
        RetrofitClient.instance.editPersonil(id, personil)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ManajemenPersonilActivity, "Personil berhasil diubah", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ManajemenPersonilActivity, "Gagal mengubah personil", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@ManajemenPersonilActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun hapusPersonil(id: String) {
        RetrofitClient.instance.hapusPersonil(id)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ManajemenPersonilActivity, "Personil berhasil dihapus", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ManajemenPersonilActivity, "Gagal menghapus personil", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@ManajemenPersonilActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
