package com.example.retomuzkiz.funcionesExtension

//Funcion de extension para desordenar el array de int
fun IntArray.desordeno(): ArrayList<Int> {
    val resultado = ArrayList<Int>()
    //Los valores que devuelve el for van desde el 0 hasta el cinco
    for (i in 0 until this.size){
        resultado.add(i % this.size)
    }

    resultado.shuffle()
    return resultado
}