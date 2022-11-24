package com.example.retomuzkiz.ponekakoermita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.R

class JuegodemarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juegodemar)

    }
}