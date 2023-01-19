package com.example.retomuzkiz.room

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity (foreignKeys = [ForeignKey(entity = Usuario::class, parentColumns = arrayOf("userId"), childColumns = arrayOf("user"), onDelete = CASCADE)],
    )
data class Progress(
    @PrimaryKey
    var user : String,
    @ColumnInfo(name="totalPuntuation")
    var totalPuntuation: Int,
    @ColumnInfo(name="achievements")
    var achievements: Int,
    @ColumnInfo(name="gamesDone")
    var gamesDone: String
)
