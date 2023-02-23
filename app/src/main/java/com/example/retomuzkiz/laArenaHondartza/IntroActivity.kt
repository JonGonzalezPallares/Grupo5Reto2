package com.example.retomuzkiz.laArenaHondartza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.retomuzkiz.databinding.ActivityIntroBinding
import com.example.retomuzkiz.funcionesExtension.cambiarF

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding
    //lista de fragmentos
    private val fragmentList = listOf(Arena_preg1(), Arena_preg2(), Arena_preg3(), Arena_preg4(), Arena_preg5())
    //variable para la posision de los fragments
    private var currentIndex = 0
    //Variable para saber cuando se tiene que cerrar y cuando no
    private var cambio = false

    //______________________________________________________________________________________________
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityIntroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        // Cargar el primer fragmento al iniciar la actividad
        fragmentList[currentIndex].cambiarF(binding.frgPreguntas.id, supportFragmentManager)

        binding.btnAtras.setOnClickListener {
            navigateBack()
        }

        binding.btnSiguiente.setOnClickListener {
            navigateNext()
        }

        binding.btnEmpezar.setOnClickListener {
            play()
        }
    }

    //______________________________________________________________________________________________
    // Llamar a la activity LaArenaHondartza
    private fun play() {
        cambio = true
        startActivity(Intent(this,LaArenaHondartza::class.java))
    }

    //______________________________________________________________________________________________
    // carga el el anterior fragment
    private fun navigateBack() {
        if (currentIndex > 0) {
            currentIndex--
            fragmentList[currentIndex].cambiarF(binding.frgPreguntas.id, supportFragmentManager)
            binding.btnSiguiente.visibility = View.VISIBLE
            binding.btnEmpezar.visibility = View.GONE

            if (currentIndex == 0) {
                binding.btnAtras.visibility = View.GONE
            }
        }
    }

    //______________________________________________________________________________________________
    // carga el siguiente fragment
    private fun navigateNext() {
        if (currentIndex < fragmentList.size - 1) {
            currentIndex++
            fragmentList[currentIndex].cambiarF(binding.frgPreguntas.id, supportFragmentManager)
            binding.btnAtras.visibility = View.VISIBLE

            if (currentIndex == fragmentList.size - 1) {
                binding.btnSiguiente.visibility = View.GONE
                binding.btnEmpezar.visibility = View.VISIBLE
            }
        }
    }

    //______________________________________________________________________________________________
    //Al poner esta actividad en pausa (al abrir otra diferente), para que no pulsemos hacia atras y nos lleve a esta directamente
    override fun onPause() {
        super.onPause()
        if(cambio){
            finish()
        }
    }
}