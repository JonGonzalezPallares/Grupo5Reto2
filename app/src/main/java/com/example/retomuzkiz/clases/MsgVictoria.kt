package com.example.retomuzkiz.clases

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import com.example.retomuzkiz.MapsActivity

class MsgVictoria() {
    //Funcion para mostrar el dialog de victoria
    fun carga(context: Context) {
        val constructor: AlertDialog.Builder = AlertDialog.Builder(context)
        constructor.setTitle("¡VICTORIA!")
        constructor.setMessage("¡Bien hecho!\nHas ganado, sigue así")
        //Al pulsar el boton nos llevara al mapa de nuevo
        constructor.setPositiveButton("Ir al mapa"){_,_ ->
            val intento = Intent(context, MapsActivity::class.java)
            startActivity(context, intento, null)
        }
        constructor.show()
    }
}