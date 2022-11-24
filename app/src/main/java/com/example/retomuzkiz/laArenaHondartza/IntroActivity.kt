package com.example.retomuzkiz.laArenaHondartza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.retomuzkiz.databinding.ActivityIntroBinding
import com.example.retomuzkiz.laArenaHondartza.adapter.RvAdapterParrafos
import com.example.retomuzkiz.laArenaHondartza.modelo.ProveedorParrafos

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding
    private lateinit var adapter : RvAdapterParrafos
    private lateinit var recyclerview: RecyclerView

    //______________________________________________________________________________________________
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

    //______________________________________________________________________________________________
    // retrocede hacia un item atras si la posicion del item es mayor que cero
    private fun atras(item: Int) {
        recyclerview.scrollToPosition(item-1)
    }

    //______________________________________________________________________________________________
    // avanza hacia un item adelante si la posicion del item es mayor que cero
    private fun seguiente(item: Int) {
        recyclerview.scrollToPosition(item+1)
    }

    //______________________________________________________________________________________________
    private fun salir(it: Int) {
        startActivity(Intent(this,LaArenaHondartza::class.java))
    }


}