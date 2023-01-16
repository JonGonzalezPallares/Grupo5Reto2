package com.example.retomuzkiz.funcionesExtension

import android.graphics.Typeface
import android.widget.RadioButton
import android.widget.RadioGroup

//Funcion de extension para eliminar el estilo de negrita de las respuestas
fun RadioGroup.eliminarNegrita(seleTxt: List<List<Int>>, cantidad: Int) {
    for(paso in 0 until 3){
        //Recogemos el texto de todos los botones
        val texto = findViewById<RadioButton>(seleTxt[cantidad-1][paso].toString().toInt())
        //Se ponen los textos sin el efecto de negrita
        texto.typeface = Typeface.DEFAULT
    }
}