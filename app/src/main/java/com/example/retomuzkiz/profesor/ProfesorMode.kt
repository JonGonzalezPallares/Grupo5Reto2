package com.example.retomuzkiz.profesor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.retomuzkiz.CargandoDirections
import com.example.retomuzkiz.databinding.ActivityProfesorModeBinding

class ProfesorMode : AppCompatActivity() {

    private lateinit var binding : ActivityProfesorModeBinding
    //Variable para saber en que fragmento nos hemos quedado
    private var regresar = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfesorModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        //Asi obtenemos el navController para poder cambiar entre fragmentos mediante el nav graph
        //binding.fragmentos.id -> contenedor donde se van a cargar los diferentes fragmentos
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentos.id) as NavHostFragment

        //Guardamos el controlador en una variable
        val navController = navHostFragment.navController

        binding.btnPuenteRes.setOnClickListener {
            //Vamos desde el fragmento vacio, hasta el puente

            navController.navigate(CargandoDirections.cargatoPuente())

            //Cambiamos el valor para saber que estamos en el puente
            regresar = "Puente"

            oscurecer(true)
        }

        binding.btnItsasRes.setOnClickListener {
            //Vamos desde el fragmento vacio, hasta itsaslur

            navController.navigate(CargandoDirections.cargatoItsaslur())

            //Cambiamos el valor para saber que estamos en itsaslur
            regresar = "Itsaslur"

            oscurecer(true)
        }

        binding.btnCastilloRes.setOnClickListener {
            //Vamos desde el fragmento vacio, hasta el castillo

            navController.navigate(CargandoDirections.cargatoCastillo())

            //Cambiamos el valor para saber que estamos en el castillo
            regresar = "Castillo"

            oscurecer(true)
        }

        binding.btnHermitaRes.setOnClickListener {
            //Vamos desde el fragmento cargando, hasta la hermita

            navController.navigate(CargandoDirections.cargatoHermita())

            //Cambiamos el valor para saber que estamos en la hermita
            regresar = "Hermita"

            oscurecer(true)
        }

        binding.btnOcultar.setOnClickListener {
            regresar(navController)

            //Para cuando se le pulsa el boton de volver hacia atras
            regresar = "vuelto"
            oscurecer(false)
        }
    }

    //Funcion para regresar al fragmento de cargando
    private fun regresar(navController: NavController) {
        //Segun con que texto venga
        when(regresar){
            "Puente" -> {
                navController.navigate(PuenteRespuestasDirections.regresoPuente())
            }
            "Itsaslur" -> {
                navController.navigate(ItsaslurRespuestasDirections.regresoItsaslur())
            }
            "Castillo" -> {
                navController.navigate(CastilloRespuestasDirections.regresoCastillo())
            }
            "Hermita" -> {
                navController.navigate(HermitaRespuestasDirections.regresoHermita())
            }
        }
    }

    //Funcion para oscurecer el fondo y desactivar los botones
    private fun oscurecer(ocultado: Boolean) {
        if(ocultado) {
            //Hacemos visible la zona de las respuestas
            binding.vistaRespuestas.visibility = View.VISIBLE

            binding.cuerpo.alpha = 0.5F
            binding.btnCastilloRes.isEnabled = false
            binding.btnHermitaRes.isEnabled = false
            binding.btnPuenteRes.isEnabled = false
            binding.btnItsasRes.isEnabled = false
        }else{
            //Hacemos invisible la zona de las respuestas
            binding.vistaRespuestas.visibility = View.GONE

            binding.cuerpo.alpha = 1F
            binding.btnCastilloRes.isEnabled = true
            binding.btnHermitaRes.isEnabled = true
            binding.btnPuenteRes.isEnabled = true
            binding.btnItsasRes.isEnabled = true
        }
    }
}