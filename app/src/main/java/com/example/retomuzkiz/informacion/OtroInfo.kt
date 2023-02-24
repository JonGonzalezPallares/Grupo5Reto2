package com.example.retomuzkiz.informacion

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.FragmentModosInfoBinding
import com.example.retomuzkiz.databinding.FragmentOtroInfoBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OtroInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtroInfo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentOtroInfoBinding

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
        binding = FragmentOtroInfoBinding.inflate(inflater, container, false)

        //Si no no se cargan los estilos del spannable
        binding.txtCerrarSesion.isAllCaps = false
        binding.txtSobreNosotros.isAllCaps = false

        //Creamos una variable con el texto que queremos añadirle los estilos
        val cerrarSesionSpan = SpannableStringBuilder(resources.getString(R.string.logOut))
        val sobreNosotrosSpan = SpannableStringBuilder(resources.getString(R.string.aboutUs))

        //Añadimos los estilos de subrayado
        cerrarSesionSpan.setSpan(UnderlineSpan(), 0, resources.getString(R.string.logOut).length, 0)
        sobreNosotrosSpan.setSpan(UnderlineSpan(), 0, resources.getString(R.string.aboutUs).length, 0)

        //Ponemos los nuevos textos en los correspondientes sitios
        binding.txtCerrarSesion.text = cerrarSesionSpan
        binding.txtSobreNosotros.text = sobreNosotrosSpan

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
         * @return A new instance of fragment OtroInfo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OtroInfo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}