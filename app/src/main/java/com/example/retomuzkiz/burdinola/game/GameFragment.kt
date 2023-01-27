package com.example.retomuzkiz.burdinola.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.retomuzkiz.R
import com.example.retomuzkiz.burdinola.score.ScoreFragment
import com.example.retomuzkiz.databinding.GameFragmentBinding


/**
 * Fragmento donde se juega el juego.
 */
class GameFragment : Fragment() {

    private lateinit var binding: GameFragmentBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.game_fragment, container, false)
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        binding.gameViewModel = viewModel

        binding.lifecycleOwner = this
        //Reemplaza lo que está en la Vista con la cuadrícula más nueva y da una lista de palabras usadas a la vista
        binding.letterGrid.data = viewModel.grid
        binding.letterGrid.usedWordsList = viewModel.usedWordsList

        /**
         * Termina el juego si la puntuación llega a (todas las palabras encontradas).
         */
        binding.letterGrid.score.observe(viewLifecycleOwner) { gameScore ->
            if (gameScore == viewModel.usedWordsList.size) {
                // puntuacion del juego
                val currentScore = binding.letterGrid.score.value ?: 0
                val newFragment = ScoreFragment()
                val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_prencipal, newFragment)
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
            }
        }
        return binding.root
    }
}