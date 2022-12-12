package com.example.retomuzkiz.Laberinto

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.*

class LaberynthGame: View {
    companion object{
        const val COLS:Int= 13
        const val ROWS:Int= 19
        const val WALL_THICKNESS = 4f
    }
    private var contexto : Context
    private var ROWS:Int= 5
    private var COLS:Int= 5
    private lateinit var cells:Array<Array<Cell>>
    var cellSize:Float=0.0f
    var hMargin:Float=4f
    var vMargin:Float=4f

    private var contador = 1
    //
    private lateinit var playerPaint: Paint
    private lateinit var exitPaint: Paint
    private final enum class Direction {up, down, left, right}

    private lateinit var random:Random
    private lateinit var wallPaint: Paint

    //
    private lateinit var player: Cell
    private lateinit var exit: Cell

    constructor(applicationContext: Context,attrs: AttributeSet?) :super(applicationContext,attrs){
        playerPaint = Paint()
        playerPaint.color = Color.RED
        exitPaint = Paint()
        exitPaint.color = Color.BLUE
        wallPaint = Paint()
        wallPaint.color = Color.BLACK
        wallPaint.strokeWidth = WALL_THICKNESS
        random = Random()
        contexto = applicationContext
        createMaze()

    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var width = width
        var height = height
        if(width/height < COLS/ ROWS){
            cellSize= width/(COLS+4).toFloat()
        }else{
            cellSize= height/(ROWS+4).toFloat()

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
            Direction.up ->
                if (!player.topWall) {
                    player = cells[player.cols1][player.rows1 - 1]
                }
            Direction.down ->
                if (!player.bottomWall) {
                    player = cells[player.cols1][player.rows1 + 1]
                }

            Direction.right->
                if (!player.rightWall) {
                    player = cells[player.cols1 + 1][player.rows1]
                }
            Direction.left->
                if (!player.leftWall) {
                    player = cells[player.cols1 - 1][player.rows1]
                }
        }
        checkExit()
        invalidate()
    }
    private fun checkExit(){
        if(contador < 3){
            if (player == exit){
                ROWS += 5
                COLS += 5
                createMaze()
                contador ++
            }
        }else{
            if(player == exit) {
                ActivityLaberinto.fin ++
                ActivityLaberinto.cambio = true
                ActivityLaberinto.finalizar(contexto)
            }
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == 0){
            return true
        }

        if(event.action == MotionEvent.ACTION_MOVE){
            var x = event.x
            var y = event.y
            var playerCenterX = hMargin + (player.cols1 + 0.5f)*cellSize
            var playerCenterY = vMargin + (player.rows1 + 0.5f)*cellSize

            var dx = x-playerCenterX
            var dy = y-playerCenterY

            var absDx = Math.abs(dx)
            var absDy = Math.abs(dy)

            if (absDx > cellSize || absDy > cellSize){
                if (absDx> absDy){
                    //Move in x
                    if (dx> 0){
                        //Move to rigth
                        movePlayer(Direction.right)
                    }else{
                        movePlayer(Direction.left)

                    }
                }else{
                    //MOve in Y
                    if (dy>0){
                        //move Down
                        movePlayer(Direction.down)

                    }else{
                        //move Up
                        movePlayer(Direction.up)

                    }
                }
            }
            return true

        }

        return super.onTouchEvent(event)
    }


    private fun createMaze(){

        var stack = Stack<Cell>()
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
        var neighbours = ArrayList<Cell>()
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
private class Cell{
    var topWall=true
    var leftWall = true
    var bottomWall = true
    var rightWall = true
    var visited = false
    var visible = true
    var cols1 = 0
    var rows1 = 0
    constructor( cols:Int, rows:Int){
        cols1 = cols
        rows1=rows
    }

}
