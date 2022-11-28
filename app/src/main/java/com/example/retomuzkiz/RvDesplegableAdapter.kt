package com.example.retomuzkiz

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.retomuzkiz.burdinola.BurdinolaVideoActivity
import com.example.retomuzkiz.databinding.SingleActividadBinding
import com.example.retomuzkiz.itsaslurIbilbidea.PantallaEspera
import com.example.retomuzkiz.laArenaHondartza.IntroActivity
import com.example.retomuzkiz.ponekakoermita.MarineroActivity
import com.example.retomuzkiz.gastelua.PuzzleActivity
import com.example.retomuzkiz.puenteRomano.PuenteRomano
import com.example.retomuzkiz.Laberinto.ActivityLaberinto
import com.example.retomuzkiz.clases.Actividad

class RvDesplegableAdapter(var listaActividades: List<Actividad>, val context: Context): RecyclerView.Adapter<RvDesplegableAdapter.ViewHolder>() {
    private lateinit var Nombres : ArrayList<String>


    class ViewHolder (vista: View) :RecyclerView.ViewHolder(vista){

        val binding = SingleActividadBinding.bind(vista)

        fun unir (actividad: Actividad, context: Context){
            val titulo = actividad.name
            binding.txtTitulo.text = actividad.name
            binding.btniniciarjuego.setOnClickListener{
                loadActivityes(titulo, context)
                println(titulo)
            }
        }

        private fun loadActivityes(titulo: String, context: Context) {
            when(titulo) {

                "Pobaleko zubi erromanikoa"->{
                    val intento = Intent(context, PuenteRomano::class.java)
                    startActivity(context, intento, null)
                }
                "Pobaleko Burdinola"->{
                    val intento = Intent(context, BurdinolaVideoActivity::class.java)
                    startActivity(context, intento,null)
                }
                "Pobeñako Ermita"->{
                    val g = Intent(context, MarineroActivity::class.java)
                    startActivity(context,g,null)
                }
                "La Arena hondartza"->{
                    val intento = Intent(context, IntroActivity::class.java)
                    startActivity(context, intento,null)
                }
                "Itsaslur Ibilbidea"->{
                    val intento = Intent(context, PantallaEspera::class.java)
                    startActivity(context, intento,null)
                }
                "Muñatones Gaztelua"->{
                    val intento = Intent(context, PuzzleActivity::class.java)
                    startActivity(context, intento,null)

                }
                "San Juan Gaua"->{
                    val intento = Intent(context, ActivityLaberinto::class.java)
                    startActivity(context, intento, null)
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