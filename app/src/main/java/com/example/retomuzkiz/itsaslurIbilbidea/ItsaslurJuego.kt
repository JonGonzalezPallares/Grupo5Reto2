package com.example.retomuzkiz.itsaslurIbilbidea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.view.isVisible
import com.example.retomuzkiz.databinding.ActivityItsaslurJuegoBinding

class ItsaslurJuego : AppCompatActivity() {

    private lateinit var binding : ActivityItsaslurJuegoBinding
    private lateinit var buttonsList1 : List<ImageButton>
    private lateinit var buttonsList2 : List<ImageButton>
    private lateinit var buttonsList3 : List<ImageButton>
    private lateinit var buttonsList4 : List<ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItsaslurJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        //this.supportActionBar!!.hide()

        //Creamos cuatro arrays para guardar los botones
        buttonsList1 = mutableListOf(binding.imgbtn11, binding.imgbtn12, binding.imgbtn13, binding.imgbtn14)
        buttonsList2 = mutableListOf(binding.imgbtn21, binding.imgbtn22, binding.imgbtn23, binding.imgbtn24)
        buttonsList3 = mutableListOf(binding.imgbtn31, binding.imgbtn32, binding.imgbtn33, binding.imgbtn34)
        buttonsList4 = mutableListOf(binding.imgbtn41, binding.imgbtn42, binding.imgbtn43, binding.imgbtn44)

        //Llamamos a la primera funcion de comprobacion
        checkFirst()
    }

    /*

    Añadir una animacion de una ventana de color rojo al fallar y otra de color verde al acertar
    Si se falla saldra una X en medio, si se hacierta saldra un tik

     */

    //Comprueba que el tag del boton de la primera tanda de preguntas sea "true", si es asi pasa al siguiente
    private fun checkFirst() {
        for(button in buttonsList1){
            button.setOnClickListener {
                if(button.tag=="true"){
                    binding.Preguntas1.visibility = View.GONE
                    binding.Preguntas2.visibility = View.VISIBLE
                    checkSecond()
                }
            }
        }
    }

    //Comprueba si el tag de la segunda tanda de preguntas es "true", si es asi sigue
    private fun checkSecond() {
        for(button in buttonsList2){
            button.setOnClickListener {
                if(button.tag=="true"){
                    binding.Preguntas2.visibility = View.GONE
                    binding.Preguntas3.visibility = View.VISIBLE
                    checkThird()
                }
            }
        }
    }

    //Comprueba si el tag de la tercera tanda de preguntas es "true", si es asi sigue
    private fun checkThird() {
        for(button in buttonsList3){
            button.setOnClickListener {
                if(button.tag=="true"){
                    binding.Preguntas3.visibility = View.GONE
                    binding.Preguntas4.visibility = View.VISIBLE
                    checkFourth()
                }
            }
        }
    }

    //Comprueba si el tag de la ultima tanda de preguntas es "true", si es asi termina
    private fun checkFourth() {
        for(button in buttonsList4){
            button.setOnClickListener {
                if(button.tag=="true"){
                    binding.Preguntas4.visibility = View.GONE
                }
            }
        }
    }
}