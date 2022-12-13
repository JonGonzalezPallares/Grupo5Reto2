package com.example.retomuzkiz

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.retomuzkiz.burdinola.BurdinolaVideoActivity
import com.example.retomuzkiz.databinding.SingleActividadBinding
import com.example.retomuzkiz.itsaslurIbilbidea.PantallaEspera
import com.example.retomuzkiz.laArenaHondartza.IntroActivity
import com.example.retomuzkiz.ponekakoermita.MarineroActivity
import com.example.retomuzkiz.puenteRomano.PuenteRomano
import com.example.retomuzkiz.Laberinto.ActivityLaberinto
import com.example.retomuzkiz.clases.Actividad
//import com.example.retomuzkiz.gastelua.ActivityGaztelua
import com.example.retomuzkiz.gastelua.ExplicacionActivity

class RvDesplegableAdapter(var listaActividades: List<Actividad>, val context: Context): RecyclerView.Adapter<RvDesplegableAdapter.ViewHolder>() {
    private lateinit var Nombres : ArrayList<String>

    class ViewHolder (vista: View) :RecyclerView.ViewHolder(vista){

        val binding = SingleActividadBinding.bind(vista)

        fun unir (actividad: Actividad, context: Context){
            val titulo = actividad.name
            loadImages(actividad.img,context)
            binding.txtTitulo.text = actividad.name
            binding.btniniciarjuego.setOnClickListener{
                loadActivityes(titulo, context)
                println(titulo)
            }
        }

        private fun loadImages(name: String, context: Context) {
            when (name) {
                MapsActivity.SITESNAMES.PUENTE_ROMANO_IMG -> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.puentecompleto)
                    )
                }

                MapsActivity.SITESNAMES.POBENA_FUNDICION_IMG_1 -> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.fundicion_pobela)
                    )
                }

                MapsActivity.SITESNAMES.POBENA_HERMITA_IMG -> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.hermita_pobena_1)
                    )
                }

                MapsActivity.SITESNAMES.PLAYA_LA_ARENA_IMG -> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.irudia_arena_2)
                    )
                }

                MapsActivity.SITESNAMES.ITSASLUR_IBILBIDEA_IMG_1-> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.itsaslur2_2)
                    )
                }

                MapsActivity.SITESNAMES.CASTILLO_MUNATONES_IMG -> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.castillo)
                    )
                }

                MapsActivity.SITESNAMES.NOCHE_SAN_JUAN_IMG -> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.irudia_san_juan_1)
                    )
                }
            }
        }

        private fun loadActivityes(titulo: String, context: Context) {
            when(titulo) {
                MapsActivity.SITESNAMES.PUENTE_ROMANO->{
                    val intento = Intent(context, PuenteRomano::class.java)
                    startActivity(context, intento, null)
                }

                MapsActivity.SITESNAMES.POBENA_FUNDICION->{
                    val intento = Intent(context, BurdinolaVideoActivity::class.java)
                    startActivity(context, intento,null)
                }

                MapsActivity.SITESNAMES.POBENA_HERMITA->{
                    val intento = Intent(context, MarineroActivity::class.java)
                    startActivity(context, intento,null)
                }

                MapsActivity.SITESNAMES.PLAYA_LA_ARENA->{
                    val intento = Intent(context, IntroActivity::class.java)
                    startActivity(context, intento,null)
                }

                MapsActivity.SITESNAMES.ITSASLUR_IBILBIDEA->{
                    val intento = Intent(context, PantallaEspera::class.java)
                    startActivity(context, intento,null)
                }

                MapsActivity.SITESNAMES.CASTILLO_MUNATONES->{
                    val intento = Intent(context, ExplicacionActivity::class.java)
                    startActivity(context, intento,null)
                }

                MapsActivity.SITESNAMES.NOCHE_SAN_JUAN->{
                    val intento = Intent(context, ActivityLaberinto::class.java)
                    startActivity(context, intento, null)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Nombres = arrayListOf()

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

