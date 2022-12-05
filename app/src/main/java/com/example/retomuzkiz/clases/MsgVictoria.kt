package com.example.retomuzkiz.clases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ActivityMsgVictoriaBinding

class MsgVictoria : AppCompatActivity() {
    private lateinit var binding : ActivityMsgVictoriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMsgVictoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        var imagenFondo = 0
        if(intent.getStringExtra("imagen") == "itsaslur2_2"){
            imagenFondo = R.drawable.itsaslur2_2
        }
        binding.imgVictoria.setImageResource(imagenFondo)
    }
}