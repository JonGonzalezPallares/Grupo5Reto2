package com.example.retomuzkiz.laArenaHondartza.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ItemIntroBinding
import com.example.retomuzkiz.laArenaHondartza.modelo.Parrafo


class RvAdapterParrafos(
    private var parrafosList: List<Parrafo>,
    private var salir: (Int) -> Unit,
    private var seguiente: (Int) -> Unit,
    private var atras: (Int) -> Unit,
):RecyclerView.Adapter<ParrafosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParrafosViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_intro,parent,false)
        return ParrafosViewHolder(view,)
    }

    override fun onBindViewHolder(holder: ParrafosViewHolder, position: Int) {
       holder.bind(parrafosList,salir, seguiente,atras)
    }

    override fun getItemCount(): Int = parrafosList.size

}

class ParrafosViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemIntroBinding.bind(view)

    fun bind(parrafosList: List<Parrafo>, salir: (Int) -> Unit, seguiente: (Int) -> Unit, atras: (Int) -> Unit ) {
        binding.btnSalir.isVisible=false
        binding.btnAtras.isEnabled=false
        binding.btnSeguiente.isEnabled=false
        binding.tvParrafo.text = parrafosList[adapterPosition].parrafo


        if (bindingAdapterPosition >= 1){
            binding.btnAtras.isEnabled=true
            binding.btnAtras.setOnClickListener {
                atras(bindingAdapterPosition)
            }
        }
        if(bindingAdapterPosition < parrafosList.size-1){
            binding.btnSeguiente.isEnabled = true
            binding.btnSeguiente.setOnClickListener {
                seguiente(bindingAdapterPosition)
            }
        }
        if (bindingAdapterPosition == parrafosList.size-1){
            binding.btnSalir.isVisible=true
            binding.btnSalir.setOnClickListener {
                salir(bindingAdapterPosition)
            }
        }

    }




}
