package com.example.retomuzkiz

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retomuzkiz.Burdinola.BurdinolaVideoActivity
import com.example.retomuzkiz.databinding.SingleActividadBinding
import com.example.retomuzkiz.databinding.VistaJugadorBinding
import com.example.retomuzkiz.puenteRomano.PuenteJuego
import com.example.retomuzkiz.puenteRomano.PuenteRomano

class rvDesplegableAdepter(var listaActividades: List<Actividad>, val context: Context): RecyclerView.Adapter<rvDesplegableAdepter.ViewHolder>() {
    protected lateinit var Nombres : ArrayList<String>


    class ViewHolder (vista: View) :RecyclerView.ViewHolder(vista){

        val binding = SingleActividadBinding.bind(vista)

        fun unir (actividad: Actividad, context: Context){
            val titulo = actividad.name
            binding.txtTitulo.text = actividad.name
            binding.btniniciarjuego.setOnClickListener(){
                cargaractividades(titulo, context)
            }
        }

        private fun cargaractividades(titulo: String, context: Context) {
            when(titulo) {

                "Pobaleko zubi erromanikoa"->{
                    val g = Intent(context, PuenteRomano::class.java)
                    startActivity(context,g,null)
                }
                "Pobaleko Burdinola"->{
                    val g = Intent(context, BurdinolaVideoActivity::class.java)
                    startActivity(context,g,null)
                }
                "Pobeñako Ermita"->{

                }
                "La Arena hondartza"->{

                }
                "Itsaslur Ibilbidea"->{

                }
                "Muñatones Gaztelua"->{

                }
                "San Juan Gaua"->{

                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Nombres = arrayListOf<String>()


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
        holder.unir(listaActividades[position], context)
    }

    override fun getItemCount(): Int {
        return listaActividades.size
    }
    fun cargaractividades(titulo:String){


    }
}