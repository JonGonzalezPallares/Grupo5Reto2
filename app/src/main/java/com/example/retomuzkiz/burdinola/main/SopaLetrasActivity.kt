package com.example.retomuzkiz.burdinola.main

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retomuzkiz.Laberinto.ActivityLaberinto
import com.example.retomuzkiz.R
import com.example.retomuzkiz.burdinola.game.GameFragment


class SopaLetrasActivity : AppCompatActivity() {
    lateinit var mediaPlay: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.juego_sopa)
        val exampleFragment = GameFragment(this)
        //Para borrar la barra superior
        this.supportActionBar!!.hide()
        // carga el fragment del juego
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_prencipal, exampleFragment)
            .commit()
    }
    override fun onPause() {

        super.onPause()
        mediaPlay!!.stop()
        mediaPlay!!.release()

        if(ActivityLaberinto.cambio){
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        mediaPlay = MediaPlayer.create(this,com.example.retomuzkiz.R.raw.nyan_cat)
        mediaPlay.isLooping = true
        mediaPlay.start()


    }
}
