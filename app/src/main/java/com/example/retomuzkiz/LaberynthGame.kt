package com.example.retomuzkiz

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class LaberynthGame: View {
    companion object{
        const val COLS:Int= 7
        const val ROWS:Int= 10
        const val WALL_THICKNESS = 4f
    }

    var cellSize:Float=0.0f
    var hMargin:Float=0f
    var vMargin:Float=0f

    var wallPaint: Paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.GREEN)

        var width = width
        var height = height
        if(width/height< COLS/ ROWS){
            cellSize= width/(COLS+1).toFloat()
        }else{
            cellSize= height/(ROWS+1).toFloat()

        }
        hMargin = (width - COLS*cellSize)/2
        vMargin = (height- ROWS*cellSize)/2
        canvas.translate(hMargin,vMargin)
        var cel:Array<Array<Cell>> = Array(COLS){ col->
            Array<Cell>(ROWS,{row->
                var celda = Cell(col,row)
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
                celda

            })}

    }
    private fun createMaze(){
        var cel:Array<Array<Cell>> = Array(COLS){ col->
            Array<Cell>(ROWS,{row->
                Cell(col,row)
            })}


    }
    constructor(applicationContext: Context,attrs: AttributeSet?) :super(applicationContext,attrs){


        wallPaint.setColor(Color.BLACK)
        wallPaint.strokeWidth = WALL_THICKNESS
        createMaze()

    }
}
private class Cell{
    var topWall=true
    var leftWall = true
    var bottomWall = true
    var rightWall = true
    var cols1 = 0
    var rows1 = 0
    constructor( cols:Int, rows:Int){
        cols1 = cols
        rows1=rows
    }

}