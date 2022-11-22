package com.example.retomuzkiz.clases

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.retomuzkiz.R

open class OptionsMenuActivity: AppCompatActivity() {
    //______________________________________________________________________________________________
    //metodo mostrar menu en la activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val miMenu= menuInflater
        miMenu.inflate(R.menu.menu,menu)
        return true
    }
}