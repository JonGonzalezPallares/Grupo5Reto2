package com.example.retomuzkiz.ponekakoermita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.appcompat.content.res.AppCompatResources
import com.example.retomuzkiz.*
import com.example.retomuzkiz.databinding.ActivityJuegodemarBinding
import kotlin.random.Random

class JuegodemarActivity : AppCompatActivity() {
    lateinit var mediaPlay: MediaPlayer
    private lateinit var binding : ActivityJuegodemarBinding
    private var respuestas = mutableListOf<String>()
    private var respuestasBien = 0
    //Variable para saber cuando se tiene que cerrar y cuando no
    private var cambio = false

    //Lista con los diferentes fotos de mares
    private val maresTipos = mutableListOf(
        R.drawable.martran2,
        R.drawable.marrev1,
        R.drawable.marrev2,
        R.drawable.martran3,
        R.drawable.marrev3,
        R.drawable.martran1
    )

    //Lista con las soluciones correctas
    private val respuestasMares = mutableListOf(
        "Tranquilo",
        "Revuelto",
        "Revuelto",
        "Tranquilo",
        "Revuelto",
        "Tranquilo"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJuegodemarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startTimer()
        fin = 0
        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        binding.btnayuda.setOnClickListener {
            dialogoAyudaJuegos("ermita",this,layoutInflater)
        }

        //Cargamos la primera imagen de fondo
        binding.imgMar.background = AppCompatResources.getDrawable(this, R.drawable.martran1)

        binding.color.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fondonegro))
        binding.imgBinoculares.startAnimation(AnimationUtils.loadAnimation(this, R.anim.binoculares))

        carga(binding.color.animation.duration)
        binding.btnRevuelto.setOnClickListener{
            respuestas.add("Revuelto")

            if ((respuestas.elementAt(respuestas.size-1)) == respuestasMares[respuestasBien]) {
                respuestasBien++
                comprobacionFinal()
                if(!comprobacionFinal()) {
                    mares(respuestasBien)

                }
            } else {
                binding.imgIncorrect.visibility = View.VISIBLE
                binding.imgIncorrect.alpha = 0f
                binding.imgIncorrect.animate().apply {
                    //El TextView se vuelve completamente visible
                    alpha(1f)
                    //La animación dura 0.75 segundo
                    duration = 750
                    //Utiliza un interpolador lineal
                    interpolator = LinearInterpolator()
                }.withEndAction {
                    binding.imgIncorrect.animate().apply {
                        //El TextView se vuelve completamente visible
                        alpha(0f)
                        //La animación dura 1 segundo
                        duration = 750
                        //Utiliza un interpolador lineal para darle
                        interpolator = LinearInterpolator()

                    }
                }.start()
                respuestas.removeLast()
            }
        }

        binding.btnTranquilo.setOnClickListener{
            respuestas.add("Tranquilo")

            if ((respuestas.elementAt(respuestas.size-1)) == respuestasMares[respuestasBien]) {
                respuestasBien++
                if(!comprobacionFinal()) {
                    mares(respuestasBien)

                }
            } else {
                binding.imgIncorrect.visibility = View.VISIBLE
                binding.imgIncorrect.alpha = 0f
                binding.imgIncorrect.animate().apply {
                    // El TextView se vuelve completamente visible
                    alpha(1f)
                    // La animación dura 0.75 segundo
                    duration = 750
                    // Utiliza un interpolador lineal
                    interpolator = LinearInterpolator()
                }.withEndAction {
                    binding.imgIncorrect.animate().apply {
                        // El TextView se vuelve completamente visible
                        alpha(0f)
                        // La animación dura 1 segundo
                        duration = 750
                        // Utiliza un interpolador lineal para darle
                        interpolator = LinearInterpolator()

                    }
                }.start()
                respuestas.removeLast()
            }
        }
    }

    private fun carga(duration: Long) {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.btnRevuelto.visibility = View.VISIBLE
            binding.btnTranquilo.visibility = View.VISIBLE
            binding.btnayuda.visibility = View.VISIBLE
        }, duration)
    }

    //Funcion para cargar las imagenes de fondo
    private fun mares(pos: Int) {
        //Numero aleatorio desde 0 hasta el tamaño de la lista
        binding.imgMar.background = AppCompatResources.getDrawable(this, maresTipos[pos])
    }

    //Funcion para comprobar las respuestas
    private fun comprobacionFinal():Boolean {
        if(respuestasBien== 5){
            stopTimer()
            juegoAcabado(5)
            fin ++
            finalizar(this,"mar")
            cambio = true
            return true
        }
        return false
    }

    //Al poner esta actividad en pausa (al abrir otra diferente), para que no pulsemos hacia atras y nos lleve a esta directamente
    override fun onPause() {

        super.onPause()
        mediaPlay!!.stop()
        mediaPlay!!.release()

        if(cambio){
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        mediaPlay = MediaPlayer.create(this,com.example.retomuzkiz.R.raw.intergalactic_odyssey)
        mediaPlay.isLooping = true
        mediaPlay.start()


    }
}