package com.example.retomuzkiz.Laberinto

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.*

class LaberynthGameLuna: View {
    companion object{
        const val WALL_THICKNESS = 4f
    }
    private var ROWS:Int= 19
    private var COLS:Int= 13
    private lateinit var cells:Array<Array<CellLuna>>
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
    private lateinit var player: CellLuna
    private lateinit var exit: CellLuna

    constructor(applicationContext: Context,attrs: AttributeSet?) :super(applicationContext,attrs){

        playerPaint = Paint()
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
        canvas.drawColor(Color.GREEN)

        var width = width
        var height = height
        if(width/height < COLS/ ROWS){
            cellSize= width/(COLS+10).toFloat()
        }else{
            cellSize= height/(ROWS+10).toFloat()

        }
        hMargin = (width - COLS*cellSize)/2
        vMargin = (height- ROWS*cellSize)/2
        canvas.translate(hMargin,vMargin)

        for(col in 0 until COLS){
            for (row in 0 until ROWS){
                var celda = cells[col][row]
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
            var margin = cellSize/10
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
        if(contador != 3){
          if (player == exit) {
//                ROWS += 5
//                COLS += 5
              createMaze()
          }
            contador ++

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


    private fun createMaze() {

        var stack = Stack<CellLuna>()
        lateinit var currentCell: CellLuna

        cells = Array(COLS) { col ->
            Array(ROWS) { row ->
                var emptyCell = CellLuna(col, row)
                var celdaBuena = CellLuna(col, row)
                emptyCell.visible = false
                emptyCell.topWall = false
                emptyCell.bottomWall = false
                emptyCell.leftWall = false
                emptyCell.rightWall = false
                var cell = CellLuna(col, row)

                if (col == 0) {
                    if (row == 0 || row == 1 || row == 2 || row == 3 || row == 4 || row == 5 || row == 13 || row == 14 || row == 15 || row == 16 || row == 17 || row == 18) {
                        celdaBuena = emptyCell

                    }

                } else if (col == 1) {
                    if (row == 0 || row == 1 || row == 2 || row == 3 || row == 15 || row == 16 || row == 17 || row == 18) {
                        celdaBuena = emptyCell

                    }

                } else if (col == 2) {
                    if (row == 0 || row == 1 || row == 2 || row == 16 || row == 17 || row == 18) {
                        celdaBuena = emptyCell

                    }

                } else if (col == 3) {
                    if (row == 0 || row == 1 || row == 17 || row == 18) {
                        celdaBuena = emptyCell

                    }

                } else if (col == 5 || col == 4) {
                    if (row == 0 || row == 18) {
                        celdaBuena = emptyCell

                    }

                } else if (col == 6) {
                    if (row == 5 || row == 6 || row == 7 || row == 8 || row == 9 || row == 10 || row == 11 || row == 12 || row == 13) {
                        celdaBuena = emptyCell

                    }

                } else if (col == 7) {
                    if (row == 4 || row == 5 || row == 6 || row == 7 || row == 8 || row == 9 || row == 10 || row == 11 || row == 12 || row == 13 || row == 14) {
                        celdaBuena = emptyCell

                    }

                } else if (col == 8) {
                    if (row == 3 || row == 4 || row == 5 || row == 6 || row == 7 || row == 8 || row == 9 || row == 10 || row == 11 || row == 12 || row == 13 || row == 14 || row == 15) {
                        celdaBuena = emptyCell

                    }
                } else if (col == 9) {
                    if (row == 2 || row == 3 || row == 4 || row == 5 || row == 6 || row == 7 || row == 8 || row == 9 || row == 10 || row == 11 || row == 12 || row == 13 || row == 14 || row == 15 || row == 16) {
                        celdaBuena = emptyCell

                    }

                } else if (col == 10 || col == 11 || col == 12) {
                    if (row == 1 || row == 2 || row == 3 || row == 4 || row == 5 || row == 6 || row == 7 || row == 8 || row == 9 || row == 10 || row == 11 || row == 12 || row == 13 || row == 14 || row == 15 || row == 16 || row == 17) {
                        celdaBuena = emptyCell


                    }


                }

                celdaBuena


            }
        }

        currentCell = cells[9][0]
        currentCell.visited = true
        player = cells[9][0]
        exit = cells[COLS - 4][ROWS - 1]
        var row = 0
//
//        for (col in 0 until COLS - 1) {
//            for (row in 0 until ROWS - 1) {
//                nextCell = cells[currentCell.cols1][currentCell.rows1]
//                delimitarLuna(currentCell, nextCell)
//                currentCell = nextCell
//            }
//            row = 0
//            nextCell = cells[currentCell.cols1 + 1][row]
//            currentCell = nextCell
//
//
//        }
//
///*            */
//
//
//
        var nextCell:CellLuna? = null
        var t = Thread  (){
            do {
                println("Ha entrado")


                nextCell = getNeighbour(currentCell)

                if (nextCell != currentCell && nextCell!!.visible) {

                    removeWall(currentCell, nextCell!!)

                    stack.push(currentCell)
                    currentCell = nextCell as CellLuna
                    currentCell.visited = true
                } else {

                    currentCell = stack.pop()
                }
            } while (!stack.isEmpty())
        }
        t.start()



 //      }


    }



    private fun delimitarLuna(currentCell: CellLuna, nextCell: CellLuna) {
        //Si currentCell es la de abajo

        if(currentCell.cols1 == nextCell.cols1 && currentCell.rows1 == nextCell.rows1+1){
            if ( currentCell.visible == true && nextCell.visible == false){
                currentCell.topWall = true
            }else if (currentCell.visible == false&& nextCell.visible == true){
                nextCell.bottomWall = true
            }

            else {
                currentCell.topWall = false
                nextCell.bottomWall = false
            }
        }
        //Si current cell es la de arriba
        if(currentCell.cols1 == nextCell.cols1 && currentCell.rows1 == nextCell.rows1-1){
            if ( currentCell.visible == true && nextCell.visible == false){
                currentCell.bottomWall =true
            }else if( currentCell.visible == false && nextCell.visible == true){
                nextCell.bottomWall = true
            }
            else{
                currentCell.bottomWall=false
                nextCell.topWall = false
            }
        }

        //Si Current Cell es la de la izquierda
        if(currentCell.cols1 == nextCell.cols1+1 && currentCell.rows1 == nextCell.rows1){
            if ( currentCell.visible == true && nextCell.visible == false){
                currentCell.leftWall =true
            }
            else if( currentCell.visible == false && nextCell.visible == true){
                nextCell.leftWall = true
            }else {
                currentCell.leftWall = false
                nextCell.rightWall = false
            }
        }
        //Si current Cell es la de la derecha
        if(currentCell.cols1 == nextCell.cols1-1 && currentCell.rows1 == nextCell.rows1){
            if ( currentCell.visible == true && nextCell.visible == false){
                currentCell.rightWall =true
            }
            else if( currentCell.visible == false && nextCell.visible == true){
                nextCell.rightWall = true
            }
            else {
                currentCell.rightWall = false
                nextCell.leftWall = false
            }
        }

    }


    private fun removeWall(currentCell: CellLuna, nextCell: CellLuna) {
        //delimitarLuna(currentCell,nextCell)

        if (currentCell.visible == true && nextCell.visible == true) {
            //Si current Cell es la de Abajo
            if (currentCell.cols1 == nextCell.cols1 && currentCell.rows1 == nextCell.rows1 + 1) {
                currentCell.topWall = false
                nextCell.bottomWall = false
            }
            //Bottom wall
            if (currentCell.cols1 == nextCell.cols1 && currentCell.rows1 == nextCell.rows1 - 1) {
                currentCell.bottomWall = false
                nextCell.topWall = false
            }
            //Si current Cell es la de la Izquierda
            if (currentCell.cols1 == nextCell.cols1 + 1 && currentCell.rows1 == nextCell.rows1) {
                currentCell.leftWall = false
                nextCell.rightWall = false
            }
            //Si current Cell es la de la derecha
            if (currentCell.cols1 == nextCell.cols1 - 1 && currentCell.rows1 == nextCell.rows1) {
                currentCell.rightWall = false
                nextCell.leftWall = false
            }


        }
    }

    private fun getNeighbour(cell: CellLuna): CellLuna?{
        var neighbours = ArrayList<CellLuna>()
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
        if(neighbours.size>0) {
            var index: Int = random.nextInt(neighbours.size)
            do {
                index=random.nextInt(neighbours.size)
            }while(neighbours.get(index).visible == false)
//            if (neighbours.get(index).rows1>= cell.cols1){
                return neighbours.get(index)
          //      }




        }else{
            return cell
        }
    }
}
private class CellLuna{
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