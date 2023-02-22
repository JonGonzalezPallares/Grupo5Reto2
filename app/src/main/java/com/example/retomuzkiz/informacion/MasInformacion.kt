package com.example.retomuzkiz.informacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.databinding.ActivityMasInformacionBinding
import com.example.retomuzkiz.funcionesExtension.cambiarF
import com.google.android.material.tabs.TabLayout

class MasInformacion : AppCompatActivity() {
    private lateinit var binding : ActivityMasInformacionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMasInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pestanias.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.text.toString()){
                    "MODOS" -> ModosInfo().cambiarF(binding.fragmentContainerView3.id, supportFragmentManager)
                    "PUNTUACION" -> PuntuacionInfo().cambiarF(binding.fragmentContainerView3.id, supportFragmentManager)
                    "OTRO" -> OtroInfo().cambiarF(binding.fragmentContainerView3.id, supportFragmentManager)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}