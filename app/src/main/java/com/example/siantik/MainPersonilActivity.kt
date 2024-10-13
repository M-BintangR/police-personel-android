package com.example.siantik

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.app.AlertDialog
import com.example.siantik.AbsenSiangActivity
import com.example.siantik.AbsenPagiActivity
import com.example.siantik.RiwayatPagiActivity
import com.example.siantik.RiwayatSiangActivity
import com.example.siantik.databinding.ActivityMainPersonilBinding


class MainPersonilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainPersonilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPersonilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi layout
        setInitLayout()
    }

    private fun setInitLayout() {
        // Setup click listeners for cards
        binding.cvAbsenPagi.setOnClickListener {
            navigateToActivity(AbsenPagiActivity::class.java, "Absen Pagi")
        }

        binding.cvAbsenSiang.setOnClickListener {
            navigateToActivity(AbsenSiangActivity::class.java, "Absen Siang")
        }

        binding.cvRiwayatpagipersonil.setOnClickListener {
            navigateToActivity(RiwayatPagiActivity::class.java, "Riwayat Pagi")
        }

        binding.cvRiwayatsiangpersonil.setOnClickListener {
            navigateToActivity(RiwayatSiangActivity::class.java, "Riwayat Siang")
        }

        // Setup click listener for logout
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
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->
                // Handle logout here, e.g., clear session and navigate to login screen
                //startActivity(Intent(this, LoginActivity::class.java))
                finish() // Close current activity
            }
            .setNegativeButton("Tidak", null)
            .show()
    }
}
