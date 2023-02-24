package com.example.retomuzkiz.burdinola.game


import WordSearch
import androidx.lifecycle.ViewModel
import com.example.retomuzkiz.YourProgress.binding
import com.example.retomuzkiz.burdinola.wordPlacement.Word

class GameViewModel : ViewModel() {

    private val wordList = listOf(
        "Barbadun",
        "Errementari",
        "Labea",
        "Sutegia",
        "Burdinola",
        "Errota",
        "Salazar",
        "Tresnak"
    )

    private val gridSize = 12

    lateinit var grid: List<List<Char>>
    lateinit var usedWordsList: List<Word>
    lateinit var usedWordString: String

    init {
        createPuzzle(gridSize, wordList)
    }

    /**
     * Devuelve el rompecabezas, una lista y una cadena de palabras utilizadas.
     */
    private fun createPuzzle(size: Int, words: List<String>) {
        val wordSearch = WordSearch()
        grid = wordSearch.createGrid(size, words)
        usedWordsList = wordSearch.usedWordsList
        usedWordString = "Barbadun, Errementari, Labea, Sutegia, Burdinola, Errota, Salazar, Tresnak"
        /*wordSearch.usedWordsList.forEachIndexed { index, word ->
            usedWordString += if (index == 0) {
                " ${word.word}"
            } else {
                ", ${word.word}"
            }
        }*/
    }
}