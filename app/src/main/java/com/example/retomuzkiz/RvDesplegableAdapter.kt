package com.example.retomuzkiz

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.retomuzkiz.burdinola.BurdinolaVideoActivity
import com.example.retomuzkiz.databinding.SingleActividadBinding
import com.example.retomuzkiz.itsaslurIbilbidea.PantallaEspera
import com.example.retomuzkiz.laArenaHondartza.IntroActivity
import com.example.retomuzkiz.ponekakoermita.MarineroActivity
import com.example.retomuzkiz.puenteRomano.PuenteRomano
import com.example.retomuzkiz.Laberinto.ActivityLaberinto
import com.example.retomuzkiz.clases.Actividad
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.gastelua.ActivityGaztelua
//import com.example.retomuzkiz.gastelua.ActivityGaztelua
import com.example.retomuzkiz.gastelua.ExplicacionActivity
import com.example.retomuzkiz.room.Game
import com.example.retomuzkiz.room.Usuario


class RvDesplegableAdapter(var listaActividades: Game, var user : Usuario,  val context: Context): RecyclerView.Adapter<RvDesplegableAdapter.ViewHolder>() {
    private lateinit var Nombres : ArrayList<String>
    class ViewHolder (vista: View) :RecyclerView.ViewHolder(vista){

        val binding = SingleActividadBinding.bind(vista)
        fun unir (game: Game, user:Usuario, context: Context){
            val id = game.gameId
            var usuario = user
            loadImages(game.res,context)
            binding.txtTitulo.text = game.gameName
            binding.btniniciarjuego.setOnClickListener{
                loadActivityes(id, user, context)
                var fragment = Fragment_mobile_rotation()
            }
        }

        private fun loadImages(name: String, context: Context) {
            when (name) {
                RetoGrupoCinco.SITESNAMES.PUENTE_ROMANO_IMG -> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.puentecompleto)

                    )
                    val a: String = context.resources.getString(R.string.DescripcionPuente)
                    binding.txtdescripcion.text = a
                }

                RetoGrupoCinco.SITESNAMES.POBENA_FUNDICION_IMG -> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.fundicion_pobela)
                    )
                    val a: String = context.resources.getString(R.string.DescripcionBurdinola)
                    binding.txtdescripcion.text = a
                }

                RetoGrupoCinco.SITESNAMES.POBENA_HERMITA_IMG -> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.hermita_pobena_1)

                    )
                    val a: String = context.resources.getString(R.string.Descripcionermita)
                    binding.txtdescripcion.text = a
                }

                RetoGrupoCinco.SITESNAMES.PLAYA_LA_ARENA_IMG -> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.irudia_arena_2)
                    )
                    val a: String = context.resources.getString(R.string.DescripcionLaarena)
                    binding.txtdescripcion.text =a
                }

                RetoGrupoCinco.SITESNAMES.ITSASLUR_IBILBIDEA_IMG-> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.itsaslur2_2)
                    )
                    val a: String = context.resources.getString(R.string.Descripcionitzalsur)

                    binding.txtdescripcion.text = a


                }

                RetoGrupoCinco.SITESNAMES.CASTILLO_MUNATONES_IMG -> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.castillo)
                    )
                    val a: String = context.resources.getString(R.string.DescripcionCastillo)
                    binding.txtdescripcion.text =a
                }

                RetoGrupoCinco.SITESNAMES.NOCHE_SAN_JUAN_IMG -> {
                    binding.img.setImageDrawable(getDrawable(context,
                        R.drawable.irudia_san_juan_1)
                    )
                    val a: String = context.resources.getString(R.string.DescripcionSanjuan)
                    binding.txtdescripcion.text =a
                }
            }
        }

        private fun loadActivityes(id: Int,user: Usuario, context: Context) {
            when(id) {
                1->{

                    val intento = Intent(context, ActivityLaberinto::class.java).putExtra("user", user)
                    startActivity(context, intento, null)
                }

                2->{
                    val intento = Intent(context, PantallaEspera::class.java).putExtra("user", user)
                    startActivity(context, intento,null)
                }

                3->{
                    val intento = Intent(context, PuenteRomano::class.java).putExtra("user", user)
                    startActivity(context, intento,null)
                }

                4->{
                    val intento = Intent(context,BurdinolaVideoActivity::class .java).putExtra("user", user)
                    startActivity(context, intento,null)
                }

                5->{
                    val intento = Intent(context, IntroActivity::class.java).putExtra("user", user)
                    startActivity(context, intento,null)
                }
                6->{

                    val intento = Intent(context, MarineroActivity::class.java).putExtra("user", user)
                    startActivity(context, intento,null)
                }

                7->{
                    val intento = Intent(context, ExplicacionActivity::class.java).putExtra("user", user)
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
        holder.unir(listaActividades, user, context)
    }

    override fun getItemCount(): Int {
        return 1
    }
}

