package com.example.retomuzkiz.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsuarioDao {
     //Listar todos los usuarios
     @Query("select * from Usuario")
     fun getAllUsers(): List<Usuario>

     //Listar usuario en concreto
     @Query("select * from Usuario where nombre like :nombre")
     fun getUser(nombre: String): Usuario

     //Insertar Usuario
     @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertUser(usuario: Usuario)

     //Eliminaar Usuario
     @Delete
     fun deleteUser(usuario: Usuario)

     //Actualizar Usuario
     @Update
     fun updateUser(usuario: Usuario)
}