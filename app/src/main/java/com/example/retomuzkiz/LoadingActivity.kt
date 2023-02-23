package com.example.retomuzkiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.LinearInterpolator
import com.example.retomuzkiz.databinding.ActivityLoadingBinding

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

    private fun animaciones(){
        binding.imgvandera.animate().apply {
            // La animación dura 1.2 segundo
            duration=1200
            //rotacion en horizontal en un sentido
            rotationYBy(360f)
        }.withEndAction {
            binding.imgvandera.animate().apply {
                // La animación dura 1.2 segundo
                duration=1200
                //rotacion en horizontal en el otro sentido
                rotationYBy(-360f)
            }.start()
        }

        binding.txtmuzkiz.animate().apply {
            // El TextView se vuelve completamente visible
            alpha(1f)
            // La animación dura 0.75 segundo
            duration = 750
            // Utiliza un interpolador lineal
            interpolator = LinearInterpolator()
        }.withEndAction {
            binding.txtmuzkiz.animate().apply {
                // El TextView se vuelve completamente visible
                alpha(0.5f)
                // La animación dura 1 segundo
                duration = 750
                // Utiliza un interpolador lineal para darle una uniformidad a la transformacion del texto
                interpolator = LinearInterpolator()
            }.withEndAction {
                binding.txtmuzkiz.animate().apply {
                    // El TextView se vuelve completamente visible
                    alpha(1f)
                    // La animación dura 1 segundo
                    duration = 750
                    // Utiliza un interpolador lineal para darle una uniformidad a la transformacion del texto
                    interpolator = LinearInterpolator()
                }
            }
        }.start()
    }
}