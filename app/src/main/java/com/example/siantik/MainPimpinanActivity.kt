package com.example.siantik

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.siantik.databinding.ActivityMainPimpinanBinding

class MainPimpinanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainPimpinanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPimpinanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        }
    }