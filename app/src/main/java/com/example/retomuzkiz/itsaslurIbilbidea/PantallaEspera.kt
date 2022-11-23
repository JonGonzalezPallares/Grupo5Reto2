package com.example.retomuzkiz.itsaslurIbilbidea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.lifecycle.lifecycleScope
import com.example.retomuzkiz.clases.MsgVictoria
import com.example.retomuzkiz.databinding.ActivityPantallaEsperaBinding
import kotlinx.coroutines.launch

class PantallaEspera : AppCompatActivity() {

    private lateinit var binding : ActivityPantallaEsperaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaEsperaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        cargar()

        binding.btnContinuar.setOnClickListener {
            val intento = Intent(this, ItsaslurJuego::class.java)
            startActivity(intento)
        }
    }

    private fun cargar() {
        Handler(Looper.myLooper()?:return).postDelayed({
            binding.btnContinuar.visibility = View.VISIBLE
        }, 5000)
    }
}