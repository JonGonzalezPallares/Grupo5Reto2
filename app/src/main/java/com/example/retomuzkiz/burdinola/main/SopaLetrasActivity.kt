package com.example.retomuzkiz.burdinola.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retomuzkiz.R
import com.example.retomuzkiz.burdinola.game.GameFragment


class SopaLetrasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.juego_sopa)
        val exampleFragment = GameFragment(this)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_prencipal, exampleFragment)
            .commit()
    }
}
