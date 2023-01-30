package com.example.retomuzkiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.LinearInterpolator
import com.example.retomuzkiz.databinding.ActivityLoadingBinding
import com.example.retomuzkiz.databinding.ActivityMenuPrincipalBinding

class LoadingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        this.supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtmuzkiz.alpha = 0f
        animaciones()


        Handler().postDelayed({
            val intento = Intent(this, MenuPrincipal::class.java)
            startActivity(intento)
            this.finish()
        },3000)
    }


    fun animaciones(){
        binding.imgvandera.animate().apply {
            duration=1200 // La animación dura 1.2 segundo
            rotationYBy(360f) //rotacion en horizontal en un sentido
        }.withEndAction {
            binding.imgvandera.animate().apply {
                duration=1200 // La animación dura 1.2 segundo
                rotationYBy(-360f) //rotacion en horizontal en el otro sentido
            }.start()
        }
        binding.txtmuzkiz.animate().apply {
            alpha(1f) // El TextView se vuelve completamente visible
            duration = 750 // La animación dura 0.75 segundo
            interpolator = LinearInterpolator() // Utiliza un interpolador lineal
        }.withEndAction {
            binding.txtmuzkiz.animate().apply {
            alpha(0.5f) // El TextView se vuelve completamente visible
                duration = 750 // La animación dura 1 segundo
                interpolator = LinearInterpolator() // Utiliza un interpolador lineal para darle
                //una uniformidad a la transformacion del texto
            }.withEndAction {
                binding.txtmuzkiz.animate().apply {
                    alpha(1f) // El TextView se vuelve completamente visible
                    duration = 750 // La animación dura 1 segundo
                    interpolator = LinearInterpolator() // Utiliza un interpolador lineal
                }
            }

        }.start() // Inicia la animación



        }


    }
