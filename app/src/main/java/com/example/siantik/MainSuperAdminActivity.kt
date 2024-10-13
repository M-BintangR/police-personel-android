package com.example.siantik

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.siantik.databinding.ActivityMainSuperAdminBinding

class MainSuperAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainSuperAdminBinding
    private lateinit var session: SessionLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inisialisasi ViewBinding
        binding = ActivityMainSuperAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SessionLogin
        session = SessionLogin(this)
        session.checkLogin()

        // Set Layout
        setInitLayout()
    }

    private fun setInitLayout() {
        // Gunakan binding untuk akses elemen-elemen UI
        binding.cvManajemenpersonil.setOnClickListener {
            navigateToActivity(ManajemenPersonilActivity::class.java, "Manajemen Personil")
        }

        binding.cvEditlokasi.setOnClickListener {
            navigateToActivity(EditLokasiActivity::class.java, "Edit Lokasi")
        }

        binding.imageLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun navigateToActivity(activityClass: Class<*>, title: String) {
        val intent = Intent(this, activityClass)
        intent.putExtra("DATA_TITLE", title)
        startActivity(intent)
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(this)
            .setMessage("Yakin Anda ingin Logout?")
            .setCancelable(true)
            .setNegativeButton("Batal") { dialog, _ -> dialog.cancel() }
            .setPositiveButton("Ya") { _, _ ->
                session.logoutUser()
                finishAffinity()
            }
            .create()
            .show()
    }
}
