 package com.example.retomuzkiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.retomuzkiz.databinding.ActivityProgresBinding


 lateinit var binding :ActivityProgresBinding


 class ProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityProgresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarImagenes()

    }

     private fun cargarImagenes() {
         binding.imgPuenteRomano.setImageDrawable(
             ContextCompat.getDrawable(
                 this,
                 R.drawable.puentecompleto
             )

         )
     }
 }