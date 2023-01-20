package com.example.retomuzkiz

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.SystemClock
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.retomuzkiz.Laberinto.ActivityLaberinto
import com.example.retomuzkiz.clases.MsgVictoria
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.itsaslurIbilbidea.ItsaslurJuego
import com.example.retomuzkiz.room.Game
import com.example.retomuzkiz.room.Progress
import com.example.retomuzkiz.room.TypeConverter

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
var fin = 0
fun finalizar(contexto: Context, gameName:String) {
    if(fin == 1){
        val intento = Intent(contexto, MsgVictoria::class.java)
        intento.putExtra("imagen", gameName)
        ContextCompat.startActivity(contexto, intento, null)
        RetoGrupoCinco.mSocket.emit("game completed", currentProgress!!.totalPuntuation)
    }
}
var startTime: Long = 0
var time: Int = 0
var currentProgress: Progress? =  null
fun startTimer() {
    startTime = SystemClock.elapsedRealtime()
}

fun stopTimer() {
    val elapsedTime = SystemClock.elapsedRealtime() - startTime
    time = (elapsedTime/100).toInt()
    println("Elapsed time: $time cs")
}
fun juegoAcabado(gamePos : Int) {
    if (fin <1){
        var lista :List<Game>? = null
        currentProgress = RetoGrupoCinco.progressDb.getUserProgress(RetoGrupoCinco.currentUser!!.userId)
        when(gamePos){
            0->lista = calcularPuntuacionLaberinto(gamePos)
            1->lista = calcularPuntuacionItsaslur(gamePos)
            2->lista = calcularPuntuacionPuenteRomano(gamePos)
            3->lista = calcularPuntuacionFundicion(gamePos)
            4->lista = calcularPuntuacionLaArena(gamePos)
            5->lista = calcularPuntuacionHermita(gamePos)
            6->lista = calcularPuntuacionMuñatones(gamePos)



        }

        currentProgress!!.gamesDone = TypeConverter.someObjectListToString(lista)
        println(currentProgress)
        updateProgress()
    }

}

fun calcularPuntuacionMuñatones(gamePos: Int): List<Game>? {
    var puntuacion = 500 - time
    var totPuntuation = 0

    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)
    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach(){
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

fun calcularPuntuacionHermita(gamePos: Int): List<Game>? {
    var puntuacion = 500 - time
    var totPuntuation = 0

    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)
    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach(){
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

fun calcularPuntuacionLaArena(gamePos: Int): List<Game>? {
    var puntuacion = 500 - time
    var totPuntuation = 0

    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)
    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach(){
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

fun calcularPuntuacionFundicion(gamePos: Int): List<Game>? {
    var puntuacion = 500 - time
    var totPuntuation = 0

    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)
    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach(){
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

fun calcularPuntuacionPuenteRomano(gamePos: Int): List<Game>? {
    var puntuacion = 500 - time
    var totPuntuation = 0

    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)
    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach(){
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

fun calcularPuntuacionItsaslur(gamePos: Int): List<Game>? {
    var puntuacion = 500 - time
    var totPuntuation = 0

    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)
    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach(){
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

fun calcularPuntuacionLaberinto(gamePos: Int): List<Game> {
    var puntuacion = 500 - time
    var totPuntuation = 0

    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)
    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach(){
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

private fun updateProgress() {
    RetoGrupoCinco.progressDb.updateProgress(currentProgress!!)
}