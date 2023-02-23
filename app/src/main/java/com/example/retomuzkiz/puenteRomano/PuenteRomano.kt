package com.example.retomuzkiz.puenteRomano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.retomuzkiz.funcionesExtension.cambiarF
import com.example.retomuzkiz.databinding.ActivityPuenteRomanoBinding

class PuenteRomano : AppCompatActivity() {

    private lateinit var binding : ActivityPuenteRomanoBinding
    //Variable para saber cuando se tiene que cerrar y cuando no
    private var cambio = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuenteRomanoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        //Para que se cargue el primer fragmento, por que si no no aparece nada
        PuenteRomano_preg1().cambiarF(binding.frgPreguntas.id, supportFragmentManager)
        binding.btnPrimero.visibility = View.GONE

        //Boton que lleva al primer fragment estando en el segundo
        binding.btnPrimero.setOnClickListener {
            //Cambiamos el estilo de los botones
            it.isEnabled = false

            //Estilo para el boton cuando esta inactivo
            binding.btnPrimero.visibility = View.GONE
            binding.btnUltimo.visibility = View.GONE
            binding.btnSiguiente.visibility = View.VISIBLE

            //Llamamos a la funcion para cambiar los fragmentos
            PuenteRomano_preg1().cambiarF(binding.frgPreguntas.id, supportFragmentManager)
        }

        //Boton que lleva al segundo fragment estando en el tercero
        binding.btnAtras.setOnClickListener {
            //Cambiamos el estilo de los botones
            it.visibility = View.GONE
            binding.btnUltimo.visibility = View.VISIBLE
            binding.btnEmpezar.visibility = View.GONE
            binding.btnUltimo.isEnabled = true
            binding.btnPrimero.visibility = View.VISIBLE

            //Llamamos a la funcion de extension para cambiar los fragmentos
            PuenteRomano_preg2().cambiarF(binding.frgPreguntas.id, supportFragmentManager)
        }

        //Boton que lleva al segundo fragment estando en el primero
        binding.btnSiguiente.setOnClickListener {
            //Cambiamos el estilo de los botones
            binding.btnPrimero.isEnabled=true

            //Estilo para el boton cuando esta activo
            binding.btnPrimero.visibility = View.VISIBLE

            it.visibility = View.GONE
            binding.btnUltimo.visibility = View.VISIBLE

            //Llamamos a la funcion de extension para cambiar los fragmentos
            PuenteRomano_preg2().cambiarF(binding.frgPreguntas.id, supportFragmentManager)
        }

        //Boton que lleva al tercer fragment estando en el segundo
        binding.btnUltimo.setOnClickListener {
            //Cambiamos el estilo de los botones
            binding.btnPrimero.visibility = View.GONE
            binding.btnAtras.visibility = View.VISIBLE
            it.visibility = View.GONE
            binding.btnEmpezar.visibility = View.VISIBLE

            //Llamamos a la funcion de extension para cambiar los fragmentos
            PuenteRomano_preg3().cambiarF(binding.frgPreguntas.id, supportFragmentManager)
        }

        //Boton que lleva a la activity del juego
        binding.btnEmpezar.setOnClickListener {
            val intento = Intent(this, PuenteJuego::class.java)
            cambio = true
            startActivity(intento)
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