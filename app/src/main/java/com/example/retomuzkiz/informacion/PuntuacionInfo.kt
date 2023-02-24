package com.example.retomuzkiz.informacion

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.FragmentModosInfoBinding
import com.example.retomuzkiz.databinding.FragmentPuntuacionInfoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PuntuacionInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class PuntuacionInfo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentPuntuacionInfoBinding

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
    ): View {
        //Para que pueda funcionar el spannable
        binding = FragmentPuntuacionInfoBinding.inflate(inflater, container, false)

        //Si no no se cargan los estilos del spannable
        binding.txtRankingInfo.isAllCaps = false
        binding.txtTuProgreso.isAllCaps = false

        //Creamos una variable con el texto que queremos añadirle los estilos
        val rankingSpan = SpannableStringBuilder(resources.getString(R.string.ranking))
        val progresoSpan = SpannableStringBuilder(resources.getString(R.string.yourProgress))

        //Añadimos los estilos de subrayado
        rankingSpan.setSpan(UnderlineSpan(), 0, resources.getString(R.string.ranking).length, 0)
        progresoSpan.setSpan(UnderlineSpan(), 0, resources.getString(R.string.yourProgress).length, 0)

        //Ponemos los nuevos textos en los correspondientes sitios
        binding.txtRankingInfo.text = rankingSpan
        binding.txtTuProgreso.text = progresoSpan

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PuntuacionInfo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PuntuacionInfo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}