package com.example.retomuzkiz.puenteRomano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ActivityPuenteJuegoBinding

class PuenteJuego : AppCompatActivity() {

    private lateinit var binding: ActivityPuenteJuegoBinding
    private var cantidad = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuenteJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Si es el primer set de preguntas
        binding.rdbRes1.setOnCheckedChangeListener { group, id ->
            when(id){
                R.id.rb_res1_1 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_1).text.toString()
                    println(texto)
                }
                R.id.rb_res1_2 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_2).text.toString()
                    println(texto)
                }
                R.id.rb_res1_3 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_3).text.toString()
                    println(texto)
                }
            }
        }

        //Si es el segundo set de preguntas
        binding.rdbRes2.setOnCheckedChangeListener { group, id ->
            when(id){
                R.id.rb_res2_1 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_1).text.toString()
                    println(texto)
                }
                R.id.rb_res2_2 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_2).text.toString()
                    println(texto)
                }
                R.id.rb_res2_3 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_3).text.toString()
                    println(texto)
                }
            }
        }

        //Si es el tercer set de preguntas
        binding.rdbRes3.setOnCheckedChangeListener { group, id ->
            when(id){
                R.id.rb_res3_1 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_1).text.toString()
                    println(texto)
                }
                R.id.rb_res3_2 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_2).text.toString()
                    println(texto)
                }
                R.id.rb_res3_3 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_3).text.toString()
                    println(texto)
                }
            }
        }

        //Si es el cuarto set de preguntas
        binding.rdbRes4.setOnCheckedChangeListener { group, id ->
            when(id){
                R.id.rb_res4_1 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_1).text.toString()
                    println(texto)
                }
                R.id.rb_res4_2 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_2).text.toString()
                    println(texto)
                }
                R.id.rb_res4_3 -> {
                    val texto = findViewById<RadioButton>(R.id.rb_res1_3).text.toString()
                    println(texto)
                }
            }
        }

        //Cambia la visibilidad de las preguntas
        binding.btnComprobar.setOnClickListener {
            when (cantidad) {
                1 -> {
                    binding.TrozoPreg1.visibility = View.GONE
                    binding.TrozoPreg2.visibility = View.VISIBLE
                }
                2 -> {
                    binding.TrozoPreg2.visibility = View.GONE
                    binding.TrozoPreg3.visibility = View.VISIBLE
                }
                3 -> {
                    binding.TrozoPreg3.visibility = View.GONE
                    binding.TrozoPreg4.visibility = View.VISIBLE
                }
            }
            cantidad++
        }
    }
}