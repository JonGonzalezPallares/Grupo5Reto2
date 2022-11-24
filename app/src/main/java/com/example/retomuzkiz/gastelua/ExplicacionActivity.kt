package com.example.retomuzkiz.gastelua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ActivityExplicacionBinding
import com.example.retomuzkiz.databinding.ActivityMapsBinding
import com.example.retomuzkiz.ponekakoermita.JuegodemarActivity

class ExplicacionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityExplicacionBinding
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicacion)
                binding = ActivityExplicacionBinding.inflate(layoutInflater)
                setContentView(binding.root)
    }
}