package com.example.retomuzkiz.clases

import android.content.Context
import android.content.SharedPreferences

class Pref(val context: Context) {
    val SHARED_DATA = "MySharedPreferences"
    val SHARED_USER = "usuario"
    val SHARED_ID = "id"


    private val storage: SharedPreferences = context.getSharedPreferences(SHARED_DATA,0)

    // Guarda usuario
    fun saveUser(usuario: String) {
        storage.edit().putString(SHARED_USER, usuario).apply()
    }

    // Guarda id usuario
    fun saveId(id: Int) {
        storage.edit().putString(SHARED_ID, id.toString()).apply()
    }

    // recuperar usuario
    fun getUser(): String {
        return storage.getString(SHARED_USER,"")!!
    }
    // recuperar id
    fun getId(): String {
        return storage.getString(SHARED_ID,"")!!
    }

    // Eleminar los datos guardados
    fun limpiar(){
        storage.edit().clear().apply()
    }
}
