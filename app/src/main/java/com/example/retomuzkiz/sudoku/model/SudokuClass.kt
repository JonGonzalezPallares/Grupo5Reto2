package com.example.retomuzkiz.sudoku.model

import com.example.retomuzkiz.sudoku.model.SudokuModel.EMPTY
import com.example.retomuzkiz.sudoku.model.SudokuModel.number0

class SudokuClass(matrix: Array<IntArray>) {

    private var sudokuMatrix: Array<IntArray> = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0))

    private var baseMatrix: Array<IntArray> = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0))

    var isBaseElement: Array<BooleanArray> = arrayOf(
        booleanArrayOf(true, true, true, true, true, true, true, true, true),
        booleanArrayOf(true, true, true, true, true, true, true, true, true),
        booleanArrayOf(true, true, true, true, true, true, true, true, true),
        booleanArrayOf(true, true, true, true, true, true, true, true, true),
        booleanArrayOf(true, true, true, true, true, true, true, true, true),
        booleanArrayOf(true, true, true, true, true, true, true, true, true),
        booleanArrayOf(true, true, true, true, true, true, true, true, true),
        booleanArrayOf(true, true, true, true, true, true, true, true, true),
        booleanArrayOf(true, true, true, true, true, true, true, true, true)
    )


    var currentNumber: Int = EMPTY
    var prevNumber: Int = number0
    var hasEMPTY: Int = 0

    private var diffiulty: Int = 0

    // Metodo para establecer la dificultad del juego
    fun setDifficulty(diff: Int){
        diffiulty = diff
    }

    // Metodo para establecer el número de campos vacíos en el tablero
    fun setHasEmpty(X: Int){
        hasEMPTY = X
    }

    // Metodo para establecer la matriz inicial del tablero
    fun setBase(matrix: Array<IntArray>){
        for(i in 0 until 9)
            for(j in 0 until 9)
                sudokuMatrix[i][j]=matrix[i][j]
    }

    // Metodo para marcar todos los elementos iniciales del tablero
    fun setIsBaseElement(){
        for(i in 0 until 9)
            for(j in 0 until 9)
                isBaseElement[i][j]=true
    }

    // Metodo para intercambiar dos columnas del tablero
    private fun swapCOL(X: Int, Y: Int){

        val temp = IntArray(9)
        for(i in 0 until 9)
            temp[i]=0

        // Itera por cada fila y mueve el contenido de la columna X a la columna Y y viceversa
        for (j in 0 until 9) {
            temp[j] = getFieldContent(X, j)
            setFieldContent(X, j, getFieldContent(Y, j))
            setFieldContent(Y, j, temp[j])
        }
    }

    // Metodo para intercambiar dos filas del tablero
    private fun swapROW(X: Int, Y: Int) {

        val temp = IntArray(9)
        for (i in 0 until 9)
            temp[i] = 0

        // Itera por cada columna y mueve el contenido de la fila X a la fila Y y viceversa
        for (j in 0 until 9) {
            temp[j] = getFieldContent(j, X)
            this.setFieldContent(j, X, this.getFieldContent(j, Y))
            this.setFieldContent(j, Y, temp[j])
        }

    }

    // Metodo para obtener el contenido de un campo en una posición específica del tablero
    fun getFieldContent(x: Int, y: Int): Int {
        return sudokuMatrix[x][y]
    }

    // Metodo para establecer el contenido de un campo en una posición específica del tablero
    fun setFieldContent(x: Int, y: Int, content: Int): Int {
        sudokuMatrix[x][y] = content
        return content
    }

    // Metodo para generar un número aleatorio dentro de un rango específico
    private fun genRandomNumber(min: Int, max: Int): Int{
        return (min..max).shuffled().first()
    }

    // Metodo para establecer el número seleccionado por el jugador
    fun setPlayer(x: Int){
        currentNumber = x
    }

    /**
     * Se encarga de eliminar el contenido de un campo del tablero.
     * Primero, genera dos números aleatorios para representar las coordenadas de una celda en el tablero.
     * Si el contenido de la celda no es cero (que significa que ya está vacía),
     * entonces el contenido se establece como cero y el valor booleano correspondiente en la matriz "isBaseElement"
     * se establece como falso.
     * la variable "hasEMPTY" se incrementa en uno para hacer un seguimiento del número de celdas vacías en el tablero.
     * */
    private fun removeFieldContent(){

        val genX: Int = genRandomNumber(0,8)
        val genY: Int = genRandomNumber(0,8)

        if (sudokuMatrix[genX][genX] != number0) {
            sudokuMatrix[genX][genY] = number0
            isBaseElement[genX][genY]=false
            hasEMPTY++
        }

    }
    /**
     * Se encarga de generar un tablero de Sudoku aleatorio.
     * Se establecen algunas variables importantes en cero.
     * Se realiza un número aleatorio de intercambios de filas y columnas del tablero,
     * para garantizar que el tablero sea aleatorio.
     * Se hace una copia del tablero en "baseMatrix" para su posterior validación.
     * Se eliminan los contenidos de algunas celdas del tablero (llamando a "removeFieldContent")
     * Se calcula el número real de celdas vacías en el tablero y se establece la variable "hasEMPTY" en ese valor.
     * */

    fun genBoard() {
        currentNumber = EMPTY
        hasEMPTY      = 0
        var realEmpty = 0

        for(i in 0 until genRandomNumber(75, 100)) {

            swapCOL(genRandomNumber(0, 2), genRandomNumber(0, 2))
            swapCOL(genRandomNumber(3, 5), genRandomNumber(3, 5))
            swapCOL(genRandomNumber(6, 8), genRandomNumber(6, 8))

            swapROW(genRandomNumber(0, 2), genRandomNumber(0, 2))
            swapROW(genRandomNumber(3, 5), genRandomNumber(3, 5))
            swapROW(genRandomNumber(6, 8), genRandomNumber(6, 8))

        }

        for(i in 0 until 9)
            for(j in 0 until 9)
                baseMatrix[i][j]=sudokuMatrix[i][j]

        while(hasEMPTY != diffiulty){
            removeFieldContent()
        }

        for(i in 0 until 9) {
            for (j in 0 until 9) {
                if (sudokuMatrix[i][j] == 0)
                    realEmpty++
            }
        }

        hasEMPTY = realEmpty
    }
    /**
     * Se encarga de validar si el usuario ha resuelto correctamente el tablero.
     * Compara el contenido del tablero actual con el contenido del tablero base "baseMatrix".
     * Si hay alguna diferencia, significa que el usuario no ha resuelto correctamente el tablero y la función devuelve falso.
     * De lo contrario, devuelve verdadero.*/
    fun validate(): Boolean{
        for(i in 0 until 9)
            for(j in 0 until 9)
                if(sudokuMatrix[i][j]!=baseMatrix[i][j])
                    return false
        return true
    }
    /**
     * Constructor de la clase establece la matriz "sudokuMatrix" a partir de una matriz de entrada "matrix".
     * */
    init {
        for(i in 0 until 9)
            for(j in 0 until 9)
                sudokuMatrix[i][j]=matrix[i][j]
    }

}

