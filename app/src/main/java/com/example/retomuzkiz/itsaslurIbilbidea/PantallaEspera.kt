package com.example.retomuzkiz.itsaslurIbilbidea

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.retomuzkiz.MapsActivity
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.databinding.ActivityPantallaEsperaBinding
import com.example.retomuzkiz.room.Usuario
import io.socket.client.IO
import io.socket.client.Socket

class PantallaEspera : AppCompatActivity() {
    companion object{
        var pantallas = 0
    }
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
        //Evento usuario unido al juego
        RetoGrupoCinco.mSocket.emit("join game", usuario.userClass)
        MapsActivity.isJoined = true

        //Evento usuario se une al juego
        RetoGrupoCinco.mSocket.on("user joined") { args ->
            runOnUiThread(){
                binding.txtUsuariosConectados.text = "${args[0]}/20"

            }

        }
        //Evento usuario deja el juego
        RetoGrupoCinco.mSocket.on("user leaved") { args ->
            runOnUiThread(){
                if(usuario.userClass.equals(args[0]) && !usuario.isProfessor ){
                    finish()

                }else{
                    binding.txtUsuariosConectados.text = "${args[1] as Int}/20"

                }
            }
        }
        //Evento profesor deja el juego
        RetoGrupoCinco.mSocket.on("game finish") { args ->
            runOnUiThread(){
                finish()
               // com.example.retomuzkiz.showDialog(this, "El profesor ha abandonado el juego", "Intentalo de nuevo mas tarde")
            }
        }
        //Evento Iniciar Juego
        var event = RetoGrupoCinco.mSocket.on("start game"){
            if ( pantallas<1 && MapsActivity.isJoined == true){

                if(!usuario.isProfessor){
                    val intento = Intent(this, ItsaslurJuego::class.java).putExtra("user", usuario)
                    startActivity(intento)
                    finish()
                    pantallas ++

                }else{
                    val intento = Intent(this, ItsaslurJuego::class.java).putExtra("user", usuario)
                    startActivity(intento)

                    pantallas ++

                }
            }


            println("El juego ha iniciado")

        }
        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        loadProfesor()
        if(binding.txtUsuariosConectados.text.toString() == "..."){
            binding.btnContinuar.visibility = View.VISIBLE
        }

        binding.btnContinuar.setOnClickListener {
            if(binding.txtUsuariosConectados.text.toString() == "..."){
                val intento = Intent(this, ItsaslurJuego::class.java).putExtra("user", usuario)
                startActivity(intento)
            }

            RetoGrupoCinco.mSocket.emit("startGame", usuario.userClass)
            cambio = true
            if(!usuario.isProfessor) {
                finish()
            }

        }
    }

    private fun loadProfesor() {
        if(usuario.isProfessor){
            binding.txtExplicativo.text = "Cuando acabes de dar la explicacion, pulsa en el boton continuar para iniciar el juego"
            binding.btnContinuar.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pantallas = 0

        MapsActivity.isJoined = false
    }
    override fun onBackPressed() {
        super.onBackPressed()
        if(usuario.isProfessor){
           // RetoGrupoCinco.mSocket.emit("user leave", usuario.userClass)
            RetoGrupoCinco.mSocket.emit("game finished", usuario.userClass)
        }else{
            RetoGrupoCinco.mSocket.emit("user leave", usuario.userClass)

        }
        pantallas = 0
        MapsActivity.isJoined=false

    }
    //Al poner esta actividad en pausa (al abrir otra diferente), para que no pulsemos hacia atras y nos lleve a esta directamente
    override fun onPause() {
        super.onPause()
        if(cambio){
            finish()
        }
        MapsActivity.isJoined=false
    }
}