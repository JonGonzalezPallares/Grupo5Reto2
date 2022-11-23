package com.example.retomuzkiz.itsaslurIbilbidea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.databinding.ActivityItsaslurJuegoBinding

class ItsaslurJuego : AppCompatActivity() {

    private lateinit var binding : ActivityItsaslurJuegoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItsaslurJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()


    }
}