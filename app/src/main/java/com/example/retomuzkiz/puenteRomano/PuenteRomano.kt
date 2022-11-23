package com.example.retomuzkiz.puenteRomano

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.retomuzkiz.databinding.ActivityPuenteRomanoBinding

class PuenteRomano : AppCompatActivity() {

    private lateinit var binding : ActivityPuenteRomanoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuenteRomanoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        //Boton que lleva al primer fragment estando en el segundo
        binding.btnPrimero.setOnClickListener {
            //Cambiamos el estilo de los botones
            it.isEnabled = false
            binding.btnUltimo.visibility = View.GONE
            binding.btnSiguiente.visibility = View.VISIBLE
            binding.btnPrimero.setBackgroundColor(Color.parseColor("#C1C0C0"))

            //Llamamos a la funcion para cambiar los fragmentos
            cambiarFrag(PuenteRomano_preg1())
        }

        //Boton que lleva al segundo fragment estando en el tercero
        binding.btnAtras.setOnClickListener {
            //Cambiamos el estilo de los botones
            it.visibility = View.GONE
            binding.btnUltimo.visibility = View.VISIBLE
            binding.btnEmpezar.visibility = View.GONE
            binding.btnUltimo.isEnabled = true
            binding.btnPrimero.visibility = View.VISIBLE

            //Llamamos a la funcion para cambiar los fragmentos
            cambiarFrag(PuenteRomano_preg2())
        }

        //Boton que lleva al segundo fragment estando en el primero
        binding.btnSiguiente.setOnClickListener {
            //Cambiamos el estilo de los botones
            binding.btnPrimero.isEnabled=true
            binding.btnPrimero.setBackgroundColor(Color.parseColor("#C822DA"))
            it.visibility = View.GONE
            binding.btnUltimo.visibility = View.VISIBLE

            //Llamamos a la funcion para cambiar los fragmentos
            cambiarFrag(PuenteRomano_preg2())
        }

        //Boton que lleva al tercer fragment estando en el segundo
        binding.btnUltimo.setOnClickListener {
            //Cambiamos el estilo de los botones
            binding.btnPrimero.visibility = View.GONE
            binding.btnAtras.visibility = View.VISIBLE
            it.visibility = View.GONE
            binding.btnEmpezar.setBackgroundColor(Color.parseColor("#C822DA"))
            binding.btnEmpezar.visibility = View.VISIBLE

            //Llamamos a la funcion para cambiar los fragmentos
            cambiarFrag(PuenteRomano_preg3())
        }

        //Boton que lleva a la activity del juego
        binding.btnEmpezar.setOnClickListener {
            val intento = Intent(this, PuenteJuego::class.java)
            startActivity(intento)
        }
    }

    //Funcion que se encarga de cambiar entre los diferentes fragmentos
    private fun cambiarFrag(fragmento: Fragment) {
        //Creamos una variable para la transaccion
        val transicion = supportFragmentManager.beginTransaction().setReorderingAllowed(true)
        //Le añadimos a que contenedor tiene que hacer referencia, y le pasamos el fragmento que queremos cargar
        transicion.replace(binding.frgPreguntas.id, fragmento)
        //Hacemos el cambio
        transicion.commit()
    }
}