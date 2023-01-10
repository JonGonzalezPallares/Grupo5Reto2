package com.example.retomuzkiz.gastelua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ActivityExplicacionBinding
import com.example.retomuzkiz.itsaslurIbilbidea.ItsaslurJuego

class ExplicacionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityExplicacionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicacion)
        binding = ActivityExplicacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnjuego.setOnClickListener{
            val intento = Intent(this, ActivityGaztelua::class.java)
            startActivity(intento)
        }


    }
}