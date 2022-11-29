package com.example.retomuzkiz

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.retomuzkiz.clases.Actividad
import com.example.retomuzkiz.databinding.SingleActividadBinding
import com.example.retomuzkiz.databinding.SingleImageDesplegableBinding

class RvDesplegableImageAdapter(var listImage: List<String>,var actividad: Actividad, var context: Context) : RecyclerView.Adapter<RvDesplegableImageAdapter.ViewHolder>() {
    class ViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {

        val binding = SingleImageDesplegableBinding.bind(vista)

        fun bind(name: String, context: Context) {


            loadImage(name, context)

        }

        private fun loadImage(name: String, context: Context) {
            when (name) {
                MapsActivity.SITESNAMES.PUENTE_ROMANO_IMG -> {

                    binding.img.setImageDrawable(ContextCompat.getDrawable(context,
                        R.drawable.puentecompleto))
                }
                MapsActivity.SITESNAMES.POBENA_FUNDICION_IMG_1 -> {
                    binding.img.setImageDrawable(ContextCompat.getDrawable(context,
                        R.drawable.fundicion_pobela))
                }
                MapsActivity.SITESNAMES.POBENA_HERMITA_IMG -> {
                    binding.img.setImageDrawable(ContextCompat.getDrawable(context,
                        R.drawable.hermita_pobena_1))

                }
                MapsActivity.SITESNAMES.PLAYA_LA_ARENA_IMG -> {
                    binding.img.setImageDrawable(ContextCompat.getDrawable(context,
                        R.drawable.irudia_arena_2))
                }
                MapsActivity.SITESNAMES.ITSASLUR_IBILBIDEA_IMG_1-> {
                    binding.img.setImageDrawable(ContextCompat.getDrawable(context,
                        R.drawable.itsaslur2_2))
                }
                MapsActivity.SITESNAMES.CASTILLO_MUNATONES_IMG -> {
                    binding.img.setImageDrawable(ContextCompat.getDrawable(context,
                        R.drawable.castillo))
                }
                MapsActivity.SITESNAMES.NOCHE_SAN_JUAN_IMG -> {
                    binding.img.setImageDrawable(ContextCompat.getDrawable(context,
                        R.drawable.irudia_san_juan_1))
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val vista = LayoutInflater
            //Tenemos que inflarlo del mas cercano al que estemos
            .from(parent.context)
            /*
            Tenemos que hacer referencia a:
                1. De donde vamos a coger el estilo de la vista
                2. Donde vamos a colocarlo
                3. Esto siempre tiene que estar a false, ya que ya estamos dentro del recycleview
             */
            .inflate(R.layout.single_image_desplegable, parent, false)
        return RvDesplegableImageAdapter.ViewHolder(vista)
    }


    override fun getItemCount(): Int {
        return listImage.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(actividad.name,context)
    }


}
