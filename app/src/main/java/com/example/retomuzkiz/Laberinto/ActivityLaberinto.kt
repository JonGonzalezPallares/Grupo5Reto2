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

class ActivityLaberinto : AppCompatActivity() {
    companion object {
        var fin = 0
        //Variable para saber cuando se tiene que cerrar y cuando no
        var cambio = false

        fun finalizar(contexto: Context) {
            if(fin == 1){
                juegoAcabado()
                val intento = Intent(contexto, MsgVictoria::class.java)
                intento.putExtra("imagen", "laberinto")
                ContextCompat.startActivity(contexto, intento, null)
            }
        }
        var startTime: Long = 0
        var time: Long = 0
        fun startTimer() {
            startTime = SystemClock.elapsedRealtime()
        }

        fun stopTimer() {
            val elapsedTime = SystemClock.elapsedRealtime() - startTime
            time = elapsedTime
            println("Elapsed time: $elapsedTime ms")
        }
        private fun juegoAcabado() {
            RetoGrupoCinco
        }




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

    }


    //Al poner esta actividad en pausa (al abrir otra diferente), para que no pulsemos hacia atras y nos lleve a esta directamente
    override fun onPause() {
        super.onPause()
        if(cambio){
            finish()
        }
    }
}















