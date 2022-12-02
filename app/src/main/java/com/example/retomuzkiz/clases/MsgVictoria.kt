package com.example.retomuzkiz.clases

import android.content.Context
import androidx.appcompat.app.AlertDialog

class MsgVictoria() {
    //Funcion para mostrar el dialog de victoria
    fun carga(context: Context) {
        val constructor: AlertDialog.Builder = AlertDialog.Builder(context)
            .setTitle("¡VICTORIA!")
            .setMessage("¡Bien hecho!\nHas ganado, sigue así")
            //Al pulsar el boton nos llevara al mapa de nuevo
            .setPositiveButton("Ir al mapa"){_,_ ->
                return@setPositiveButton
            }
            .setCancelable(false)
        constructor.show()
    }
}