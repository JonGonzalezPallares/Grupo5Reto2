package com.example.retomuzkiz.Burdinola

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ActivityBurdinolaSopaBinding
import com.example.retomuzkiz.databinding.ActivityBurdinolaVideoBinding

class BurdinolaSopaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBurdinolaSopaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_burdinola_sopa)
        binding = ActivityBurdinolaSopaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVolver.setOnClickListener{
            finish()
        }
    }
}