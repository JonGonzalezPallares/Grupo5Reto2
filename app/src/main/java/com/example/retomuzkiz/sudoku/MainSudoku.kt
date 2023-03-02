package com.example.retomuzkiz.sudoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.databinding.ActivityMainSudokuBinding
import com.example.retomuzkiz.sudoku.model.SudokuModel

class MainSudoku : AppCompatActivity() {
    private lateinit var binding: ActivityMainSudokuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainSudokuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        this.supportActionBar!!.hide()
        setContentView(binding.root)
        iniciar()
    }
    private fun iniciar() {
        //binding.btnStart.setOnClickListener {
            SudokuModel.sudoku.setDifficulty(SudokuModel.HARD)
            startActivity(Intent(this, GameActivity::class.java))
            finish()
        //}
    }
}