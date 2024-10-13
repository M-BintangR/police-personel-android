 package com.example.siantik

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.siantik.databinding.ActivityAbsenSiangBinding

 class AbsenSiangActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAbsenSiangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbsenSiangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        }
    }
