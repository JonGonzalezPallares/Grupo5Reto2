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
    //Variable para saber cuando se tiene que cerrar y cuando no
    private var cambio = false

    //______________________________________________________________________________________________
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityIntroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        init()
    }

    //______________________________________________________________________________________________
    // Inicializar  recyclerview adapter
    private fun init() {
        recyclerview = binding.rvParrafos
        adapter = RvAdapterParrafos(ProveedorParrafos.listaParrafos,{
            play()
        },{
            nextItem(it)
        },{
            previusItem(it)
        })

        recyclerview.adapter = adapter
    }

    //______________________________________________________________________________________________
    // Retroceder hacia un item atras si la posicion del item es mayor que cero
    private fun previusItem(item: Int) {
        recyclerview.scrollToPosition(item-1)
    }

    //______________________________________________________________________________________________
    // Avanzar hacia un item adelante si la posicion del item es mayor que cero
    private fun nextItem(item: Int) {
        recyclerview.scrollToPosition(item+1)
    }

    //______________________________________________________________________________________________
    // Llamar a la activity LaArenaHondartza
    private fun play() {
        cambio = true
        startActivity(Intent(this,LaArenaHondartza::class.java))
    }


    //Al poner esta actividad en pausa (al abrir otra diferente), para que no pulsemos hacia atras y nos lleve a esta directamente
    override fun onPause() {
        super.onPause()
        if(cambio){
            finish()
        }
    }
}