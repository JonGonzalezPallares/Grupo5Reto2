package com.example.retomuzkiz.burdinola.score
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ScoreFragmentBinding


/**
 * Fragmento donde se muestra la puntuación final, una vez finalizada la partida.

 */
class ScoreFragment : Fragment() {

    private lateinit var binding: ScoreFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.score_fragment, container, false
        )

        return binding.root
    }


}
