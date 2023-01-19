package com.example.retomuzkiz.room

import androidx.room.*

@Dao
interface GameDao {
    //Listar todos los juegos
    @Query("select * from Game")
    fun getAllGames(): List<Game>

    //Listar juegos en concreto
    @Query("select * from Game where gameName like :name")
    fun getGame(name: String): Game

    //Insertar juegos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: Game)

    //Eliminaar juegos
    @Delete
    fun deleteGame(game: Game)

    //Actualizar juegos
    @Update
    fun updateGame(game: Game)
}
