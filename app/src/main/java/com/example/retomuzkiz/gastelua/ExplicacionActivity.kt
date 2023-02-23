package com.example.retomuzkiz.gastelua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ActivityExplicacionBinding

class ExplicacionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityExplicacionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicacion)
        binding = ActivityExplicacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Para borrar la barra superior
        this.supportActionBar!!.hide()
        binding.btnjuego.setOnClickListener{
            val intento = Intent(this, ActivityGaztelua::class.java)
            startActivity(intento)
            this.finish()
        }
    }
}