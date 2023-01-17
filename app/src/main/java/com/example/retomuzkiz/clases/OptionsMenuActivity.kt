package com.example.retomuzkiz.clases

import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.retomuzkiz.R

open class OptionsMenuActivity: AppCompatActivity() {
    //______________________________________________________________________________________________
    //metodo mostrar menu en la activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val miMenu= menuInflater
        //val guide = findViewById<View>(R.id.m_Modoguiado)
        val free = findViewById<View>(R.id.m_Modolibre)

        free.isEnabled= false
        miMenu.inflate(R.menu.menu,menu)
        return true
    }
}