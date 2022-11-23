package com.example.retomuzkiz.ponekacoermita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.R

class juegodemarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        this.supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juegodemar)

    }
}