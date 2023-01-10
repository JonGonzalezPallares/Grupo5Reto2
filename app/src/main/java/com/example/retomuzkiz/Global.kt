package com.example.retomuzkiz

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

/**
 * Muestra un dialogo con un mensaje y un texto
 *
 * @param context Contexto donde se lanza el dialogo de mensaje
 * @param text Texto del mensaje a mostrar
 * @param title Titulo del mensaje
 */
fun showDialog (context : Context, text : String, title : String = "") {
    val builder : AlertDialog.Builder = AlertDialog.Builder (context)
    builder.setTitle (title)
    builder.setMessage (text)
    builder.setPositiveButton ("Ok",
        DialogInterface.OnClickListener (function = {
                dialog : DialogInterface, which : Int ->
        }))
    builder.show ()
}