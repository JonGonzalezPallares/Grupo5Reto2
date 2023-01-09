package com.example.retomuzkiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.databinding.ActivityCrearClaseSocketBinding
import com.example.retomuzkiz.databinding.FragmentMobileRotationBinding

class ActivityCrearClaseSocket : AppCompatActivity() {
    private lateinit var binding: ActivityCrearClaseSocketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCrearClaseSocketBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}