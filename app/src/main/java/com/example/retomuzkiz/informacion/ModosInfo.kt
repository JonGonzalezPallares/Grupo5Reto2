package com.example.retomuzkiz.informacion

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.FragmentModosInfoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ModosInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class ModosInfo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentModosInfoBinding

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
        binding = FragmentModosInfoBinding.inflate(inflater, container, false)

        //Si no no se cargan los estilos del spannable
        binding.txtFreeMode.isAllCaps = false
        binding.txtGuideMode.isAllCaps = false
        binding.txtProfesorMode.isAllCaps = false

        //Creamos una variable con el texto que queremos añadirle los estilos
        val freeModeSpan = SpannableStringBuilder(resources.getString(R.string.freeMode))
        val guideModeSpan = SpannableStringBuilder(resources.getString(R.string.guidedMode))
        val profesorModeSpan = SpannableStringBuilder(resources.getString(R.string.profesorMode))

        //Añadimos los estilos de subrayado
        freeModeSpan.setSpan(UnderlineSpan(), 0, resources.getString(R.string.freeMode).length, 0)
        guideModeSpan.setSpan(UnderlineSpan(), 0, resources.getString(R.string.guidedMode).length, 0)
        profesorModeSpan.setSpan(UnderlineSpan(), 0, resources.getString(R.string.profesorMode).length, 0)

        //Ponemos los nuevos textos en los correspondientes sitios
        binding.txtFreeMode.text = freeModeSpan
        binding.txtGuideMode.text = guideModeSpan
        binding.txtProfesorMode.text = profesorModeSpan

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
         * @return A new instance of fragment ModosInfo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ModosInfo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}