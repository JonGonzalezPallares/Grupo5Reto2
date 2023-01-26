package com.example.retomuzkiz.Ranking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.retomuzkiz.Jugador
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.SingleRankBinding

class RVRankingAdapter(var lista:ArrayList<Jugador>, var context:Context) : RecyclerView.Adapter<RVRankingAdapter.ViewHolder>() {
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
            .inflate(R.layout.single_rank, parent, false)
        return ViewHolder(vista)
    }

    //Relacionar cada elemento

    //Devuelve la cantidad de elementos
    override fun getItemCount(): Int {
        return lista.size
    }

    /*
    Otra manera para hacerlo
    override fun getItemCount(): Int = lista.size
     */

    class ViewHolder (vista: View) : RecyclerView.ViewHolder(vista){

        val binding = SingleRankBinding.bind(vista)

        fun linkFirst(jugador: Jugador,context:Context){
            binding.imageView.setImageDrawable(
                ContextCompat.getDrawable(context,
                    R.drawable.medallaoro
                ))
            binding.txtNombre.text = jugador.nombre
            binding.txtClass.text = jugador.clase
            binding.txtPuntuacion.text = jugador.puntuacion
        }

        fun linkSecond(jugador: Jugador, context: Context) {
            binding.imageView.setImageDrawable(
                ContextCompat.getDrawable(context,
                    R.drawable.medallaplata
                ))
            binding.txtNombre.text = jugador.nombre
            binding.txtClass.text = jugador.clase

            binding.txtPuntuacion.text = jugador.puntuacion
        }
        fun linkThird(jugador: Jugador, context: Context) {
            binding.imageView.setImageDrawable(
                ContextCompat.getDrawable(context,
                    R.drawable.medallabronce
                ))
            binding.txtNombre.text = jugador.nombre
            binding.txtClass.text = jugador.clase

            binding.txtPuntuacion.text = jugador.puntuacion
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       if(position==0){
           holder.linkFirst(lista[position],context)

       }else if(position == 1){
           holder.linkSecond(lista[position],context)

       }
       else if(position == 2){
           holder.linkThird(lista[position],context)
       }
    }


}


