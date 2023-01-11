package com.example.retomuzkiz.itsaslurIbilbidea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import com.example.retomuzkiz.clases.MsgVictoria
import com.example.retomuzkiz.databinding.ActivityItsaslurJuegoBinding

class ItsaslurJuego : AppCompatActivity() {

    private lateinit var binding : ActivityItsaslurJuegoBinding
    //Variable para saber en que lista de botones estamos
    private var posicion = 0
    //Variables de las listas para los botones
    private lateinit var buttonsList1 : List<ImageButton>
    private lateinit var buttonsList2 : List<ImageButton>
    private lateinit var buttonsList3 : List<ImageButton>
    private lateinit var buttonsList4 : List<ImageButton>
    //Variable de la lista de listas
    private lateinit var listaButtons : List<List<ImageButton>>
    //Variable de lista para guardar los grupos de preguntas
    private lateinit var preguntas : List<LinearLayout>
    //Variable para saber cuando se tiene que cerrar y cuando no
    private var cambio = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItsaslurJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        //Metemos todas las imagenes botones en cada una de su lista
        buttonsList1 = listOf(binding.imgbtn11, binding.imgbtn12, binding.imgbtn13, binding.imgbtn14)
        buttonsList2 = listOf(binding.imgbtn21, binding.imgbtn22, binding.imgbtn23, binding.imgbtn24)
        buttonsList3 = listOf(binding.imgbtn31, binding.imgbtn32, binding.imgbtn33, binding.imgbtn34)
        buttonsList4 = listOf(binding.imgbtn41, binding.imgbtn42, binding.imgbtn43, binding.imgbtn44)

        //Metemos las listas creadas con anterioridad en una sola lista para crear un bucle
        listaButtons = listOf(buttonsList1, buttonsList2, buttonsList3, buttonsList4)

        //Metemos los grupos de preguntas en una sola lista
        preguntas = listOf(binding.Preguntas1, binding.Preguntas2, binding.Preguntas3, binding.Preguntas4)

        //Llamamos a la primera funcion de comprobacion
        comprobar()
    }

    /*

    Añadir una animacion de una ventana de color rojo al fallar y otra de color verde al acertar
    Si se falla saldra una X en medio, si se hacierta saldra un tik

     */

    //Funcion para comprobar que se hayan acertado las imagenes seleccionadas
    private fun comprobar() {
        //Recorremos la lista con listas
        for (lista in listaButtons){
            //Recorremos cada lista para obtener cada boton
            for(button in lista){
                //Si estamos en la ultima pregunta y hemos acertado, abre el mensaje de victoria
                if(posicion==3){
                    button.setOnClickListener {
                        if(button.tag == "true"){
                            val intento = Intent(this, MsgVictoria::class.java)
                            intento.putExtra("imagen", "itsaslur")
                            cambio = true
                            startActivity(intento)
                        }
                    }
                }else{
                    //Añadimos el listener a cada boton
                    button.setOnClickListener {
                        //Si el tag del boton es correcto, cambiamos la visibilidad de las preguntas. Sumamos uno a la variable de posicion y volvemos a llamar a la funcion
                        if(button.tag == "true"){
                            preguntas[posicion].visibility = View.GONE
                            val avanze = posicion+1
                            preguntas[avanze].visibility = View.VISIBLE
                            posicion=avanze
                            comprobar()
                        }
                    }
                }
            }
        }
    }

    //Al poner esta actividad en pausa (al abrir otra diferente), para que no pulsemos hacia atras y nos lleve a esta directamente
    override fun onPause() {
        super.onPause()
        if(cambio){
            finish()
        }
    }
}