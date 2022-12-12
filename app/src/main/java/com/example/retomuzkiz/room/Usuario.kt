package com.example.retomuzkiz.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = false)
    var nombre: String,

    @ColumnInfo(name = "puntos")
    var puntos: Int,

    @ColumnInfo(name = "juegos_completados")
    var juegosCompletados: Int
)
