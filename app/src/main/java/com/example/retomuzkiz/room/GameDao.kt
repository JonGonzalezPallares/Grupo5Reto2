package com.example.retomuzkiz.room

import androidx.room.*

@Dao
interface GameDao {
    //Listar todos los usuarios
    @Query("select * from Game")
    fun getAllGames(): List<Game>

    //Listar usuario en concreto
    @Query("select * from Game where gameName like :name")
    fun getGame(name: String): Game

    //Insertar Usuario
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: Game)

    //Eliminaar Usuario
    @Delete
    fun deleteGame(game: Game)

    //Actualizar Usuario
    @Update
    fun updateGame(game: Game)
}
