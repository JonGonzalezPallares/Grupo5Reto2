package com.example.retomuzkiz.laArenaHondartza


import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ActivityLaArenaHondartzaBinding


class LaArenaHondartza : Activity() {
    //______________________________________________________________________________________________
    // variables para los componentes de la vista
    private var tableroRespuestas = arrayOfNulls<ImageButton>(5)
    private var tableroPreguntas = arrayOfNulls<ImageButton>(5)
    private lateinit var botonReiniciar: Button
    private lateinit var resultado: TextView
    private lateinit var puntos: String
    private lateinit var binding: ActivityLaArenaHondartzaBinding


    //______________________________________________________________________________________________
    //imagenes
    private lateinit var preguntas: IntArray
    private lateinit var respuestas: IntArray
    var fondo = 0

    //______________________________________________________________________________________________
    //variables del juego
    private lateinit var arrayDesordenadoRespuestas: ArrayList<Int>
    private lateinit var arrayDesordenadoPreguntas: ArrayList<Int>
    private var primero: ImageButton? = null
    private var numeroPrimero = 0
    private var numeroSegundo = 0
    private var bloqueo = false
    private var aciertos = 0

    //______________________________________________________________________________________________
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaArenaHondartzaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    //______________________________________________________________________________________________
    private fun cargarTablero() {
        tableroPreguntas[0] = binding.boton00
        tableroPreguntas[1] = binding.boton01
        tableroPreguntas[2] = binding.boton02
        tableroPreguntas[3] = binding.boton03
        tableroPreguntas[4] = binding.boton04

        tableroRespuestas[0] = binding.boton08
        tableroRespuestas[1] = binding.boton09
        tableroRespuestas[2] = binding.boton10
        tableroRespuestas[3] = binding.boton11
        tableroRespuestas[4] = binding.boton12
    }

    //______________________________________________________________________________________________
    private fun cargarBotones() {
        botonReiniciar = binding.botonJuegoReiniciar
        botonReiniciar.setOnClickListener { init() }
        resultado = binding.tvAciertos
        resultado.text = ""
        puntos = "Has acertado: "
        aciertos = 0
    }

    //______________________________________________________________________________________________
    private fun cargarImagenes() {
        preguntas = intArrayOf(
            R.drawable.pre0,
            R.drawable.pre1,
            R.drawable.pre2,
            R.drawable.pre3,
            R.drawable.pre4,
        )
        respuestas = intArrayOf(
            R.drawable.res0,
            R.drawable.res1,
            R.drawable.res2,
            R.drawable.res3,
            R.drawable.res4,
        )
        fondo = R.drawable.fondo_preguntas
    }

    //______________________________________________________________________________________________
    /* devuelve array desordenado  requiere de parametro la longitud del array */
    private fun desordenarArray(longitud: Int): ArrayList<Int> {
        val result = ArrayList<Int>()
        for (i in 0 until longitud) {
            result.add(i % longitud)
        }
        result.shuffle()
        return result
    }

    //______________________________________________________________________________________________
    private fun comprobar(i: Int, pregunta: Boolean, imgb: ImageButton?) {
        if (primero == null)
        {
            if (pregunta) 
            {
                primero = imgb
                primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
                primero!!.setImageResource(preguntas[arrayDesordenadoPreguntas[i]])
                primero!!.isEnabled = false
                numeroPrimero = arrayDesordenadoPreguntas[i]

            } else
            {
                primero = imgb
                primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
                primero!!.setImageResource(respuestas[arrayDesordenadoRespuestas[i]])
                primero!!.isEnabled = false
                numeroPrimero = arrayDesordenadoRespuestas[i]
            }

        } else
        {
            when (pregunta)
            {
                true -> setPregunta(i, imgb)
                else -> setRespuesta(i, imgb)
            }

            if (numeroPrimero == numeroSegundo) 
            {
                primero = null
                bloqueo = false
                aciertos++
                puntos += aciertos
                resultado.text = puntos
                puntos ="Has acertado: "
                if (aciertos == respuestas.size)
                {
                    println("Game over...")
                }
            } else 
            {
                Handler(Looper.myLooper()?:return).postDelayed({
                    primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
                    primero!!.setImageResource(fondo)
                    primero!!.isEnabled = true
                    imgb!!.scaleType = ImageView.ScaleType.CENTER_CROP
                    imgb.setImageResource(fondo)
                    imgb.isEnabled = true
                    bloqueo = false
                    primero = null
                }, 1000)
            }
        }
    }

    //______________________________________________________________________________________________
    private fun setRespuesta(i: Int, imgb: ImageButton?)
    {
        bloqueo = true
        imgb!!.scaleType = ImageView.ScaleType.CENTER_CROP
        imgb.setImageResource(respuestas[arrayDesordenadoRespuestas[i]])
        imgb.isEnabled = false
        numeroSegundo = arrayDesordenadoRespuestas[i]
    }

    //______________________________________________________________________________________________
    private fun setPregunta(i: Int, imgb: ImageButton?)
    {
        bloqueo = true
        imgb!!.scaleType = ImageView.ScaleType.CENTER_CROP
        imgb.setImageResource(preguntas[arrayDesordenadoPreguntas[i]])
        imgb.isEnabled = false
        numeroSegundo = arrayDesordenadoPreguntas[i]
    }

    //______________________________________________________________________________________________
    private fun init()
    {
        cargarTablero()
        cargarBotones()
        cargarImagenes()

        arrayDesordenadoRespuestas = desordenarArray(respuestas.size)
        arrayDesordenadoPreguntas = desordenarArray(preguntas.size)

        // setea imagen a cada button en la posicion donde se encuentra
        for (i in 0..4) 
        {
            tableroRespuestas[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
            tableroPreguntas[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
            tableroRespuestas[i]!!.setImageResource(respuestas[arrayDesordenadoRespuestas[i]])
            tableroPreguntas[i]!!.setImageResource(preguntas[arrayDesordenadoPreguntas[i]])
        }
        // demora  medio segundo luego esconde las imagenes puestas a los buttones
        Handler(Looper.myLooper()?:return).postDelayed({
            for (i in 0..4)
            {
                tableroRespuestas[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
                tableroPreguntas[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
                tableroRespuestas[i]!!.setImageResource(fondo)
                tableroPreguntas[i]!!.setImageResource(fondo)
            }
        }, 500)

        // al hacer  click sobre alguna foto llama a la funcion comprobar si no esta bloqueado
        for (i in 0..4)
        {
            tableroPreguntas[i]!!.isEnabled = true
            tableroRespuestas[i]!!.isEnabled = true
            tableroRespuestas[i]!!.setOnClickListener { if (!bloqueo) comprobar(i, false, tableroRespuestas[i]) }
            tableroPreguntas[i]!!.setOnClickListener { if (!bloqueo) comprobar(i, true, tableroPreguntas[i]) }
        }
    }
    //______________________________________________________________________________________________
}