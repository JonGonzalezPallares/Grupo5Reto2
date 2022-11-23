package com.example.retomuzkiz.burdinola

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ActivityBurdinolaSopaBinding

class BurdinolaSopaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBurdinolaSopaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_burdinola_sopa)
        binding = ActivityBurdinolaSopaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para borrar la barra superior
        //this.supportActionBar!!.hide()

        binding.btnVolver.setOnClickListener{
            finish()
        }
    }
}