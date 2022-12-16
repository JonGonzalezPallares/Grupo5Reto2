package com.example.retomuzkiz.room

import androidx.room.Database
import androidx.room.RoomDatabase




@Database(entities = [Usuario::class, Progress::class, Game::class],version = 1)

abstract class AppDatabase: RoomDatabase(){
    abstract val usuarioDao: UsuarioDao
    abstract val progressDao: ProgressDao
    abstract val gameDao: GameDao

    companion object{
        const val DATABASE_NAME = "appDatabase"
    }
}