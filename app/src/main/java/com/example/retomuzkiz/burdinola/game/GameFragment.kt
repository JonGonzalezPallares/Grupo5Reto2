package com.example.retomuzkiz.burdinola.game

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.retomuzkiz.*
import com.example.retomuzkiz.burdinola.custom.GridView
import com.example.retomuzkiz.burdinola.wordPlacement.Word
import com.example.retomuzkiz.databinding.GameFragmentBinding


/**
 * Fragmento donde se juega el juego.
 */
class GameFragment(applicationContext: Context) : Fragment() {

    private lateinit var binding: GameFragmentBinding
    private lateinit var viewModel: GameViewModel
    private var contexto : Context = applicationContext

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.game_fragment, container, false)

        fin = 0
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        binding.gameViewModel = viewModel
        startTimer()
        binding.lifecycleOwner = this
        //Reemplaza lo que está en la Vista con la cuadrícula más nueva y da una lista de palabras usadas a la vista
        binding.letterGrid.data = viewModel.grid
        binding.letterGrid.usedWordsList = viewModel.usedWordsList
        /**
         * Termina el juego si la puntuación llega a (todas las palabras encontradas).
         */
        binding.letterGrid.score.observe(viewLifecycleOwner) { gameScore ->
            println("hola")
            viewModel.usedWordString = "sfiod"
            if (gameScore == viewModel.usedWordsList.size) {
                // puntuacion del juego
                //LLamar el finalizar

                stopTimer()
                juegoAcabado(3)
                fin ++
                finalizar(contexto,"fundicion")
                requireActivity().finish()
            }
        }
        return binding.root
    }
}