package com.example.retomuzkiz

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

class LaberynthGame: View {
    companion object{
        const val COLS:Int= 13
        const val ROWS:Int= 19
        const val WALL_THICKNESS = 4f
    }
    private lateinit var cells:Array<Array<Cell>>
    var cellSize:Float=0.0f
    var hMargin:Float=4f
    var vMargin:Float=4f
    private lateinit var random:Random
    private lateinit var wallPaint: Paint
    constructor(applicationContext: Context,attrs: AttributeSet?) :super(applicationContext,attrs){

        wallPaint = Paint()
        wallPaint.setColor(Color.BLACK)
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
            cellSize= width/(COLS+4).toFloat()
        }else{
            cellSize= height/(ROWS+4).toFloat()

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
        }


    }
    private fun createMaze(){
        var stack = Stack<Cell>()
        lateinit var currentCell: Cell
        var nextCell: Cell?

        cells = Array(COLS){ col->
            Array<Cell>(ROWS,{row->
                Cell(col,row)
            })}

        currentCell = cells[0][0]
        currentCell.visited = true
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

    private fun delimitarLuna(currentCell: Cell,nextCell: Cell) {
        //Left side
        var col1 = listOf<Int>(0,1,2,3,4,5)
        col1.forEach(){
            if(it == currentCell.cols1 && currentCell.rows1 == 0){
                currentCell.topWall=false
                currentCell.leftWall=false
                currentCell.rightWall=false
                currentCell.bottomWall= false
                if ((currentCell.cols1 == 6) && currentCell.rows1 == 0){

                    currentCell.bottomWall = true
                    currentCell.leftWall = true
                }
                if(currentCell.cols1==4 && currentCell.rows1 == 0)
                {
                    currentCell.bottomWall = true
                }

            }
        }

    }


    private fun removeWall(currentCell: Cell, nextCell: Cell) {
        delimitarLuna(currentCell,nextCell)
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
        if(neighbours.size>0) {
            var index = random.nextInt(neighbours.size)
            return neighbours.get(index)
        }else{
            return null
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