package com.example.retomuzkiz

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.retomuzkiz.databinding.SingleActividadBinding
import com.example.retomuzkiz.databinding.VistaJugadorBinding

class rvDesplegableAdepter(var listaActividades: List<Actividad>): RecyclerView.Adapter<rvDesplegableAdepter.ViewHolder>() {
    class ViewHolder (vista: View) :RecyclerView.ViewHolder(vista){
        val binding = SingleActividadBinding.bind(vista)

        fun unir (actividad: Actividad){
            binding.txtTitulo.text = actividad.name
            binding.button2.setOnClickListener(){

            }
        }
    }

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
            .inflate(R.layout.single_actividad, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.unir(listaActividades[position])
    }

    override fun getItemCount(): Int {
        return listaActividades.size
    }
}