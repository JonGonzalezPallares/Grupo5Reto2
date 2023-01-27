package com.example.retomuzkiz.burdinola.custom

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retomuzkiz.R
import com.example.retomuzkiz.burdinola.wordPlacement.Word

/**
 * Vista utilizada para mostrar el rompecabezas.
 */
class GridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), View.OnTouchListener {

    init {
        setOnTouchListener(this)
    }

    data class Tile(val row: Int, val column: Int, val character: Char)

    /**
     * Comunica score al fragmento de juego.
     */
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    var usedWordsList: List<Word> = emptyList()
    var tiles: MutableList<Tile> = mutableListOf()
    private val selectedTiles = mutableSetOf<Tile>()
    private val correctSelectedTiles = mutableSetOf<Tile>()
    private var stringSelection = ""
    private var validMove = false
    private var startX = 0f
    private var startY = 0f
    private var endX = 0f
    private var endY = 0f
    private var startTile = Tile(0, 0, ' ')


    private var selectedTilePaint = Paint().apply {
        textAlign = Paint.Align.CENTER
        style = Paint.Style.FILL
        textSize = 2f * Resources.getSystem().displayMetrics.scaledDensity
    }

    private val textPaint = Paint().apply {
        textAlign = Paint.Align.CENTER
        color = ContextCompat.getColor(context, R.color.black)
        textSize = 20f * Resources.getSystem().displayMetrics.scaledDensity
    }

    var data: List<List<Char>> = emptyList()
        set(value) {
            field = value
            tiles.clear()
            value.forEachIndexed { row, chars ->
                chars.forEachIndexed { column, char ->
                    tiles.add(Tile(row, column, char))
                }
            }
            invalidate()
        }

    private fun getTile(x: Float, y: Float): Tile {
        val column = (x / tileWidth()).toInt()
        val row = (y / tileHeight()).toInt()
        return tiles.first { it.column == column && it.row == row }
    }


    /**
     * Resalta los mosaicos seleccionados.
     * Colorea azul si la cadena seleccionada es correcta, colorea amarillo si el usuario aún está seleccionando mosaicos.
     */
    private fun highlightSelectedTiles(tile: Tile, canvas: Canvas, correct: Boolean) {
        canvas.drawRect(
            tile.column * tileWidth(),
            tile.row * tileHeight(),
            (tile.column +1) * tileWidth(),
            (tile.row + 1) * tileHeight(),
            selectedTilePaint.apply {
                color = if (correct) {
                    ContextCompat.getColor(context, R.color.botones)

                } else {
                    ContextCompat.getColor(context, R.color.botones_desactivados)
                }
            }
        )
    }

    /**
     * Pone caracteres, dibuja una cuadrícula y resalta las cadenas seleccionadas.
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        selectedTiles.forEach { tile ->
            if (validMove) {
                highlightSelectedTiles(tile, canvas, false)
            }
        }
        correctSelectedTiles.forEach { tile ->
            highlightSelectedTiles(tile, canvas, true)
        }
        tiles.forEach { tile ->
            canvas.drawText(
                tile.character.toString(),
                horizontalCenterOfTile(tile.column),
                verticalCenterOfTile(tile.row),
                textPaint
            )
        }
    }

    private fun horizontalCenterOfTile(column: Int) = (column * tileWidth()) + tileWidth() / 2
    private fun verticalCenterOfTile(row: Int) = (row * tileHeight()) + tileHeight() / 2

    private fun tileWidth() = width / data.size.toFloat()
    private fun tileHeight() = height / data.size.toFloat()

    /**
     * Selecciona los mosaicos donde el usuario tocó la pantalla. Una vez que el usuario levanta el dedo, pasa la cuerda.
     * seleccionado en el método wordFound.
     */


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
                startTile = getTile(startX, startY)
                validMove = true
                stringSelection = ""
                selectedTiles.clear()

            }
            MotionEvent.ACTION_MOVE -> {
                endX = event.x
                endY = event.y
                val endTile = getTile(endX, endY)
                if (startTile.row != endTile.row && startTile.column != endTile.column) {
                    validMove = false
                } else {
                    if (!selectedTiles.contains(endTile)) {
                        selectedTiles.add(endTile)
                        stringSelection += endTile.character
                    }
                }
                invalidate()

            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {

                if (validMove) {
                    if (wordFound(stringSelection)) {
                        selectedTiles.forEach { tile ->
                            correctSelectedTiles.add(tile)
                        }
                    }
                    selectedTiles.clear()
                    stringSelection = ""
                    invalidate()
                }
            }
        }
        return true
    }

    /**
     * Compara la cadena seleccionada por el usuario con las palabras utilizadas para construir el rompecabezas.
     * Si uno coincide, cambia el valor booleano de la palabra a verdadero e incrementa la puntuación en 1.
     * Devuelve un Toast basado en la palabra que ingresó el usuario.
     */
    private fun wordFound(stringSelected: String): Boolean {
        for (word in usedWordsList) {
            if (!word.found && word.word == stringSelected) {
                word.found = true
                _score.value = _score.value?.plus(1) ?: 1
                val congratulate = correctStringSelected.shuffled().first()
                Toast.makeText(context, congratulate, Toast.LENGTH_SHORT).show()
                return true
            }
            if (word.found && word.word == stringSelected) {
                val message = foundStringSelected.shuffled().first()
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                return false
            }
        }
        val shame = wrongStringSelected.shuffled().first()
        Toast.makeText(context, shame, Toast.LENGTH_SHORT).show()
        return false
    }

    /**
     * Lista de palabras/frases que se devolverán en el Toast.
     */
    private val correctStringSelected = listOf(
        "AHHH YEAH"
    )

    private val foundStringSelected = listOf(
        "encuentra algo mas"
    )

    private val wrongStringSelected = listOf(
        "leer el texto anterior"
    )
}