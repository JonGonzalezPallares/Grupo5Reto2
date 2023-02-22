package com.example.retomuzkiz.puenteRomano

import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.retomuzkiz.*
import com.example.retomuzkiz.databinding.ActivityPuenteJuegoBinding
import com.example.retomuzkiz.funcionesExtension.eliminarNegrita


class PuenteJuego : AppCompatActivity() {
    lateinit var mediaPlay: MediaPlayer
    private lateinit var binding: ActivityPuenteJuegoBinding
    //Grupo de preguntas en el que estamos
    private var cantidad = 1
    //Lista para guardar las respuestas correctas
    private lateinit var respuestas : MutableList<String>
    //Lista para guardar las respuestas seleccionadas
    private lateinit var seleccion : String
    //Array con los trozos de las imagenes
    private lateinit var imagenes : MutableList<ImageView>
    //Lista para los textos de las soluciones
    private lateinit var seleTxt : List<List<Int>>
    //Variable para saber cuando se tiene que cerrar y cuando no
    private var cambio = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuenteJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startTimer()
        fin = 0
        //Para borrar la barra superior
        this.supportActionBar!!.hide()
        binding.btnayuda.setOnClickListener {
            dialogoAyudaJuegos("puente",this,layoutInflater)
        }

        //Metemos las respuestas en el array
        //Tiene que ser una mutable list por que los textos estan obtenidos del resources
        respuestas = mutableListOf(
            resources.getString(R.string.puente_preg_1_res_2),
            resources.getString(R.string.puente_preg_2_res_1),
            resources.getString(R.string.puente_preg_3_res_3),
            resources.getString(R.string.puente_preg_4_res_2)
        )

        //Metemos los trozos de la imagen en un array
        imagenes = mutableListOf(binding.imgTrozo1, binding.imgTrozo2, binding.imgTrozo3, binding.imgTrozo4)

        //Listas para guardar los botones
        val grupos = listOf(
            binding.rdbRes1, binding.rdbRes2, binding.rdbRes3, binding.rdbRes4
        )

        //Lista de listas para guardar los id de los botones que seleccionemos
        val seleId = listOf(
            listOf(
                R.id.rb_res1_1,
                R.id.rb_res1_2,
                R.id.rb_res1_3
            ),
            listOf(
                R.id.rb_res2_1,
                R.id.rb_res2_2,
                R.id.rb_res2_3
            ),
            listOf(
                R.id.rb_res3_1,
                R.id.rb_res3_2,
                R.id.rb_res3_3
            ),
            listOf(
                R.id.rb_res4_1,
                R.id.rb_res4_2,
                R.id.rb_res4_3
            )
        )

        //Lista de listas para guardar los textos de las selecciones
        seleTxt = listOf(
            listOf(
                binding.rbRes11.id,
                binding.rbRes12.id,
                binding.rbRes13.id
            ),
            listOf(
                binding.rbRes21.id,
                binding.rbRes22.id,
                binding.rbRes23.id
            ),
            listOf(
                binding.rbRes31.id,
                binding.rbRes32.id,
                binding.rbRes33.id
            ),
            listOf(
                binding.rbRes41.id,
                binding.rbRes42.id,
                binding.rbRes43.id
            )
        )

        var paso = 0

        //Recorremos el grupo de todos los radiogroups
        for(grupo in grupos) {
            //Guardamos en una variable la lista de id separado por cada grupo de preguntas
            val listaId = seleId[paso]

            val primero = findViewById<RadioButton>(seleTxt[paso][0].toString().toInt())
            val segundo = findViewById<RadioButton>(seleTxt[paso][1].toString().toInt())
            val tercero = findViewById<RadioButton>(seleTxt[paso][2].toString().toInt())

            //Añadimos el listener a cada grupo
            grupo.setOnCheckedChangeListener { _, id ->
                when(id){
                    //Si el seleccionado es la primera opcion
                    listaId[0] -> {
                        //Recogemos el texto y se lo pasamos a la variable de seleccion
                        val texto = findViewById<RadioButton>(listaId[0]).text.toString()
                        seleccion = texto

                        val lista = listOf(primero, segundo, tercero)
                        typefaceEstyle(lista, 0)
                    }

                    //Si el seleccionado es la segunda opcion
                    listaId[1] -> {
                        //Recogemos el texto y se lo pasamos a la variable de seleccion
                        val texto = findViewById<RadioButton>(listaId[1]).text.toString()
                        seleccion = texto

                        val lista = listOf(primero, segundo, tercero)
                        typefaceEstyle(lista,1)
                    }

                    //Si el seleccionado es la tercera opcion
                    listaId[2] -> {
                        //Recogemos el texto y se lo pasamos a la variable de seleccion
                        val texto = findViewById<RadioButton>(listaId[2]).text.toString()
                        seleccion = texto

                        val lista = listOf(primero, segundo, tercero)
                        typefaceEstyle(lista, 2)
                    }
                }
                binding.btnComprobar.alpha = 1F
                binding.btnComprobar.isEnabled = true
            }
            paso += 1
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
                changeScreen()
            }else{
                cleanSelection()
            }
        }
    }

    //Funcion para cambiar el estilo de los typeface de los radio button
    private fun typefaceEstyle(lista: List<RadioButton>, i: Int) {
        val tamanio = lista.size
        for(elemento in 0 .. tamanio){
            if(elemento == i) {
                lista[i].typeface = Typeface.DEFAULT_BOLD
            }else{
                lista[i].typeface = Typeface.DEFAULT
            }
        }
    }

    //Muestra el siguiente set de preguntas si se acierta
    private fun changeScreen() {
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
                showImg()
            }
        }
        cantidad++
        binding.btnComprobar.alpha = 0.5F
        binding.btnComprobar.isEnabled = false
    }

    //Para limpiar la seleccion que se haya hecho cada vez que se falle
    private fun cleanSelection() {
        when (cantidad){
            1 -> {
                binding.rdbRes1.clearCheck()

                //Llamada a la funcion para eliminar el efecto de "negrita"
                binding.rdbRes1.eliminarNegrita(seleTxt, cantidad)
            }

            2 -> {
                binding.rdbRes2.clearCheck()

                //Llamada a la funcion para eliminar el efecto de "negrita"
                binding.rdbRes2.eliminarNegrita(seleTxt, cantidad)
            }

            3 -> {
                binding.rdbRes3.clearCheck()

                //Llamada a la funcion para eliminar el efecto de "negrita"
                binding.rdbRes3.eliminarNegrita(seleTxt, cantidad)
            }

            else -> {
                binding.rdbRes4.clearCheck()

                //Llamada a la funcion para eliminar el efecto de "negrita"
                binding.rdbRes4.eliminarNegrita(seleTxt, cantidad)
            }
        }
        binding.btnComprobar.alpha = 0.5F
    }

    //Funcion para mostrar la imagen final
    private fun showImg() {
        for(imagen in imagenes){
            imagen.visibility = View.GONE
        }

        binding.imgFinal.visibility = View.VISIBLE
        binding.imgFinal.startAnimation(AnimationUtils.loadAnimation(this, R.anim.animacion_puente_final))

        val tiempo = binding.imgFinal.animation.duration

        Handler(Looper.myLooper()?:return).postDelayed({
            stopTimer()
            juegoAcabado(2)
            fin++
            cambio = true
            finalizar(this,"itsaslur")
        }, (tiempo+500))
    }

    //Al poner esta actividad en pausa (al abrir otra diferente), para que no pulsemos hacia atras y nos lleve a esta directamente
    override fun onPause() {

        super.onPause()
        mediaPlay!!.stop()
        mediaPlay!!.release()
        //musica("sanjuan",false,this)
        if(cambio){
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        mediaPlay = MediaPlayer.create(this,com.example.retomuzkiz.R.raw.mall)
        mediaPlay.isLooping = true
        mediaPlay.start()

        //musica("sanjuan",true,this)
    }
}