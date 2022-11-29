package com.example.retomuzkiz.room

import android.app.Application
import androidx.room.Room

class UsuarioRoomApp: Application() {
    companion object {
        var database: UsuarioDB? = null
    }

    override fun onCreate() {
        super.onCreate()
        UsuarioRoomApp.database = Room
            .databaseBuilder(this,UsuarioDB::class.java,UsuarioDB.DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }
}