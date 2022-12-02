package com.example.retomuzkiz.Laberinto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.retomuzkiz.databinding.ActivityLaberintoBinding
import com.example.retomuzkiz.databinding.ActivityPuenteJuegoBinding
import com.example.retomuzkiz.R

class ActivityLaberinto : AppCompatActivity() {

    private lateinit var binding: ActivityLaberintoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaberintoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        //Para ponerla animacion a las llamas
        binding.fuegoAtras.startAnimation(AnimationUtils.loadAnimation(this, R.anim.animacion_fuego))
    }
}