package com.example.retomuzkiz.clases

import android.app.Application
import androidx.room.Room
import com.example.retomuzkiz.MapsActivity
import com.example.retomuzkiz.R
import com.example.retomuzkiz.room.*
import io.socket.client.IO
import io.socket.client.Socket
import java.nio.file.attribute.UserPrincipal

class RetoGrupoCinco : Application() {
    object SITESNAMES {
        var POBENA_FUNDICION_IMG = "fundicion_pobela"
        var POBENA_HERMITA_IMG = "irudiapobena1"
        var ITSASLUR_IBILBIDEA_IMG = "itsaslur1_2"
        var PLAYA_LA_ARENA_IMG = "irudia_arena_2"
        var PUENTE_ROMANO_IMG = "puentecompleto"
        var CASTILLO_MUNATONES_IMG = "irudia_pobena_1"
        var NOCHE_SAN_JUAN_IMG = "irudia_san_juan"
    }
    companion object {
        lateinit var prefs: Pref
        lateinit var database: AppDatabase
        lateinit var progressDb:ProgressDao
        lateinit var gameDb :GameDao
        lateinit var userDb :UsuarioDao
        var mSocket : Socket = IO.socket("https://servicioitsaslur.glitch.me/");



    }

    override fun onCreate() {
        super.onCreate()
        prefs = Pref(applicationContext)
        database = Room.databaseBuilder(this, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .allowMainThreadQueries()
            .build()

         progressDb = database.progressDao
         gameDb = database.gameDao
         userDb = database.usuarioDao
        dataInput()
    }

    private fun dataInput() {

        cargarJuegos()
        cargarUsers()
        cargarProgreso()
    }

    private fun cargarUsers() {

        val user =  Usuario("1", "Admin",  "AAAA", true)
        userDb.insertUser(user)
    }
    private fun cargarProgreso() {

        val progress =  Progress("1", 0, 0,0)

        progressDb.insertProgress(progress)
    }

    private fun cargarJuegos() {
        var gameList = listOf<Game>(
            Game(getString(R.string.gameSanJuan),1, false,SITESNAMES.NOCHE_SAN_JUAN_IMG),
            Game(getString(R.string.gameItsaslurIbilbidea), 2,false,SITESNAMES.ITSASLUR_IBILBIDEA_IMG),
            Game(getString(R.string.gamePuenteRomano), 3,false,SITESNAMES.PUENTE_ROMANO_IMG),
            Game(getString(R.string.gameFundicion),4, false,SITESNAMES.POBENA_FUNDICION_IMG),
            Game(getString(R.string.gameLaArenaHondartza),5, false,SITESNAMES.PLAYA_LA_ARENA_IMG),
            Game(getString(R.string.gameHermitaDePobeña), 6,false,SITESNAMES.POBENA_HERMITA_IMG),
            Game(getString(R.string.gameCastilloMuñatones),7, false,SITESNAMES.CASTILLO_MUNATONES_IMG))
        gameList.forEach(){
            gameDb.insertGame(it)
        }
    }

}
