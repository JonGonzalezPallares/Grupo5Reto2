package com.example.retomuzkiz.puenteRomano

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.retomuzkiz.R
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

        //Boton que lleva al primer fragment estando en el segundo
        binding.btnPrimero.setOnClickListener {
            //Cambiamos el estilo de los botones
            it.isEnabled = false

            //Estilo para el boton cuando esta inactivo
            binding.btnPrimero.setBackgroundResource(R.drawable.estilo_boton_desactivado)
            binding.btnPrimero.alpha = 0.5F
            binding.btnPrimero.setTextColor(Color.BLACK)

            binding.btnUltimo.visibility = View.GONE
            binding.btnSiguiente.visibility = View.VISIBLE

            //Llamamos a la funcion para cambiar los fragmentos
            changeFrag(PuenteRomano_preg1())
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
            changeFrag(PuenteRomano_preg2())
        }

        //Boton que lleva al segundo fragment estando en el primero
        binding.btnSiguiente.setOnClickListener {
            //Cambiamos el estilo de los botones
            binding.btnPrimero.isEnabled=true

            //Estilo para el boton cuando esta activo
            binding.btnPrimero.setBackgroundResource(R.drawable.estilo_botones)
            binding.btnPrimero.alpha = 1F
            binding.btnPrimero.setTextColor(Color.WHITE)

            it.visibility = View.GONE
            binding.btnUltimo.visibility = View.VISIBLE

            //Llamamos a la funcion para cambiar los fragmentos
            changeFrag(PuenteRomano_preg2())
        }

        //Boton que lleva al tercer fragment estando en el segundo
        binding.btnUltimo.setOnClickListener {
            //Cambiamos el estilo de los botones
            binding.btnPrimero.visibility = View.GONE
            binding.btnAtras.visibility = View.VISIBLE
            it.visibility = View.GONE
            binding.btnEmpezar.visibility = View.VISIBLE

            //Llamamos a la funcion para cambiar los fragmentos
            changeFrag(PuenteRomano_preg3())
        }

        //Boton que lleva a la activity del juego
        binding.btnEmpezar.setOnClickListener {
            val intento = Intent(this, PuenteJuego::class.java)
            cambio = true
            startActivity(intento)
        }
    }

    //Funcion que se encarga de cambiar entre los diferentes fragmentos
    private fun changeFrag(fragmento: Fragment) {
        //Creamos una variable para la transaccion
        val transicion = supportFragmentManager.beginTransaction().setReorderingAllowed(true)
        //Le añadimos a que contenedor tiene que hacer referencia, y le pasamos el fragmento que queremos cargar
        transicion.replace(binding.frgPreguntas.id, fragmento)
        //Hacemos el cambio
        transicion.commit()
    }

    //Al poner esta actividad en pausa (al abrir otra diferente), para que no pulsemos hacia atras y nos lleve a esta directamente
    override fun onPause() {
        super.onPause()
        if(cambio){
            finish()
        }
    }
}