package com.example.retomuzkiz.funcionesExtension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

//Funcion de extension que se encarga de cambiar entre los diferentes fragmentos
fun Fragment.cambiarF(id: Int, supportFragmentManager: FragmentManager) {
    //Creamos una variable para la transaccion
    val transicion = supportFragmentManager.beginTransaction().setReorderingAllowed(true)
    //Le añadimos a que contenedor tiene que hacer referencia, y le pasamos el fragmento que queremos cargar
    transicion.replace(id, this)
    //Hacemos el cambio
    transicion.commit()
}