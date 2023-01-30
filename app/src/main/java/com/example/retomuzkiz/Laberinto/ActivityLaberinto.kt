package com.example.retomuzkiz.Laberinto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.example.retomuzkiz.databinding.ActivityLaberintoBinding
import com.example.retomuzkiz.R
import com.example.retomuzkiz.clases.MsgVictoria
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.dialogoAyudaJuegos
import com.example.retomuzkiz.fin
import com.example.retomuzkiz.room.Game
import com.example.retomuzkiz.room.Progress
import com.example.retomuzkiz.room.TypeConverter
import com.example.retomuzkiz.room.Usuario
import com.example.retomuzkiz.startTimer

class ActivityLaberinto : AppCompatActivity() {
    companion object {

        //Variable para saber cuando se tiene que cerrar y cuando no
        var cambio = false


    }
    private lateinit var binding: ActivityLaberintoBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        startTimer()
        binding = ActivityLaberintoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fin = 0

        //Para borrar la barra superior
        this.supportActionBar!!.hide()
        //Para ponerla animacion a las llamas
        binding.fuegoAtras.startAnimation(AnimationUtils.loadAnimation(this, R.anim.animacion_fuego))
        binding.btnayuda.setOnClickListener {
            dialogoAyudaJuegos("sanjuan",this,layoutInflater)
        }

    }



    //Al poner esta actividad en pausa (al abrir otra diferente), para que no pulsemos hacia atras y nos lleve a esta directamente
    override fun onPause() {
        super.onPause()
        if(cambio){
            finish()
        }
    }
}















