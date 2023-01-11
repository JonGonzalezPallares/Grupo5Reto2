package com.example.retomuzkiz.itsaslurIbilbidea

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.databinding.ActivityPantallaEsperaBinding
import com.example.retomuzkiz.room.Usuario
import io.socket.client.IO
import io.socket.client.Socket

class PantallaEspera : AppCompatActivity() {

    private lateinit var binding : ActivityPantallaEsperaBinding
    //Variable para saber cuando se tiene que cerrar y cuando no
    private var cambio = false
    private var isEventSend = false
    private lateinit var usuario: Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaEsperaBinding.inflate(layoutInflater)
        setContentView(binding.root)
         usuario = intent.getParcelableExtra("user")!!
        var event = RetoGrupoCinco.mSocket.on("start game"){
            if(!usuario.isProfessor){
                val intento = Intent(this, ItsaslurJuego::class.java).putExtra("user", usuario)
                startActivity(intento)
            }

            println("El juego ha iniciado")

        }
        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        loadProfesor()

        binding.btnContinuar.setOnClickListener {
//            val intento = Intent(this, ItsaslurJuego::class.java).putExtra("user", usuario)
//            startActivity(intento)
            val intento = Intent(this, ItsaslurJuego::class.java).putExtra("user", usuario)
            startActivity(intento)



            RetoGrupoCinco.mSocket.emit("startGame", usuario.userClass)
            cambio = true
        }
    }

    private fun loadProfesor() {
        if(usuario.isProfessor){
            binding.txtExplicativo.text = "Cuando acabes de dar la explicacion, pulsa en el boton continuar para iniciar el juego"
            binding.btnContinuar.visibility = View.VISIBLE
        }
    }

    //Al poner esta actividad en pausa (al abrir otra diferente), para que no pulsemos hacia atras y nos lleve a esta directamente
    override fun onPause() {
        super.onPause()
        if(cambio){
            finish()
        }
    }
}