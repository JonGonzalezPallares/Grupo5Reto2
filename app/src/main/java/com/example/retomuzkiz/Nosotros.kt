package com.example.retomuzkiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.databinding.ActivityNosotrosBinding

class Nosotros : AppCompatActivity() {

    private lateinit var binding : ActivityNosotrosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNosotrosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        binding.btnVolver.setOnClickListener {
            val intento = Intent(this, MapsActivity::class.java)
            this.finish()
            startActivity(intento)
        }
    }
}