package com.example.retomuzkiz.laArenaHondartza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.retomuzkiz.databinding.ActivityIntroBinding
import com.example.retomuzkiz.laArenaHondartza.adapter.RvAdapterParrafos
import com.example.retomuzkiz.laArenaHondartza.modelo.ProveedorParrafos

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding
    private lateinit var adapter : RvAdapterParrafos
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityIntroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        recyclerview = binding.rvParrafos
        adapter = RvAdapterParrafos(ProveedorParrafos.listaParrafos,{
            salir(it)
        },{
            seguiente(it)
        },{
            atras(it)
        })
        recyclerview.adapter = adapter
    }

    private fun atras(it: Int) {
        if (it >= 1)
        recyclerview.scrollToPosition(it-1)
    }

    private fun seguiente(it: Int) {
        if(it < adapter.itemCount-1)
        recyclerview.scrollToPosition(it+1)
    }

    private fun salir(it: Int) {
        startActivity(Intent(this,LaArenaHondartza::class.java))
    }


}