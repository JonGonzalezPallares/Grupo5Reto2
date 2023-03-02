package com.example.retomuzkiz.sudoku

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import com.example.retomuzkiz.clases.MsgVictoria
import com.example.retomuzkiz.databinding.ActivityGameBinding
import com.example.retomuzkiz.sudoku.model.SudokuModel
import com.example.retomuzkiz.sudoku.model.SudokuModel.EMPTY


class GameActivity : AppCompatActivity(){
    lateinit var mediaPlay: MediaPlayer
    private lateinit var binding: ActivityGameBinding
    private var btnarray = arrayOfNulls<Button>(10)
    private lateinit var clock: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar!!.hide()
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clock = binding.chornometer
        SudokuModel.sudoku.setHasEmpty(0)
        SudokuModel.sudoku.setIsBaseElement()
        SudokuModel.sudoku.setBase(SudokuModel.baseMatrix)
        SudokuModel.sudoku.genBoard()
        resetGame()
        inicializarArrayButtones()

        binding.customButton1.setOnClickListener {
            onNumberButtonClick(1)
        }

        binding.customButton2.setOnClickListener{
            onNumberButtonClick(2)
        }

        binding.customButton3.setOnClickListener{
            onNumberButtonClick(3)
        }

        binding.customButton4.setOnClickListener{
            onNumberButtonClick(4)
        }

        binding.customButton5.setOnClickListener{
            onNumberButtonClick(5)
        }

        binding.customButton6.setOnClickListener{
            onNumberButtonClick(6)
        }

        binding.customButton7.setOnClickListener{
            onNumberButtonClick(7)
        }

        binding.customButton8.setOnClickListener{
            onNumberButtonClick(8)
        }

        binding.customButton9.setOnClickListener{
            onNumberButtonClick(9)
        }

        binding.customButtonRemove.setOnClickListener{
            setSelected(10)
            if (SudokuModel.sudoku.currentNumber != EMPTY) {
                SudokuModel.sudoku.prevNumber = SudokuModel.sudoku.currentNumber }
            SudokuModel.sudoku.setPlayer(EMPTY)
        }
    }

    private fun inicializarArrayButtones() {
        for (i in 0 until 10) {
            val btnId = when (i) {
                0 -> binding.customButton1
                1 -> binding.customButton2
                2 -> binding.customButton3
                3 -> binding.customButton4
                4 -> binding.customButton5
                5 -> binding.customButton6
                6 -> binding.customButton7
                7 -> binding.customButton8
                8 -> binding.customButton9
                9 -> binding.customButtonRemove
                else -> throw IllegalArgumentException("Invalid index: $i")
            }
            btnarray[i] = btnId
        }
    }
    /**
     * Se llama cuando se hace clic en un botón numérico
     * @param number el número que se ha pulsado
     * Llama al método "setSelected" con el número seleccionado
     * Verifica si el número actual en el modelo de Sudoku es diferente del número seleccionado.
     * Si es así, el número anterior se establece como el número actual previo en el modelo de Sudoku.
     * Llama al método "setPlayer" del modelo de Sudoku con el número seleccionado como argumento.
     * */

    private fun onNumberButtonClick(number: Int) {
        setSelected(number)
        if (SudokuModel.sudoku.currentNumber != number) {
            SudokuModel.sudoku.prevNumber = SudokuModel.sudoku.currentNumber }
        SudokuModel.sudoku.setPlayer(number)
    }
    /**
     * se utiliza para establecer el botón seleccionado correspondiente
     * al número que se ha seleccionado en la interfaz de usuario de Sudoku.
     * @param x que representa el número seleccionado.
     * */
    private fun setSelected(x: Int){
        for(i in 0 until 10){
            btnarray[i]?.isSelected = i == (x-1)
        }
    }

    /**
     * Si se llega a llenar todas la celdas vacias comprueba si la solucion es correcta
     * si es correcta se guarda la solucion en el modelo y se muestra un mensaje de exito
     * si no es correcta se muestra un mensaje de fallo
     * */
    fun endGame() {
        if(SudokuModel.sudoku.validate()){
            //Toast.makeText(this, "Se ha completado con exito!", Toast.LENGTH_LONG).show()
            SudokuModel.sudoku.setHasEmpty(0)
            SudokuModel.sudoku.setIsBaseElement()
            val clock: Chronometer = binding.chornometer
            clock.stop()
            SudokuModel.winnedTimeWhenFinished =  clock.text.toString()
            val intento = Intent(this, MsgVictoria::class.java)
            intento.putExtra("imagen", "sudoku")
            startActivity(intento)
            this.finish()
        }
        else {
            clock.stop()
            Toast.makeText(this, "Error...", Toast.LENGTH_LONG).show()
        }
    }
    /**
     * Resetea el juego para un nuevo juego mediante el button reset que se encuantra en vista
     */
    private fun resetGame() {
        binding.btnReset.setOnClickListener {
            try {
                binding.chornometer.base = SystemClock.elapsedRealtime()
                SudokuModel.sudoku.setHasEmpty(0)
                SudokuModel.sudoku.setIsBaseElement()
                SudokuModel.sudoku.setBase(SudokuModel.baseMatrix)
                SudokuModel.sudoku.genBoard()
            } catch (e: Exception) {
                Toast.makeText(this, "Error $e", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mediaPlay = MediaPlayer.create(this,com.example.retomuzkiz.R.raw.bossfight)
        mediaPlay.isLooping = true
        mediaPlay.start()
        clock.start()
    }
    override fun onPause() {
        mediaPlay!!.stop()
        mediaPlay!!.release()
        super.onPause()
        clock.stop()
    }
}

