package com.example.retomuzkiz.burdinola.game

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
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
        startTimer()
        binding.lifecycleOwner = this
        //Reemplaza lo que está en la Vista con la cuadrícula más nueva y da una lista de palabras usadas a la vista
        binding.letterGrid.data = viewModel.grid
        binding.letterGrid.usedWordsList = viewModel.usedWordsList
        binding.wordsString.text = viewModel.usedWordString
        binding.wordsString.isAllCaps = false
        /**
         * Termina el juego si la puntuación llega a (todas las palabras encontradas).
         */
        binding.letterGrid.score.observe(viewLifecycleOwner) { gameScore ->
            val cambio = SpannableStringBuilder(binding.wordsString.text)
            viewModel.usedWordsList.forEach {
                if(it.found){
                    when(it.word){
                        "BARBADUN" -> {
                            cambio.setSpan(BackgroundColorSpan(Color.GREEN), 0, (0+it.word.length), 0)
                            binding.wordsString.text = cambio
                        }

                        "ERREMENTARI" -> {
                            cambio.setSpan(BackgroundColorSpan(Color.GREEN), 10, (10+it.word.length), 0)
                            binding.wordsString.text = cambio
                        }

                        "LABEA" -> {
                            cambio.setSpan(BackgroundColorSpan(Color.GREEN), 23, (23+it.word.length), 0)
                            binding.wordsString.text = cambio
                        }

                        "SUTEGIA" -> {
                            cambio.setSpan(BackgroundColorSpan(Color.GREEN), 30, (30+it.word.length), 0)
                            binding.wordsString.text = cambio
                        }

                        "BURDINOLA" -> {
                            cambio.setSpan(BackgroundColorSpan(Color.GREEN), 39, (39+it.word.length), 0)
                            binding.wordsString.text = cambio
                        }

                        "ERROTA" -> {
                            cambio.setSpan(BackgroundColorSpan(Color.GREEN), 50, (50+it.word.length), 0)
                            binding.wordsString.text = cambio
                        }

                        "SALAZAR" -> {
                            cambio.setSpan(BackgroundColorSpan(Color.GREEN), 58, (58+it.word.length), 0)
                            binding.wordsString.text = cambio
                        }

                        "TRESNAK" -> {
                            cambio.setSpan(BackgroundColorSpan(Color.GREEN), 67, (67+it.word.length), 0)
                            binding.wordsString.text = cambio
                        }
                    }
                }
            }
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