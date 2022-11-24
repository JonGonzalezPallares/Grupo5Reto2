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
    private var play: () -> Unit,
    private var nextItem: (Int) -> Unit,
    private var privuisItem: (Int) -> Unit,
):RecyclerView.Adapter<ParrafosViewHolder>() {

    //______________________________________________________________________________________________
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParrafosViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_intro,parent,false)
        return ParrafosViewHolder(view,)
    }

    //______________________________________________________________________________________________
    override fun onBindViewHolder(holder: ParrafosViewHolder, position: Int) {
       holder.bind(parrafosList,play, nextItem,privuisItem)
    }

    //______________________________________________________________________________________________
    override fun getItemCount(): Int = parrafosList.size

}

class ParrafosViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemIntroBinding.bind(view)

    //______________________________________________________________________________________________
    fun bind(parrafosList: List<Parrafo>,
             play:() -> Unit,
             nextItem: (Int) -> Unit,
             privuisItem: (Int) -> Unit
    ) {

        binding.btnSalir.isVisible=false
        binding.btnAtras.visibility = View.INVISIBLE
        binding.tvParrafo.text = parrafosList[bindingAdapterPosition].parrafo

        // si la posicion es mayor a cero, habilitar el button atras con lambda
        if (bindingAdapterPosition >= 1){
            binding.btnAtras.visibility = View.VISIBLE
            binding.btnAtras.setOnClickListener {
                privuisItem(bindingAdapterPosition)

            }
        }
        // si la posicion es menor que el ultimo item, habilitar el button seguiente con lambda
        if(bindingAdapterPosition < parrafosList.size-1){
            binding.btnSeguiente.visibility = View.VISIBLE
            binding.btnSeguiente.setOnClickListener {
                nextItem(bindingAdapterPosition)
            }
        }
        // si la posicion es igual a ultimo item, habilitar el button salir con lambda
        if (bindingAdapterPosition == parrafosList.size-1){
            binding.btnSalir.isVisible=true
            binding.btnSeguiente.visibility = View.INVISIBLE
            binding.btnSalir.setOnClickListener {
                play()
            }
        }

    }




}
