package com.example.retomuzkiz.Laberinto

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.retomuzkiz.*
import com.example.retomuzkiz.databinding.ActivityLaberintoBinding

class ActivityLaberinto : AppCompatActivity() {
    companion object {
        //Variable para saber cuando se tiene que cerrar y cuando no
        var cambio = false
    }

    lateinit var mediaPlay: MediaPlayer
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
        mediaPlay.stop()
        mediaPlay.release()
        if(cambio){
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        mediaPlay = MediaPlayer.create(this,R.raw.epic_sax_mp3)
        mediaPlay.isLooping = true
        mediaPlay.start()
    }
}