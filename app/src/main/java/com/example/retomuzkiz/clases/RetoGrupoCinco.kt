package com.example.retomuzkiz.clases

import android.app.Application
import androidx.room.Room
import com.example.retomuzkiz.room.UsuarioDB

class RetoGrupoCinco : Application() {

    companion object {
        lateinit var prefs: Pref
        var database: UsuarioDB? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Pref(applicationContext)
        database = Room
            .databaseBuilder(this,UsuarioDB::class.java,UsuarioDB.DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }
}
