package com.example.retomuzkiz.gastelua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.databinding.ActivityGazteluaBinding

class ActivityGaztelua : AppCompatActivity() {
    lateinit var binding: ActivityGazteluaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGazteluaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnVolver.setOnClickListener{
            finish()
        }
    }
}