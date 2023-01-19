package com.example.retomuzkiz.Laberinto

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.retomuzkiz.fin
import com.example.retomuzkiz.finalizar
import com.example.retomuzkiz.juegoAcabado
import com.example.retomuzkiz.stopTimer
import java.util.*
import kotlin.math.abs

class LaberynthGame(applicationContext: Context, attrs: AttributeSet?) :
    View(applicationContext, attrs) {
    companion object{
        const val WALL_THICKNESS = 4f
    }
    private var contexto : Context = applicationContext
    private var ROWS:Int= 5
    private var COLS:Int= 5
    private lateinit var cells:Array<Array<Cell>>
    var cellSize:Float=0.0f
    var hMargin:Float=4f
    var vMargin:Float=4f

    private var contador = 1
    //
    private var playerPaint = Paint()
    private var exitPaint: Paint
    private enum class Direction {Up, Down, Left, Right}

    private var random:Random
    private var wallPaint: Paint

    //
    private lateinit var player: Cell
    private lateinit var exit: Cell

    init {
        playerPaint.color = Color.RED
        exitPaint = Paint()
        exitPaint.color = Color.BLUE
        wallPaint = Paint()
        wallPaint.color = Color.BLACK
        wallPaint.strokeWidth = WALL_THICKNESS
        random = Random()
        createMaze()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width
        val height = height
        cellSize = if(width/height < COLS/ ROWS){
            width/(COLS+4).toFloat()
        }else{
            height/(ROWS+4).toFloat()

        }
        hMargin = (width - COLS*cellSize)/2
        vMargin = (height- ROWS*cellSize)/2
        canvas.translate(hMargin,vMargin)

        for(col in 0 until COLS){
            for (row in 0 until ROWS){
                val celda = cells[col][row]
                if(celda.topWall){
                    canvas.drawLine(
                        col*cellSize,
                        row*cellSize,
                        (col+1)*cellSize,
                        row*cellSize,
                        wallPaint
                    )
                }

                if(celda.leftWall){
                    canvas.drawLine(
                        col*cellSize,
                        row*cellSize,
                        col*cellSize,
                        (row+1)*cellSize,wallPaint
                    )
                }

                if(celda.bottomWall){
                    canvas.drawLine(
                        col*cellSize,
                        (row+1)*cellSize,
                        (col+1)*cellSize,
                        (row+1)*cellSize,wallPaint
                    )
                }

                if(celda.rightWall){
                    canvas.drawLine(
                        (col+1)*cellSize,
                        row*cellSize,
                        (col+1)*cellSize,
                        (row+1)*cellSize,wallPaint
                    )
                }
            }

            val margin = cellSize/10
            canvas.drawRect(
                player.cols1*cellSize+margin,
                player.rows1*cellSize+margin,
                (player.cols1+1)*cellSize-margin,
                (player.rows1+1)*cellSize-margin,
                playerPaint
            )

            canvas.drawRect(
                exit.cols1*cellSize + margin,
                exit.rows1*cellSize + margin,
                (exit.cols1+1)*cellSize - margin,
                (exit.rows1+1)*cellSize - margin,
                exitPaint
            )
        }
    }


    private fun movePlayer(direccion: Direction){
        when(direccion){
            Direction.Up ->
                if (!player.topWall) {
                    player = cells[player.cols1][player.rows1 - 1]
                }

            Direction.Down ->
                if (!player.bottomWall) {
                    player = cells[player.cols1][player.rows1 + 1]
                }

            Direction.Right->
                if (!player.rightWall) {
                    player = cells[player.cols1 + 1][player.rows1]
                }

            Direction.Left->
                if (!player.leftWall) {
                    player = cells[player.cols1 - 1][player.rows1]
                }
        }
        checkExit()
        invalidate()
    }


    private fun checkExit(){
        if(contador < 1){
            if (player == exit){
                ROWS += 5
                COLS += 5
                createMaze()
                contador ++
            }
        }else{
            if(player == exit) {


                stopTimer()
                juegoAcabado(0)
                fin ++
                finalizar(contexto,"laberinto")
                ActivityLaberinto.cambio = true
            }
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == 0){
            return true
        }

        if(event.action == MotionEvent.ACTION_MOVE){
            val x = event.x
            val y = event.y
            val playerCenterX = hMargin + (player.cols1 + 0.5f)*cellSize
            val playerCenterY = vMargin + (player.rows1 + 0.5f)*cellSize
            val dx = x-playerCenterX
            val dy = y-playerCenterY
            /**
             * Antes
             *
             * val absDx = Math.abs(dx)
             * val absDy = Math.abs(dy)
             */
            val absDx = abs(dx)
            val absDy = abs(dy)

            if (absDx > cellSize || absDy > cellSize){
                if (absDx> absDy){
                    //Move in x
                    if (dx> 0){
                        //Move to rigth
                        movePlayer(Direction.Right)
                    }else{
                        movePlayer(Direction.Left)
                    }
                }else{
                    //MOve in Y
                    if (dy>0){
                        //move Down
                        movePlayer(Direction.Down)
                    }else{
                        //move Up
                        movePlayer(Direction.Up)
                    }
                }
            }
            return true
        }
        return super.onTouchEvent(event)
    }


    private fun createMaze(){
        val stack = Stack<Cell>()
        lateinit var currentCell: Cell
        var nextCell: Cell?

        cells = Array(COLS){ col->
            Array(ROWS) { row ->
                Cell(col, row)
            }
        }

        currentCell = cells[0][0]
        currentCell.visited = true
        player = cells[0][0]
        exit = cells[COLS-1][ROWS-1]
        do {
            nextCell = getNeighbour(currentCell)
            if (nextCell != null) {

                removeWall(currentCell, nextCell)
                stack.push(currentCell)
                currentCell = nextCell
                currentCell.visited = true
            } else {
                currentCell = stack.pop()
            }
        }while (!stack.isEmpty())
    }


    private fun removeWall(currentCell: Cell, nextCell: Cell) {
        //Top Wall
        if(currentCell.cols1 == nextCell.cols1 && currentCell.rows1 == nextCell.rows1+1){
            currentCell.topWall=false
            nextCell.bottomWall = false
        }

        //Bottom wall
        if(currentCell.cols1 == nextCell.cols1 && currentCell.rows1 == nextCell.rows1-1){
            currentCell.bottomWall=false
            nextCell.topWall = false
        }

        //LeftWall
        if(currentCell.cols1 == nextCell.cols1+1 && currentCell.rows1 == nextCell.rows1){
            currentCell.leftWall=false
            nextCell.rightWall = false
        }

        //rightWall
        if(currentCell.cols1 == nextCell.cols1-1 && currentCell.rows1 == nextCell.rows1){
            currentCell.rightWall=false
            nextCell.leftWall = false
        }
    }


    private fun getNeighbour(cell: Cell): Cell?{
        val neighbours = ArrayList<Cell>()
        //left neighbour
        if(cell.cols1>0){
            if(!cells[cell.cols1-1][cell.rows1].visited){
                neighbours.add(cells[cell.cols1-1][cell.rows1])

            }
        }

        //rigth neighbour
        if(cell.cols1< COLS-1){
            if(!cells[cell.cols1+1][cell.rows1].visited){
                neighbours.add(cells[cell.cols1+1][cell.rows1])

            }
        }

        //top neighbour
        if(cell.rows1>0){
            if(!cells[cell.cols1][cell.rows1-1].visited){
                neighbours.add(cells[cell.cols1][cell.rows1-1])

            }
        }

        //bottom neighbour
        if(cell.rows1 < ROWS-1){
            if(!cells[cell.cols1][cell.rows1+1].visited){
                neighbours.add(cells[cell.cols1][cell.rows1+1])

            }
        }

        return if(neighbours.size>0) {
            val index = random.nextInt(neighbours.size)
            neighbours[index]
        }else{
            null
        }
    }
}


private class Cell(cols: Int, rows: Int) {
    var topWall=true
    var leftWall = true
    var bottomWall = true
    var rightWall = true
    var visited = false
    var cols1 = cols
    var rows1 = rows
}
