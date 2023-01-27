

import com.example.retomuzkiz.burdinola.custom.Util
import com.example.retomuzkiz.burdinola.wordPlacement.PlacementType
import com.example.retomuzkiz.burdinola.wordPlacement.Word
import java.util.*

/**
 * Clase utilizada para crear el rompecabezas.
 * Crea una cuadrícula con 6 palabras elegidas al azar de una lista pasada al método createGrid.
 */
class WordSearch {

    /**
     * Diferentes tipos de ubicación que puede adoptar una palabra. Cuando se llama, se baraja para que las diferentes palabras se coloquen de diferentes maneras
     */
    private val placementTypes = arrayListOf(
        PlacementType.LeftRight,
        PlacementType.RightLeft,
        PlacementType.UpDown,
        PlacementType.DownUp
    )

    /**
     *  Devuelva una lista de palabras utilizadas para construir el rompecabezas.
     */
    val usedWordsList = MutableList(0) {
        Word(
            "",
            false
        )
    }

    /**
     * Rellena los espacios vacíos en la cuadrícula.
     */
    private fun fillSlots(grid: MutableList<MutableList<Char>>) {
        for (row in grid) {
            val rowIterator = row.listIterator()
            rowIterator.forEach { slot ->
                if (slot == ' ') {
                    rowIterator.set(Util.randomChar)
                }
            }
        }
    }

    /**
     * Haz la cuadrícula (lista 2d).
     * Primero crea una lista mutable 2d vacía, luego coloca todas las palabras que pueden encajar en ubicaciones aleatorias y finalmente llena los espacios vacíos.
     */
    fun createGrid(size: Int, words: List<String>): List<List<Char>> {
        val grid = MutableList(size) { MutableList(size) { ' ' } }

        placeWordList(words, size, grid)
        fillSlots(grid)
        return grid
    }

    /**
     * Busca una ranura en función de las coordenadas y el movimiento proporcionado.
     * Si la palabra encaja (por ejemplo, todos los espacios que ocupará están vacíos o ocupados por caracteres coincidentes), devolverá verdadero.
     */
    private fun findEmptySection(
        x: Int,
        y: Int,
        word: String,
        movement: IntArray,
        grid: MutableList<MutableList<Char>>
    ): Boolean {
        var xPosition = x
        var yPosition = y

        word.forEach { letter ->
            if (xPosition in 0 until grid.size && yPosition in 0 until grid.size) {
                val slot = grid[xPosition][yPosition]
                if (slot == ' ' || slot == letter) {
                    xPosition += movement[0]
                    yPosition += movement[1]
                } else {
                    return false
                }
            } else {
                return false
            }
        }

        return true
    }

    /**
     * Intenta insertar una palabra en una posición aleatoria en la cuadrícula.
     * Si findEmptySection devuelve verdadero, esto coloca la palabra en esa posición en la cuadrícula.
     * Dada una palabra y su tipo de ubicación, pasa ranura por ranura para verificar si la palabra se puede colocar y solo se detiene una vez que se ha hecho, o se agotan todas las posibilidades.
     * Rellenos usedWordsList las palabras, sus coordenadas de inicio y final en la cuadrícula.
     */
    private fun placeWord(word: String, movement: IntArray, size: Int, grid: MutableList<MutableList<Char>>): Boolean {

        val xLength = movement[0] * (word.length)
        val yLength = movement[1] * (word.length)

        val rows = (0..size).shuffled()
        val columns = (0..size).shuffled()

        for (row in rows) {
            for (column in columns) {
                val xFinal = xLength + row
                val yFinal = yLength + column

                if ((xFinal in 0..size) && (yFinal in 0..size)) {
                    if (findEmptySection(column, row, word, movement, grid)) {
                        var xPosition = column
                        var yPosition = row

                        word.forEach { letter ->
                            grid[xPosition][yPosition] = letter
                            xPosition += movement[0]
                            yPosition += movement[1]
                        }

                        usedWordsList.add(Word(word, false))

                        return true
                    }
                }
            }
        }
        return false
    }

    /**
     * Dada una lista de palabras, intenta colocar las palabras en la cuadrícula.
     * Pasa por los diferentes tipos de ubicación hasta que la palabra se coloca en la cuadrícula o se agotan todas las posibilidades.
     * La lista de palabras y los tipos de ubicación se mezclan.
     * Límite establecido en 6 palabras por rompecabezas.
     */
    private fun placeWordList(wordList: List<String>, size: Int, grid: MutableList<MutableList<Char>>) {
        val shuffledWordList = wordList.shuffled()
        var wordCount = 0

        for (word in shuffledWordList) {
            val formattedWord = word.uppercase(Locale.ROOT)
            val shuffledPlacementTypes = placementTypes.shuffled()
            for (placementType in shuffledPlacementTypes) {
                if (placeWord(formattedWord, movement(placementType), size, grid)) {
                    wordCount++
                    break
                }
            }
            if (wordCount == wordList.size) {
                break
            }
        }
    }

    /**
     * Movimientos asociados a cada tipo de colocación.
     */
    private fun movement(placementType: PlacementType): IntArray {
        return when (placementType) {
            PlacementType.LeftRight -> intArrayOf(1, 0)
            PlacementType.RightLeft -> intArrayOf(-1, 0)
            PlacementType.UpDown -> intArrayOf(0, 1)
            PlacementType.DownUp -> intArrayOf(0, -1)
        }
    }
}

