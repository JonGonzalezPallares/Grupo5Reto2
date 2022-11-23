package com.example.retomuzkiz.ponekacoermita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ActivityMarineroBinding

class MarineroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarineroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        this.supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marinero)
        binding = ActivityMarineroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnsiguiente.setOnClickListener {
            val actividad = Intent(this, juegodemarActivity::class.java)
            ContextCompat.startActivity(this, actividad, null)

        }
    }
}