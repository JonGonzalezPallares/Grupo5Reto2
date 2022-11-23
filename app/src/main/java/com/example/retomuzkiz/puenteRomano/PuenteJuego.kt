package com.example.retomuzkiz.puenteRomano

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.retomuzkiz.R
import com.example.retomuzkiz.clases.MsgVictoria
import com.example.retomuzkiz.databinding.ActivityPuenteJuegoBinding


class PuenteJuego : AppCompatActivity() {

    private lateinit var binding: ActivityPuenteJuegoBinding
    //Grupo de preguntas en el que estamos
    private var cantidad = 1
    //Lista para guardar las respuestas correctas
    private lateinit var respuestas : MutableList<String>
    //Lista para guardar las respuestas seleccionadas
    private lateinit var seleccion : String
    //Array con los trozos de las imagenes
    private lateinit var imagenes : MutableList<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuenteJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Metemos las respuestas en el array
        respuestas = mutableListOf("XIII. mendean, Muskizko zaharrena izan zen",
        "Barbadun ibaia", "Galdames eta Sopuertako pertsonak Muskizera igaro ahal izateko", "Harriz")

        //Metemos los trozos de la imagen en un array
        imagenes = mutableListOf(binding.imgTrozo1, binding.imgTrozo2, binding.imgTrozo3, binding.imgTrozo4)

        //Si es el primer set de preguntas
        binding.rdbRes1.setOnCheckedChangeListener { group, id ->
            when(id){
                R.id.rb_res1_1 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_1).text.toString()
                    seleccion = texto
                }
                R.id.rb_res1_2 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_2).text.toString()
                    seleccion = texto
                }
                R.id.rb_res1_3 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_3).text.toString()
                    seleccion = texto
                }
            }
            binding.btnComprobar.isEnabled = true
        }

        //Si es el segundo set de preguntas
        binding.rdbRes2.setOnCheckedChangeListener { group, id ->
            when(id){
                R.id.rb_res2_1 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res2_1).text.toString()
                    seleccion = texto
                }
                R.id.rb_res2_2 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res2_2).text.toString()
                    seleccion = texto
                }
                R.id.rb_res2_3 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res2_3).text.toString()
                    seleccion = texto
                }
            }
            binding.btnComprobar.isEnabled = true
        }

        //Si es el tercer set de preguntas
        binding.rdbRes3.setOnCheckedChangeListener { group, id ->
            when(id){
                R.id.rb_res3_1 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res3_1).text.toString()
                    seleccion = texto
                }
                R.id.rb_res3_2 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res3_2).text.toString()
                    seleccion = texto
                }
                R.id.rb_res3_3 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res3_3).text.toString()
                    seleccion = texto
                }
            }
            binding.btnComprobar.isEnabled = true
        }

        //Si es el cuarto set de preguntas
        binding.rdbRes4.setOnCheckedChangeListener { group, id ->
            when(id){
                R.id.rb_res4_1 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res4_1).text.toString()
                    seleccion = texto
                }
                R.id.rb_res4_2 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res4_2).text.toString()
                    seleccion = texto
                }
                R.id.rb_res4_3 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res4_3).text.toString()
                    seleccion = texto
                }
            }
            binding.btnComprobar.isEnabled = true
        }

        //Comprueba si el dato seleccionado concuerda con la respuesta correcta
        binding.btnComprobar.setOnClickListener {
            val respuestaBien = respuestas[cantidad-1]
            if(respuestaBien == seleccion){
                val imagen = imagenes[cantidad-1]
                imagen.visibility = View.VISIBLE
                if(cantidad<4){
                    imagen.startAnimation(AnimationUtils.loadAnimation(this, R.anim.animacion_puente))
                }
                cambiarPantalla()
            }else{
                limpiarSeleccion()
            }
        }
    }

    //Muestra el siguiente set de preguntas si se acierta
    private fun cambiarPantalla() {
        when (cantidad) {
            1 -> {
                binding.TrozoPreg1.visibility = View.GONE
                binding.TrozoPreg2.visibility = View.VISIBLE
            }
            2 -> {
                binding.TrozoPreg2.visibility = View.GONE
                binding.TrozoPreg3.visibility = View.VISIBLE
            }
            3 -> {
                binding.TrozoPreg3.visibility = View.GONE
                binding.TrozoPreg4.visibility = View.VISIBLE
            }
            else -> {
                limpiarSeleccion()
            }
        }
        cantidad++
        binding.btnComprobar.isEnabled = false
    }

    //Para limpiar la seleccion que se haya hecho cada vez que se falle
    private fun limpiarSeleccion() {
        when (cantidad){
            1 -> {
                binding.rdbRes1.clearCheck()
            }
            2 -> {
                binding.rdbRes2.clearCheck()
            }
            3 -> {
                binding.rdbRes3.clearCheck()
            }
            else -> {
                binding.rdbRes4.clearCheck()
                mostrarImg()
            }
        }
    }

    //Funcion para mostrar la imagen final
    private fun mostrarImg() {
        for(imagen in imagenes){
            imagen.visibility = View.GONE
        }
        binding.imgFinal.visibility = View.VISIBLE

        binding.imgFinal.startAnimation(AnimationUtils.loadAnimation(this, R.anim.animacion_puente_final))

        val tiempo = binding.imgFinal.animation.duration

        Handler(Looper.myLooper()?:return).postDelayed({
            MsgVictoria().carga(this)
        }, (tiempo+1000))
    }
}