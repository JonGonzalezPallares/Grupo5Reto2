package com.example.retomuzkiz.room

import androidx.room.*

@Entity (tableName = "Usuario",foreignKeys =
    [ForeignKey(entity = Progress::class, parentColumns = arrayOf("totalPuntuation"),
      childColumns = arrayOf("totPuntuation"), onDelete = ForeignKey.CASCADE)],
    primaryKeys = arrayOf("userId", "totPuntuation"),
    indices = [Index(
        value = arrayOf("userId"),
        unique = true
        )])


data class Usuario(

    val userId: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "totPuntuation")
    var totPuntuation: Int,
    @ColumnInfo(name = "userClass")
    var userClass: String,
    @ColumnInfo(name = "gamesDone")
    var gamesDone: Int,

    @ColumnInfo(name = "isProfessor")
    var isProfessor: Boolean,

    )
