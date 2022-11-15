package com.example.retomuzkiz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.databinding.ActivityMenuPrincipalBinding

class MenuPrincipal : AppCompatActivity() {

    private lateinit var binding : ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Quitamos la barra superior para que se vea mejor
        this.supportActionBar!!.hide()

        binding.button.setOnClickListener {
            val intento = Intent(this, MapsActivity::class.java)
            startActivity(intento)
        }
    }
}