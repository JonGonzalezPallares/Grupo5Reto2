package com.example.retomuzkiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retomuzkiz.databinding.VistaJugadorBinding
import com.example.retomuzkiz.room.Usuario

class UsuariosAdapter(private var lista:List<Usuario>,private val escuchador:(Usuario)->Unit) :RecyclerView.Adapter<UsuariosAdapter.ViewHolder>(){

    //Lo que emvuelve a cada elemento del recycleview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater
            //Tenemos que inflarlo del mas cercano al que estemos
            .from(parent.context)
            /*
            Tenemos que hacer referencia a:
                1. De donde vamos a coger el estilo de la vista
                2. Donde vamos a colocarlo
                3. Esto siempre tiene que estar a false, ya que ya estamos dentro del recycleview
             */
            .inflate(R.layout.vista_jugador, parent, false)
        return ViewHolder(vista)
    }

    //Relacionar cada elemento
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Creamos una funcion que nos haga la union de los elementos
        //Accedemos a la lista de asignaturas para cada posicion
        holder.link(lista[position])
        //Al clickar sobre algun item
        holder.itemView.setOnClickListener {
            escuchador(lista[position])
        }

    }

    //Devuelve la cantidad de elementos
    override fun getItemCount(): Int {
        return lista.size
    }
    /*
    Otra manera para hacerlo
    override fun getItemCount(): Int = lista.size
     */

    class ViewHolder (vista: View) :RecyclerView.ViewHolder(vista){
        val binding = VistaJugadorBinding.bind(vista)

        fun link (usuario: Usuario){
            binding.txtNombre.text = usuario.name

        }
    }
}