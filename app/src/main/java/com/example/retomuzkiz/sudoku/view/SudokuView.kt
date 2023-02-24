package com.example.retomuzkiz.sudoku.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.retomuzkiz.sudoku.GameActivity
import com.example.retomuzkiz.sudoku.model.SudokuModel
import com.example.retomuzkiz.sudoku.model.SudokuModel.number0
import kotlin.math.min


class SudokuView : View {


    private var paintText = Paint()
    private val paintBg = Paint()
    private val paintLine = Paint()
    private val paintCircle = Paint()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    init {
        paintBg.color = Color.BLACK
        paintBg.style = Paint.Style.FILL

        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE

        paintCircle.color = Color.GRAY
        paintCircle.style = Paint.Style.FILL
    }


    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paintBg)
        drawCircle(canvas)
        drawGameArea(canvas)
        if(SudokuModel.sudoku.currentNumber != SudokuModel.sudoku.prevNumber)
            invalidate()
        drawNumbers(canvas)
    }

    /**
     * Obtener el ancho y alto de la vista
     * Establecer el grosor de la línea y dibujar las líneas verticales y horizontales del tablero
     * Establecer un grosor mayor y dibujar las líneas más gruesas del tablero
     * */
    private fun drawGameArea(canvas: Canvas) {
        val widthFloat: Float = width.toFloat()
        val heightFloat: Float = height.toFloat()

        paintLine.strokeWidth = 5F
        for(i in 1 until 9) {
            canvas.drawLine(20F, i * heightFloat / 9, widthFloat-20F, i * heightFloat / 9, paintLine)
            canvas.drawLine(i * widthFloat / 9, 20F, i * widthFloat / 9, heightFloat-20F, paintLine)
        }

        paintLine.strokeWidth = 13F
        canvas.drawLine(0F, 3 * heightFloat / 9, widthFloat, 3 * heightFloat / 9, paintLine) // Línea horizontal superior
        canvas.drawLine(0F, 6 * heightFloat / 9, widthFloat, 6 * heightFloat / 9, paintLine) // Línea horizontal inferior
        canvas.drawLine(3 * widthFloat / 9, 0F, 3 * widthFloat / 9, heightFloat, paintLine) // Línea vertical izquierda
        canvas.drawLine(6 * widthFloat / 9, 0F, 6 * widthFloat / 9, heightFloat, paintLine) // Línea vertical derecha
    }


    /**
     * Selecciona la medida más pequeña entre el ancho y el alto
     * Establece las dimensiones de la vista con la medida calculada
     * */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = resolveSizeAndState(min(widthMeasureSpec, heightMeasureSpec), 0, 0)
        setMeasuredDimension(size, size)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // Verifica si se ha tocado la pantalla
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                // Si se ha seleccionado un número diferente, se invalida la vista
                if (SudokuModel.sudoku.currentNumber != SudokuModel.sudoku.prevNumber) {
                    invalidate()
                }
                // Obtiene la posición en la que se ha tocado la pantalla
                val tX: Int = (event.x / (width / 9)).toInt()
                val tY: Int = (event.y / (height / 9)).toInt()

                // Verifica si la posición está dentro del rango del tablero y si el campo está vacío
                if (tX < 9 && tY < 9 && SudokuModel.sudoku.getFieldContent(tX, tY) == number0) {
                    // Establece el número seleccionado en el campo seleccionado y disminuye el contador de campos vacíos
                    SudokuModel.sudoku.setFieldContent(tX, tY, SudokuModel.sudoku.currentNumber)
                    SudokuModel.sudoku.hasEMPTY--
                    // Verifica si se han llenado todos los campos y si es así, finaliza el juego
                    if (SudokuModel.sudoku.hasEMPTY == 0) {
                        (context as GameActivity).endGame()
                    }
                    // Invalida la vista para que se actualice
                    invalidate()
                }

                // Verifica si la posición está dentro del rango del tablero y si el número seleccionado es el vacío y si el campo seleccionado no es un elemento base
                if (tX < 9 && tY < 9 && SudokuModel.sudoku.currentNumber == SudokuModel.EMPTY && !SudokuModel.sudoku.isBaseElement[tX][tY]) {
                    // Establece el campo seleccionado como vacío y aumenta el contador de campos vacíos
                    SudokuModel.sudoku.setFieldContent(tX, tY, 0)
                    SudokuModel.sudoku.hasEMPTY++
                    // Invalida la vista para que se actualice
                    invalidate()
                }
                return true
            }
            else -> return super.onTouchEvent(event)
        }
    }


    private fun drawCircle(canvas: Canvas){
        for(i in 0 until 9){
            for (j in 0 until 9){

                if (SudokuModel.sudoku.getFieldContent(i, j) == SudokuModel.sudoku.currentNumber) {
                    val centerX = (i * width / 9 + width / 9 - 59).toFloat()
                    val centerY = (j * height / 9 + height / 9 - 59).toFloat()

                    canvas.drawCircle(centerX, centerY, (width / 24).toFloat(), paintCircle)
                }

                if (SudokuModel.sudoku.getFieldContent(i,j) != 0 && !SudokuModel.sudoku.isBaseElement[i][j] && SudokuModel.sudoku.currentNumber == SudokuModel.EMPTY){
                    val centerX = (i * width / 9 + width / 9 - 59).toFloat()
                    val centerY = (j * height / 9 + height / 9 - 59).toFloat()
                    canvas.drawCircle(centerX, centerY, (width / 24).toFloat(), paintCircle)

                }
            }
        }

    }

    private fun drawNumbers(canvas: Canvas) {
        paintText.textSize = 65.toFloat()
        paintText.color = Color.WHITE
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                val num = SudokuModel.sudoku.getFieldContent(i, j)
                paintText.color = if (!SudokuModel.sudoku.isBaseElement[i][j]) Color.GREEN else Color.WHITE
                val centerX = (i * width / 9 + width / 9 - 76).toFloat()
                val centerY = (j * height / 9 + height / 9 - 41).toFloat()
                drawNumber(canvas, num, centerX, centerY)
            }
        }
    }

    private fun drawNumber(canvas: Canvas, num: Int, centerX: Float, centerY: Float) {
        when (num) {
            SudokuModel.number1 -> canvas.drawText("1", centerX, centerY, paintText)
            SudokuModel.number2 -> canvas.drawText("2", centerX, centerY, paintText)
            SudokuModel.number3 -> canvas.drawText("3", centerX, centerY, paintText)
            SudokuModel.number4 -> canvas.drawText("4", centerX, centerY, paintText)
            SudokuModel.number5 -> canvas.drawText("5", centerX, centerY, paintText)
            SudokuModel.number6 -> canvas.drawText("6", centerX, centerY, paintText)
            SudokuModel.number7 -> canvas.drawText("7", centerX, centerY, paintText)
            SudokuModel.number8 -> canvas.drawText("8", centerX, centerY, paintText)
            SudokuModel.number9 -> canvas.drawText("9", centerX, centerY, paintText)
        }
    }
}




