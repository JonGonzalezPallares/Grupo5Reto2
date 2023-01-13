package com.example.retomuzkiz.ponekakoermita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.retomuzkiz.databinding.ActivityMarineroBinding

class MarineroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarineroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        super.onCreate(savedInstanceState)

        binding = ActivityMarineroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnsiguiente.setOnClickListener {
            val actividad = Intent(this, JuegodemarActivity::class.java)
            ContextCompat.startActivity(this, actividad, null)
        }

        binding.button2.setOnClickListener {
            binding.txt1.visibility = View.GONE
            binding.txt2.visibility = View.VISIBLE
            binding.btnsiguiente.visibility = View.VISIBLE
            it.visibility = View.GONE
        }
    }
}