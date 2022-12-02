package com.example.retomuzkiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.retomuzkiz.databinding.ActivityMenuPrincipalBinding
import com.example.retomuzkiz.itsaslurIbilbidea.ItsaslurJuego
import com.example.retomuzkiz.itsaslurIbilbidea.PantallaEspera
import com.example.retomuzkiz.laArenaHondartza.IntroActivity
import com.example.retomuzkiz.puenteRomano.PuenteJuego
import com.example.retomuzkiz.puenteRomano.PuenteRomano

class MenuPrincipal : AppCompatActivity() {

    private lateinit var binding : ActivityMenuPrincipalBinding
    private lateinit var lista: List<Jugador>
    private lateinit var adaptador: JugadoresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        //Prueba para ver como queda el recycleview, quitarlo cuando tengamos jugadores
        load()

        adaptador = JugadoresAdapter(lista)

        binding.rvJugadores.adapter = adaptador

        binding.button.setOnClickListener {
            //val intento = Intent(this, MapsActivity::class.java)
            val intento = Intent(this, ItsaslurJuego::class.java)
            startActivity(intento)
        }
        binding.btnLaberinto.setOnClickListener(){

            startActivity(Intent(this, ActivityLaberinto::class.java))

        }
    }

    private fun load() {
        lista = listOf(
            Jugador("Jugador 1", "29"),
            Jugador("Jugador 2", "940"),
            Jugador("Jugador 3", "94028"),
            Jugador("Jugador 4", "2048"),
            Jugador("Jugador 5", "299382"),
        )
    }
}