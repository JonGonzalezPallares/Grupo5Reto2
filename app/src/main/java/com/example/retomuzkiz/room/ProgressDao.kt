package com.example.retomuzkiz.room

import androidx.room.*

@Dao
interface ProgressDao{
    //Listar todos los usuarios
    @Query("select * from Progress")
    fun getAllUsersProgress(): List<Progress>

    //Listar progreso en concreto
    @Query("select * from Progress where user like :userId")
    fun getUserProgress(userId: String): Progress

    //Insertar progreso
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProgress(progress: Progress)

    //Eliminaar Usuario
    @Delete
    fun deleteUserProgress(progress: Progress)

    //Actualizar Usuario
    @Update
    fun updateProgress(progress: Progress)
}
