package com.example.retomuzkiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retomuzkiz.databinding.ActivityMenuPrincipalBinding
import com.example.retomuzkiz.puenteRomano.PuenteRomano

class MenuPrincipal : AppCompatActivity() {

    private lateinit var binding : ActivityMenuPrincipalBinding
    private lateinit var lista: List<Jugador>
    private lateinit var adaptador: JugadoresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Prueba para ver como queda el recycleview, quitarlo cuando tengamos jugadores
        carga()

        adaptador = JugadoresAdapter(lista)

        binding.rvJugadores.adapter = adaptador

        binding.button.setOnClickListener {
            //val intento = Intent(this, MapsActivity::class.java)
            val intento = Intent(this, PuenteRomano::class.java)
            startActivity(intento)
        }
    }

    private fun carga() {
        lista = listOf(
            Jugador("Jugador 1", "29"),
            Jugador("Jugador 2", "940"),
            Jugador("Jugador 3", "94028"),
            Jugador("Jugador 4", "2048"),
            Jugador("Jugador 5", "299382"),
        )
    }
}