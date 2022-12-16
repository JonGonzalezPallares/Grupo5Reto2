package com.example.retomuzkiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retomuzkiz.databinding.ActivityProfesorModeBinding
import com.example.retomuzkiz.databinding.ActivityPuenteRomanoBinding

class ProfesorMode : AppCompatActivity() {

    private lateinit var binding : ActivityProfesorModeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfesorModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val duracion =Toast.LENGTH_LONG

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        binding.btnArenaRes.setOnClickListener{
            val toast = Toast.makeText(applicationContext, "Soluciones de Arena", duracion)
            toast.show()
        }

        binding.btnItsasRes.setOnClickListener{
            val toast = Toast.makeText(applicationContext, "Soluciones de itsaslur", duracion)
            toast.show()
        }

        binding.btnPuenteRes.setOnClickListener{
            val toast = Toast.makeText(applicationContext, "Soluciones de puente", duracion)
            toast.show()
        }
    }
}