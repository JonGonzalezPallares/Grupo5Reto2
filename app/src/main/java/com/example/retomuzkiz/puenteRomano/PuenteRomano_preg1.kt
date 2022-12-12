package com.example.retomuzkiz.puenteRomano

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.retomuzkiz.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PuenteRomano_preg1.newInstance] factory method to
 * create an instance of this fragment.
 */
class PuenteRomano_preg1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_puente_romano_preg1, container, false)
        val saltar = vista.findViewById<ImageButton>(R.id.btnSaltar1)

        //Campo de texto donde va a estar el enunciado
        val texto = vista.findViewById<TextView>(R.id.txtPreg1)

        //Texto per se del enunciado
        val pregunta = texto.text.toString()

        //Texto guardado en un array para poder ir mostrando poco a poco
        val cadena = (texto.text.toString()).toCharArray()

        //Life cycle con la creacion del enunciado
        val ejecucion = viewLifecycleOwner.lifecycleScope.launch{
            //Ponemos el texto vacio para que no se superponga
            texto.text=""

            //Recorremos el array recoguiendo todas las letras y mostrandolas cada 0.08 segundos
            for(elemento in cadena){
                texto.append(""+elemento)
                delay(80)
            }
        }

        //Al clickar el boton de saltar se detiene el life cycle y nos pone el texto al completo
        saltar.setOnClickListener {
            ejecucion.cancel()
            texto.text = pregunta
        }
        return vista
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PuenteRomano_preg1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PuenteRomano_preg1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}