package com.example.retomuzkiz.Laberinto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.R

class ActivityLaberinto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_laberinto)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()
    }
}