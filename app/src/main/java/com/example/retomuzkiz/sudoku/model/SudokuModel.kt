package com.example.retomuzkiz.sudoku.model

object SudokuModel {

    const val HARD: Int = 50
    const val EMPTY: Int = -1
    const val number0: Int = 0
    const val number1: Int = 1
    const val number2: Int = 2
    const val number3: Int = 3
    const val number4: Int = 4
    const val number5: Int = 5
    const val number6: Int = 6
    const val number7: Int = 7
    const val number8: Int = 8
    const val number9: Int = 9

    val baseMatrix: Array<IntArray> = arrayOf(
        intArrayOf(7, 1, 9, 2, 3, 5, 4, 8, 6),
        intArrayOf(4, 6, 5, 1, 7, 8, 9, 2, 3),
        intArrayOf(3, 2, 8, 9, 4, 6, 5, 7, 1),
        intArrayOf(9, 4, 6, 8, 5, 1, 2, 3, 7),
        intArrayOf(5, 8, 3, 7, 6, 2, 1, 9, 4),
        intArrayOf(1, 7, 2, 3, 9, 4, 6, 5, 8),
        intArrayOf(6, 3, 7, 5, 1, 9, 8, 4, 2),
        intArrayOf(8, 9, 1, 4, 2, 7, 3, 6, 5),
        intArrayOf(2, 5, 4, 6, 8, 3, 7, 1, 9))

    val sudoku = SudokuClass(baseMatrix)

    var winnedTimeWhenFinished: String = "00:00"

}