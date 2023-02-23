package com.example.retomuzkiz

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.retomuzkiz.clases.MsgVictoria
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.room.Game
import com.example.retomuzkiz.room.Progress
import com.example.retomuzkiz.room.TypeConverter
import com.example.retomuzkiz.room.Usuario
import com.example.retomuzkiz.usuario.currentUser

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
    builder.setPositiveButton ("Ok") { _: DialogInterface, _: Int ->}
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
var puntuacion = 0

object usuario  {
    lateinit var currentUser:Usuario
}

lateinit var currentProgress: Progress

fun startTimer() {
    startTime = SystemClock.elapsedRealtime()
}

fun stopTimer() {
    val elapsedTime = SystemClock.elapsedRealtime() - startTime
    time = (elapsedTime/100).toInt()
}

fun juegoAcabado(gamePos : Int) {
    if (fin <1){
        var lista :List<Game>? = null

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
        updateProgress()
        comprobarTopTres()
    }
}

fun comprobarTopTres() {
    RetoGrupoCinco.mSocket.emit("set ranking", currentUser!!.userId, currentUser!!.name, currentUser!!.userClass, currentProgress!!.totalPuntuation,)
    RetoGrupoCinco.mSocket.on("top Three"){}
}

fun calcularPuntuacionMuñatones(gamePos: Int): List<Game>? {
    calcularPuntuacionTiempo(false)

    var totPuntuation = 0
    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)

    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach {
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

fun calcularPuntuacionHermita(gamePos: Int): List<Game>? {
    calcularPuntuacionTiempo(false)

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
    calcularPuntuacionTiempo(false)

    var totPuntuation = 0
    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)

    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach {
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

fun calcularPuntuacionFundicion(gamePos: Int): List<Game>? {
    calcularPuntuacionTiempo(true)

    var totPuntuation = 0
    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)

    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach {
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

fun calcularPuntuacionPuenteRomano(gamePos: Int): List<Game>? {
    calcularPuntuacionTiempo(false)

    var totPuntuation = 0
    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)

    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach {
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

fun calcularPuntuacionItsaslur(gamePos: Int): List<Game>? {
    calcularPuntuacionTiempo(false)

    var totPuntuation = 0

    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)
    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach {
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

fun calcularPuntuacionLaberinto(gamePos: Int): List<Game> {
    calcularPuntuacionTiempo(false)

    var totPuntuation = 0

    var list = TypeConverter.stringToSomeObjectList(currentProgress!!.gamesDone)
    if (!list[gamePos].done || puntuacion > list[gamePos].puntuacion){
        list[gamePos].puntuacion = puntuacion
        list.forEach {
            totPuntuation += it.puntuacion
        }
        currentProgress!!.totalPuntuation =  totPuntuation
        list[gamePos].done = true
    }
    return list
}

fun calcularPuntuacionTiempo(sifundicion : Boolean){
    puntuacion = if(sifundicion){
        if(time>1000){
            0
        }else {
            1000 - time
        }
    }else{
        if(time>500){
            0
        }else {
            500 - time
        }
    }

}

private fun updateProgress() {
    RetoGrupoCinco.progressDb.updateProgress(currentProgress!!)
}

fun puntuacionJuegos():String{
    return puntuacion.toString()
}

fun dialogoAyudaJuegos (juego : String, context: Context,layoutInflater: LayoutInflater){
    val builder = AlertDialog.Builder(context)
    val view = layoutInflater.inflate(R.layout.ventanayuda,null)

    builder.setView(view)

    val dialog = builder.create()
    dialog.show()

    val titulo = view.findViewById<TextView>(R.id.txtnombrejuego)
    val img = view.findViewById<ImageView>(R.id.imagenjuego)
    val explicacion = view.findViewById<TextView>(R.id.txtexplicacion)
    val boton = view.findViewById<Button>(R.id.btncerrarayuda)
    boton.setOnClickListener {
        dialog.hide()
    }

    when(juego){
        "castillo"->{
            titulo.text = context.resources.getString(R.string.gameCastilloMuñatones)
            cargargifs(img,ContextCompat.getDrawable(context, R.drawable.ayudacastillo)!!,context)
            explicacion.text = context.resources.getString(R.string.Ayudacastillo)
        }

        "playa"->{
            titulo.text = context.resources.getString(R.string.gameLaArenaHondartza)
            cargargifs(img,ContextCompat.getDrawable(context, R.drawable.laarenaayuda)!!,context)
            explicacion.text = context.resources.getString(R.string.Ayudaplaya)
        }

        "fundicion"->{

        }

        "sanjuan"->{
            titulo.text = context.resources.getString(R.string.gameSanJuan)
            cargargifs(img,ContextCompat.getDrawable(context, R.drawable.lavertintoayuda)!!,context)
            explicacion.text = context.resources.getString(R.string.Ayudalab)
        }

        "puente"->{
            titulo.text = context.resources.getString(R.string.gamePuenteRomano)
            cargargifs(img,ContextCompat.getDrawable(context, R.drawable.puente)!!,context)
            explicacion.text = context.resources.getString(R.string.Ayudapuente)
        }

        "ermita"->{
            titulo.text = context.resources.getString(R.string.gameHermitaDePobeña)
            cargargifs(img,ContextCompat.getDrawable(context, R.drawable.ermitaayuda)!!,context)
            explicacion.text = context.resources.getString(R.string.Ayudaermita)
        }

        "paseo"->{
            titulo.text = context.resources.getString(R.string.gameItsaslurIbilbidea)
            cargargifs(img,ContextCompat.getDrawable(context, R.drawable.ayudapaseo)!!,context)
            explicacion.text = context.resources.getString(R.string.Ayudapaseo)
        }
    }
}

fun cargargifs(img : ImageView, draw : Drawable,context: Context) {
    Glide.with(context).load(draw).into(img)
}

fun setUser(user: Usuario) {
    currentUser = user
    if((RetoGrupoCinco.progressDb.getUserProgress(currentUser!!.userId) != null)){
        currentProgress = RetoGrupoCinco.progressDb.getUserProgress(currentUser!!.userId)
    }
}