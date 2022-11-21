package com.example.retomuzkiz.clases

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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
    //______________________________________________________________________________________________
    //metodo identifica la option selectionada
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.m_home -> Toast.makeText(this," home", Toast.LENGTH_SHORT).show()
            R.id.m_modo -> Toast.makeText(this," modo libre", Toast.LENGTH_SHORT).show()
            R.id.m_progreso -> Toast.makeText(this,"el segundo puesto te alcanza", Toast.LENGTH_SHORT).show()
            R.id.m_logout -> Toast.makeText(this," Logout", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
    //______________________________________________________________________________________________
}