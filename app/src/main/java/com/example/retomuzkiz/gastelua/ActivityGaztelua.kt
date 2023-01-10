package com.example.retomuzkiz.gastelua

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import com.example.retomuzkiz.databinding.ActivityGazteluaBinding

class ActivityGaztelua : AppCompatActivity() {
    private lateinit var Imagen : ImageView
    lateinit var binding: ActivityGazteluaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGazteluaBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.imgarribaizq.setOnLongClickListener(longClickListener)
        binding.imgarribamed.setOnLongClickListener(longClickListener)
        binding.imgarribader.setOnLongClickListener(longClickListener)
        binding.imgcentroizq.setOnLongClickListener(longClickListener)
        binding.imgcentromed.setOnLongClickListener(longClickListener)
        binding.imgcentroder.setOnLongClickListener(longClickListener)
        binding.imgabajoizq.setOnLongClickListener(longClickListener)
        binding.imgabajomed.setOnLongClickListener(longClickListener)
        binding.imgabajoder.setOnLongClickListener(longClickListener)

        binding.imagenarribaizq.setOnDragListener(dragListener)
        binding.imagenarribamed.setOnDragListener(dragListener)
        binding.imagenarribader.setOnDragListener(dragListener)
        binding.imagenmedioizq.setOnDragListener(dragListener)
        binding.imagenmediomed.setOnDragListener(dragListener)
        binding.imagenmedioder.setOnDragListener(dragListener)
        binding.imagenabajoizq.setOnDragListener(dragListener)
        binding.imagenabajomed.setOnDragListener(dragListener)
        binding.imagenabajoder.setOnDragListener(dragListener)

        binding.imagenarribaizq.setAlpha(0)
        binding.imagenarribamed.setAlpha(0)
        binding.imagenarribader.setAlpha(0)
        binding.imagenmedioizq.setAlpha(0)
        binding.imagenmediomed.setAlpha(0)
        binding.imagenmedioder.setAlpha(0)
        binding.imagenabajoizq.setAlpha(0)
        binding.imagenabajomed.setAlpha(0)
        binding.imagenabajoder.setAlpha(0)

    }

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
    private val dragListener = View.OnDragListener{ v, event ->
        val receiverView: ImageView = v as ImageView

        when(event.action){
            DragEvent.ACTION_DRAG_STARTED ->{
                if(event.clipDescription.label == receiverView.tag as String){
                    //v.visibility = View.VISIBLE
                }
                true
            }
            DragEvent.ACTION_DRAG_ENTERED ->{

                v.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION ->{
                //Imagen.visibility = View.VISIBLE
                true
            }
            DragEvent.ACTION_DRAG_EXITED ->{
                if(event.clipDescription.label == receiverView.tag as String) {

                    v.invalidate()
                }
                true
            }

            DragEvent.ACTION_DROP ->{
                if(event.clipDescription.label == receiverView.tag as String) {
                    receiverView.setAlpha(255)

                    println("correcto")

                    Imagen.visibility = View.INVISIBLE

                }else{
                    receiverView.setAlpha(0)
                    println("error")

                    Imagen.visibility = View.VISIBLE


                }
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                //Imagen.visibility = View.VISIBLE


                true
            }

            else -> {
                false
            }        }
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
}