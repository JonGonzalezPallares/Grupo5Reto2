package com.example.retomuzkiz.profesor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.retomuzkiz.databinding.ActivityProfesorModeBinding

class ProfesorMode : AppCompatActivity() {

    private lateinit var binding : ActivityProfesorModeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfesorModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val duracion =Toast.LENGTH_LONG

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        binding.btnPuenteRes.setOnClickListener {
            //binding.contenedorFrag = contenedor donde se van a cargar todos los fragmentos
            //PuenteRespuestas() = siendo el fragmento que queremos cargar
            binding.contenedorFrag.changeFrag(PuenteRespuestas())

            oscurecer(true)
        }

        binding.btnItsasRes.setOnClickListener {
            //binding.contenedorFrag = contenedor donde se van a cargar todos los fragmentos
            //ItsaslurRespuestas() = siendo el fragmento que queremos cargar
            binding.contenedorFrag.changeFrag(ItsaslurRespuestas())

            oscurecer(true)
        }

        binding.btnCastilloRes.setOnClickListener {
            //binding.contenedorFrag = contenedor donde se van a cargar todos los fragmentos
            //CastilloRespuestas() = siendo el fragmento que queremos cargar
            binding.contenedorFrag.changeFrag(CastilloRespuestas())

            oscurecer(true)
        }

        binding.btnHermitaRes.setOnClickListener {
            //binding.contenedorFrag = contenedor donde se van a cargar todos los fragmentos
            //HermitaRespuestas() = siendo el fragmento que queremos cargar
            binding.contenedorFrag.changeFrag(HermitaRespuestas())

            oscurecer(true)
        }

        binding.btnOcultar.setOnClickListener {
            oscurecer(false)
        }
    }

    //Funcion para oscurecer el fondo y desactivar los botones
    private fun oscurecer(ocultado: Boolean) {
        if(ocultado) {
            //Hacemos visible la zona de las respuestas
            binding.vistaRespuestas.visibility = View.VISIBLE

            binding.cuerpo.alpha = 0.5F
            binding.txtClaseRoom.isEnabled = false
            binding.btnCastilloRes.isEnabled = false
            binding.btnHermitaRes.isEnabled = false
            binding.btnPuenteRes.isEnabled = false
            binding.btnItsasRes.isEnabled = false
        }else{
            //Hacemos invisible la zona de las respuestas
            binding.vistaRespuestas.visibility = View.GONE

            binding.cuerpo.alpha = 1F
            binding.txtClaseRoom.isEnabled = true
            binding.btnCastilloRes.isEnabled = true
            binding.btnHermitaRes.isEnabled = true
            binding.btnPuenteRes.isEnabled = true
            binding.btnItsasRes.isEnabled = true
        }
    }

    //Añadimos una funcion de extension a los FragmentContainerView
    private fun FragmentContainerView.changeFrag(fragmento: Fragment) {
        //Creamos una variable para la transaccion
        val transicion = supportFragmentManager.beginTransaction().setReorderingAllowed(true)
        //Al usar funciones de extension ya sabemos en que fragmento estamos, asi que solamente tenemos que pasarle el id y el fragmento a cambiar
        transicion.replace(this.id, fragmento)
        //Hacemos el cambio
        transicion.commit()
    }
}