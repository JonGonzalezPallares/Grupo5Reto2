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

        when (intent.getStringExtra("imagen")) {
            "itsaslur" -> imagenFondo=R.drawable.itsaslur2_2
            "arena" -> imagenFondo=R.drawable.irudia_arena_2
            "laberinto" ->imagenFondo=R.drawable.irudia_san_juan_1;
            "puente" -> imagenFondo=R.drawable.puentecompleto
            "mar" -> imagenFondo=R.drawable.martran2
            "castillo" -> imagenFondo=R.drawable.castillo
            "fundicion" -> imagenFondo=R.drawable.fundicion_pobela

        }


        binding.imgVictoria.setImageResource(imagenFondo)

        binding.btnIrMapa.setOnClickListener {
        finish()
        }
    }

    private fun juegoCompletado(s: String) {
    }
}