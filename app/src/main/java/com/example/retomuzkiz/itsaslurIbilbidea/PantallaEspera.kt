package com.example.retomuzkiz.itsaslurIbilbidea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.retomuzkiz.databinding.ActivityPantallaEsperaBinding

class PantallaEspera : AppCompatActivity() {

    private lateinit var binding : ActivityPantallaEsperaBinding
    //Variable para saber cuando se tiene que cerrar y cuando no
    private var cambio = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaEsperaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        load()

        binding.btnContinuar.setOnClickListener {
            val intento = Intent(this, ItsaslurJuego::class.java)
            startActivity(intento)
            cambio = true
        }
    }

    private fun load() {
        Handler(Looper.myLooper()?:return).postDelayed({
            binding.btnContinuar.visibility = View.VISIBLE
        }, 2000)
    }

    //Al poner esta actividad en pausa (al abrir otra diferente), para que no pulsemos hacia atras y nos lleve a esta directamente
    override fun onPause() {
        super.onPause()
        if(cambio){
            finish()
        }
    }
}