package com.example.retomuzkiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retomuzkiz.Laberinto.ActivityLaberinto
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.database
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.prefs
import com.example.retomuzkiz.databinding.ActivityMenuPrincipalBinding
import com.example.retomuzkiz.room.Usuario

class MenuPrincipal : AppCompatActivity() {

    private lateinit var binding : ActivityMenuPrincipalBinding
    //private lateinit var lista: List<Jugador>
    //private lateinit var adaptador: JugadoresAdapter
    private lateinit var adaptadorUsuario: UsuariosAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        //Prueba para ver como queda el recycleview, quitarlo cuando tengamos jugadores
        /*  load()
          adaptador = JugadoresAdapter(lista)
          binding.rvJugadores.adapter = adaptador*/

        binding.button.setOnClickListener {
            guardarUsuario()
            val intento = Intent(this, MapsActivity::class.java)
            //val intento = Intent(this, PuenteRomano::class.java)
            //val intento = Intent(this, PantallaEspera::class.java)
            //val intento = Intent(this, LaberynthGame::class.java)
            startActivity(intento)
        }
        binding.btnLaberinto.setOnClickListener {
            startActivity(Intent(this, ActivityLaberinto::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        //__________________________________________________________________________________________
        val usuarioLista = database!!.usuarioDao.getAllUsers()
        adaptadorUsuario = UsuariosAdapter(usuarioLista){
            listar(it.nombre)
        }
        binding.rvJugadores.adapter = adaptadorUsuario
    }
    //______________________________________________________________________________________________
    // guarda en sharedFreferences nombre pasado en parametro y llama la activity maps
    private fun listar(nombre: String) {
        prefs.saveUser(nombre)
        val intento = Intent(this, MapsActivity::class.java)
        startActivity(intento)
    }

    //______________________________________________________________________________________________
    // recoge nombre del usuario, crea objeto usuario y se guarda en la base de datos
    private fun guardarUsuario() {
        val userName = binding.txtUsuario.text.toString()
        val puntos = 0
        val juegosCompletados = 0
        val user = Usuario(
            userName,
            puntos,
            juegosCompletados
        )
        database!!.usuarioDao.insertUser(user)
        prefs.saveUser(userName)
    }

}