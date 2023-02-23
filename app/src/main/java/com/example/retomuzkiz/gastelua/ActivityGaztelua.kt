package com.example.retomuzkiz.gastelua

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Point
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.example.retomuzkiz.*
import com.example.retomuzkiz.Laberinto.ActivityLaberinto.Companion.cambio
import com.example.retomuzkiz.databinding.ActivityGazteluaBinding

class ActivityGaztelua : AppCompatActivity() {
    lateinit var mediaPlay: MediaPlayer
    private lateinit var Imagen : ImageView
    lateinit var binding: ActivityGazteluaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        fin = 0
        startTimer()
        super.onCreate(savedInstanceState)
        binding = ActivityGazteluaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        //Creamos una lista para guardar las imagenes respuesta
        val imagenesRes = listOf(
            binding.imgarribaizq,
            binding.imgarribamed,
            binding.imgarribader,
            binding.imgcentroizq,
            binding.imgcentromed,
            binding.imgcentroder,
            binding.imgabajoizq,
            binding.imgabajomed,
            binding.imgabajoder
        )

        imagenesRes.forEach {
            //Añadimos el listener
            it.setOnLongClickListener(longClickListener)
        }

        //Creamos una lista para guardar las imagenes que arrastramos
        val imagenesAr = listOf(
            binding.imagenarribaizq,
            binding.imagenarribamed,
            binding.imagenarribader,
            binding.imagenmedioizq,
            binding.imagenmediomed,
            binding.imagenmedioder,
            binding.imagenabajoizq,
            binding.imagenabajomed,
            binding.imagenabajoder
        )

        imagenesAr.forEach {
            //Añadimos el listener correspondiente
            it.setOnDragListener(dragListener)
            //Los hacemos invisibles
            it.alpha = 0F
        }

        binding.btnayuda.setOnClickListener{
            dialogoAyudaJuegos("castillo",this,layoutInflater)
        }
    }

    //funcion del long listener de arrastrar
    private val longClickListener = View.OnLongClickListener { v ->
        Imagen = v as ImageView
        val item = ClipData.Item(v.tag as? CharSequence)
        val dragData = ClipData(
            v.tag as CharSequence,
            arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
            item
        )
        val myShadow = MyDragShadowBuilder(v)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            v.startDragAndDrop(dragData,myShadow,null,0)
        }else{
            v.startDrag(dragData,myShadow,null,0)
        }
        true
    }

    @SuppressLint("range")
    //funcion de deteccion de eventos al arrastrar
    private val dragListener = View.OnDragListener{ v, event ->
        val receiverView: ImageView = v as ImageView

        when(event.action){
            DragEvent.ACTION_DRAG_STARTED ->{
                true
            }
            DragEvent.ACTION_DRAG_ENTERED ->{
                true
            }
            DragEvent.ACTION_DRAG_LOCATION ->{
                true
            }
            DragEvent.ACTION_DRAG_EXITED ->{

                true
            }

            DragEvent.ACTION_DROP ->{
                if(event.clipDescription.label == receiverView.tag as String) {

                    receiverView.alpha = 255F

                    Imagen.visibility = View.INVISIBLE

                    //comprobacion de todas las partes visibles para finalizar el juego
                    if(!binding.imgarribaizq.isVisible && !binding.imgarribamed.isVisible
                        && !binding.imgarribader.isVisible && !binding.imgcentroizq.isVisible
                        && !binding.imgcentromed.isVisible && !binding.imgcentroder.isVisible
                        && !binding.imgabajoizq.isVisible && !binding.imgabajomed.isVisible
                        && !binding.imgabajoder.isVisible)
                    {
                        stopTimer()
                        juegoAcabado(6)
                        fin++
                        cambio = true
                        finalizar(this,"castillo")
                        this.finish()
                    }

                }else{
                    Imagen.visibility = View.VISIBLE
                }
                true
            }
            else -> {
                false
            }
        }
    }

    //crear sombreado al arrastrar
    private class MyDragShadowBuilder(val v : View) : View.DragShadowBuilder(v){
        override fun onProvideShadowMetrics(size: Point, touch: Point) {
            size.set(view.width,view.height)
            touch.set(view.width / 2,view.height / 2)
        }

        override fun onDrawShadow(canvas: Canvas) {
            v.draw(canvas)
        }
    }
    override fun onPause() {

        super.onPause()
        mediaPlay!!.stop()
        mediaPlay!!.release()

        if(cambio){
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        mediaPlay = MediaPlayer.create(this,com.example.retomuzkiz.R.raw.intergalactic_odyssey)
        mediaPlay.isLooping = true
        mediaPlay.start()


    }
}