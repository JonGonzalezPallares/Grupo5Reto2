package com.example.retomuzkiz.room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Game(
    @PrimaryKey
    var gameName : String,
    var gameId: Int,
   // var achievement: String,
    var done: Boolean,
    var res: String

)
