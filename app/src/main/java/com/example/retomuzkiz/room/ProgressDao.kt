package com.example.retomuzkiz.room

import androidx.room.*

@Dao
interface ProgressDao{
    //Listar todos los usuarios
    @Query("select * from Progress")
    fun getAllUsersProgress(): List<Progress>

    //Listar usuario en concreto
    @Query("select * from Progress where user like :userId")
    fun getUserProgress(userId: String): Progress

//    Insertar Usuario
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertUser(usuario: Usuario)

    //Eliminaar Usuario
    @Delete
    fun deleteUserProgress(progress: Progress)

    //Actualizar Usuario
    @Update
    fun updateProgress(progress: Progress)
}
