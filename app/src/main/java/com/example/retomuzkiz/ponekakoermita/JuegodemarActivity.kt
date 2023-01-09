package com.example.retomuzkiz.ponekakoermita

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.marginTop
import com.example.retomuzkiz.R
import com.example.retomuzkiz.clases.MsgVictoria
import com.example.retomuzkiz.databinding.ActivityJuegodemarBinding
import kotlin.concurrent.thread
import kotlin.random.Random

class JuegodemarActivity : AppCompatActivity() {

    private lateinit var binding : ActivityJuegodemarBinding
    private var respuestas = mutableListOf<String>()
    private var respuestasBien = mutableListOf<String>()

    //Lista con los diferentes fotos de mares
    private val maresTipos = mutableListOf(
        R.drawable.martran2,
        R.drawable.martran3,
        R.drawable.marrev1,
        R.drawable.marrev2,
        R.drawable.marrev3
    )

    //Lista con las soluciones correctas
    private val respuestasMares = mutableListOf(
        "Tranquilo",
        "Tranquilo",
        "Revuelto",
        "Revuelto",
        "Revuelto"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJuegodemarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        //Cargamos la primera imagen de fondo
        binding.imgMar.background = AppCompatResources.getDrawable(this, R.drawable.martran1)

        respuestasBien.add("Tranquilo")

        binding.color.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fondonegro))
        binding.imgBinoculares.startAnimation(AnimationUtils.loadAnimation(this, R.anim.binoculares))

        binding.btnRevuelto.setOnClickListener{
            respuestas.add("Revuelto")
            if(respuestasBien.size==6){
                comprobacion()
            }else{
                mares()
            }
        }

        Thread {
            Thread.sleep(2005)
            binding.btnRevuelto.visibility = View.VISIBLE
            binding.btnTranquilo.visibility = View.VISIBLE
        }

        binding.btnTranquilo.setOnClickListener{
            respuestas.add("Tranquilo")
            if(respuestasBien.size==6){
                comprobacion()
            }else{
                mares()
            }
        }
    }

    //Funcion para cargar las imagenes de fondo
    private fun mares() {
        //Numero aleatorio desde 0 hasta el tamaño de la lista
        val random = Random.nextInt(maresTipos.size)

        binding.imgMar.background = AppCompatResources.getDrawable(this, maresTipos[random])

        //Despues de cargar la imagen borramos ese elemento de la lista
        maresTipos.removeAt(random)

        respuestasBien.add(respuestasMares[random])

        //Despues de guardar la respuesta correcta, borramos ese elemento
        respuestasMares.removeAt(random)
    }

    //Funcion para comprobar las respuestas
    private fun comprobacion() {
        var correctas = 0
        for (respuesta in 0 until 6){
            if(respuestasBien[respuesta]==respuestas[respuesta]){
                correctas += 1
            }
        }
    }
}