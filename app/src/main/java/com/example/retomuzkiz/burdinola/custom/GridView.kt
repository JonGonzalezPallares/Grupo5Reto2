package com.example.retomuzkiz.burdinola.custom

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
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
import kotlin.random.Random

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
    private val correctSelectedTilesBar = mutableSetOf<Tile>()
    private val correctSelectedTilesErre = mutableSetOf<Tile>()
    private val correctSelectedTilesLab = mutableSetOf<Tile>()
    private val correctSelectedTilesSut = mutableSetOf<Tile>()
    private val correctSelectedTilesBur = mutableSetOf<Tile>()
    private val correctSelectedTilesErro = mutableSetOf<Tile>()
    private val correctSelectedTilesSala = mutableSetOf<Tile>()
    private val correctSelectedTilesTre = mutableSetOf<Tile>()
    private var stringSelection = ""
    private var validMove = false
    private var startX = 0f
    private var startY = 0f
    private var endX = 0f
    private var endY = 0f
    private var startTile = Tile(0, 0, ' ')
    private var color1 = 0
    private var color2 = 0
    private var color3 = 0
    private var coloresBar = mutableListOf<Int>()
    private var coloresErre = mutableListOf<Int>()
    private var coloresLab = mutableListOf<Int>()
    private var coloresSut = mutableListOf<Int>()
    private var coloresBur = mutableListOf<Int>()
    private var coloresErro = mutableListOf<Int>()
    private var coloresSala = mutableListOf<Int>()
    private var coloresTre = mutableListOf<Int>()

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
        //verificar que x e y estén dentro del rango válido
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Tile(0, 0, ' ')
        }
        val column = (x / tileWidth()).toInt()
        val row = (y / tileHeight()).toInt()
        return tiles.first { it.column == column && it.row == row }
    }

    /**
     * Resalta los mosaicos seleccionados.
     * Colorea azul si la cadena seleccionada es correcta, colorea amarillo si el usuario aún está seleccionando mosaicos.
     */
    private fun highlightSelectedTiles(tile: Tile, canvas: Canvas) {
        canvas.drawRect(
            tile.column * tileWidth(),
            tile.row * tileHeight(),
            (tile.column +1) * tileWidth(),
            (tile.row + 1) * tileHeight(),
            selectedTilePaint.apply {
                this.setARGB(255, color1, color2, color3)
            }
        )
    }

    /**
     * Por cada palabra se le da un color de fondo diferente
     */
    private fun highlightSelectedTilesBar(tile: Tile, canvas: Canvas) {
        canvas.drawRect(
            tile.column * tileWidth(),
            tile.row * tileHeight(),
            (tile.column +1) * tileWidth(),
            (tile.row + 1) * tileHeight(),
            selectedTilePaint.apply {
                this.setARGB(255, coloresBar[0], coloresBar[1], coloresBar[2])
            }
        )
    }
    private fun highlightSelectedTilesErre(tile: Tile, canvas: Canvas) {
        canvas.drawRect(
            tile.column * tileWidth(),
            tile.row * tileHeight(),
            (tile.column +1) * tileWidth(),
            (tile.row + 1) * tileHeight(),
            selectedTilePaint.apply {
                this.setARGB(255, coloresErre[0], coloresErre[1], coloresErre[2])
            }
        )
    }
    private fun highlightSelectedTilesLab(tile: Tile, canvas: Canvas) {
        canvas.drawRect(
            tile.column * tileWidth(),
            tile.row * tileHeight(),
            (tile.column +1) * tileWidth(),
            (tile.row + 1) * tileHeight(),
            selectedTilePaint.apply {
                this.setARGB(255, coloresLab[0], coloresLab[1], coloresLab[2])
            }
        )
    }
    private fun highlightSelectedTilesSut(tile: Tile, canvas: Canvas) {
        canvas.drawRect(
            tile.column * tileWidth(),
            tile.row * tileHeight(),
            (tile.column +1) * tileWidth(),
            (tile.row + 1) * tileHeight(),
            selectedTilePaint.apply {
                this.setARGB(255, coloresSut[0], coloresSut[1], coloresSut[2])
            }
        )
    }
    private fun highlightSelectedTilesBur(tile: Tile, canvas: Canvas) {
        canvas.drawRect(
            tile.column * tileWidth(),
            tile.row * tileHeight(),
            (tile.column +1) * tileWidth(),
            (tile.row + 1) * tileHeight(),
            selectedTilePaint.apply {
                this.setARGB(255, coloresBur[0], coloresBur[1], coloresBur[2])
            }
        )
    }
    private fun highlightSelectedTilesErro(tile: Tile, canvas: Canvas) {
        canvas.drawRect(
            tile.column * tileWidth(),
            tile.row * tileHeight(),
            (tile.column +1) * tileWidth(),
            (tile.row + 1) * tileHeight(),
            selectedTilePaint.apply {
                this.setARGB(255, coloresErro[0], coloresErro[1], coloresErro[2])
            }
        )
    }    private fun highlightSelectedTilesSala(tile: Tile, canvas: Canvas) {
        canvas.drawRect(
            tile.column * tileWidth(),
            tile.row * tileHeight(),
            (tile.column +1) * tileWidth(),
            (tile.row + 1) * tileHeight(),
            selectedTilePaint.apply {
                this.setARGB(255, coloresSala[0], coloresSala[1], coloresSala[2])
            }
        )
    }
    private fun highlightSelectedTilesTre(tile: Tile, canvas: Canvas) {
        canvas.drawRect(
            tile.column * tileWidth(),
            tile.row * tileHeight(),
            (tile.column +1) * tileWidth(),
            (tile.row + 1) * tileHeight(),
            selectedTilePaint.apply {
                this.setARGB(255, coloresTre[0], coloresTre[1], coloresTre[2])
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
                highlightSelectedTiles(tile, canvas)
            }
        }

        /**
        * Repetidas para cada palabra que seleccionemos
        */
        correctSelectedTilesBar.forEach { tile ->
            highlightSelectedTilesBar(tile, canvas)
        }
        correctSelectedTilesErre.forEach { tile ->
            highlightSelectedTilesErre(tile, canvas)
        }
        correctSelectedTilesLab.forEach { tile ->
            highlightSelectedTilesLab(tile, canvas)
        }
        correctSelectedTilesSut.forEach { tile ->
            highlightSelectedTilesSut(tile, canvas)
        }
        correctSelectedTilesBur.forEach { tile ->
            highlightSelectedTilesBur(tile, canvas)
        }
        correctSelectedTilesErro.forEach { tile ->
            highlightSelectedTilesErro(tile, canvas)
        }
        correctSelectedTilesSala.forEach { tile ->
            highlightSelectedTilesSala(tile, canvas)
        }
        correctSelectedTilesTre.forEach { tile ->
            highlightSelectedTilesTre(tile, canvas)
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

                /**
                 * Para los colores aleatorios de cada palabra
                 */
                val rnd = Random

                color1 = rnd.nextInt(256)
                color2 = rnd.nextInt(256)
                color3 = rnd.nextInt(256)

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
                        /**
                         * Segun la palabra que sea añadimos a una lista los colores que hayan salido antes
                         * y llamamos a su funcion correspondiente
                         */
                        when(stringSelection){
                            "BARBADUN"->{
                                selectedTiles.forEach { tile ->
                                    coloresBar.add(color1)
                                    coloresBar.add(color2)
                                    coloresBar.add(color3)
                                    correctSelectedTilesBar.add(tile)
                                }
                            }
                            "ERREMENTARI"->{
                                selectedTiles.forEach { tile ->
                                    coloresErre.add(color1)
                                    coloresErre.add(color2)
                                    coloresErre.add(color3)
                                    correctSelectedTilesErre.add(tile)
                                }
                            }
                            "LABEA"->{
                                selectedTiles.forEach { tile ->
                                    coloresLab.add(color1)
                                    coloresLab.add(color2)
                                    coloresLab.add(color3)
                                    correctSelectedTilesLab.add(tile)
                                }
                            }
                            "SUTEGIA"->{
                                selectedTiles.forEach { tile ->
                                    coloresSut.add(color1)
                                    coloresSut.add(color2)
                                    coloresSut.add(color3)
                                    correctSelectedTilesSut.add(tile)
                                }
                            }
                            "BURDINOLA"->{
                                selectedTiles.forEach { tile ->
                                    coloresBur.add(color1)
                                    coloresBur.add(color2)
                                    coloresBur.add(color3)
                                    correctSelectedTilesBur.add(tile)
                                }
                            }
                            "ERROTA"->{
                                selectedTiles.forEach { tile ->
                                    coloresErro.add(color1)
                                    coloresErro.add(color2)
                                    coloresErro.add(color3)
                                    correctSelectedTilesErro.add(tile)
                                }
                            }
                            "SALAZAR"->{
                                selectedTiles.forEach { tile ->
                                    coloresSala.add(color1)
                                    coloresSala.add(color2)
                                    coloresSala.add(color3)
                                    correctSelectedTilesSala.add(tile)
                                }
                            }
                            else->{
                                selectedTiles.forEach { tile ->
                                    coloresTre.add(color1)
                                    coloresTre.add(color2)
                                    coloresTre.add(color3)
                                    correctSelectedTilesTre.add(tile)
                                }
                            }
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
            if(word.word == stringSelected){
                return if (word.found) {
                    Toast.makeText(context, (R.string.frase_repetida), Toast.LENGTH_SHORT).show()
                    false
                }else{
                    word.found = true
                    _score.value = _score.value?.plus(1) ?: 1
                    Toast.makeText(context, (R.string.frase_correcta), Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
        Toast.makeText(context, (R.string.frase_incorrecta), Toast.LENGTH_SHORT).show()
        return false
    }
}