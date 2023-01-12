package com.example.retomuzkiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Nosotros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nosotros)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()
    }
}