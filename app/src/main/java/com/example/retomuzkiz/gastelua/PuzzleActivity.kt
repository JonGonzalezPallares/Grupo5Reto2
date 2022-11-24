package com.example.retomuzkiz.gastelua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ActivityExplicacionBinding
import com.example.retomuzkiz.databinding.ActivityPuzzleBinding
import com.example.retomuzkiz.ponekacoermita.MarineroActivity

class PuzzleActivity : AppCompatActivity() {
    private lateinit var binding :ActivityPuzzleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puzzle)
        binding = ActivityPuzzleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnsiguiente.setOnClickListener{
            val g = Intent(this, ExplicacionActivity::class.java)
            ContextCompat.startActivity(this, g, null)

        }
    }
}