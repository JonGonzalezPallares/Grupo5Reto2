package com.example.retomuzkiz.YourProgress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.retomuzkiz.R
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.databinding.ActivityProgresBinding
import com.example.retomuzkiz.room.TypeConverter
import com.example.retomuzkiz.usuario.currentUser
import android.view.animation.Animation
import android.view.animation.AnimationUtils


lateinit var binding :ActivityProgresBinding
lateinit var shine: View


class ProgressActivity : AppCompatActivity() {
    var echos = 0
    var progress = RetoGrupoCinco.progressDb.getUserProgress(currentUser!!.userId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityProgresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shine = binding.txtGamesCompleted
        this.supportActionBar!!.hide()
        binding.Navegation.setOnClickListener(){
           if(binding.fragmentContainerView2.visibility == 0){
               binding.fragmentContainerView2.visibility = View.GONE
           }else{
               binding.fragmentContainerView2.visibility = View.VISIBLE

           }
        }
        cargarImagenes()
        binding.txtTotPuntuacion.text = "${progress.totalPuntuation}"
        binding.txtGamesCompleted.setOnClickListener{
            if(echos == 7 || echos == 8){
                Toast.makeText(this, " Secreto", Toast.LENGTH_SHORT).show()
            }
        }

    }



    private fun cargarImagenes() {
        var gamesDone = TypeConverter.stringToSomeObjectList(progress.gamesDone)

        gamesDone.forEach() {  game->
            if(game.done) {
                echos++
                when(game.gameId){
                    1->  binding.imgSanJuan.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.irudia_san_juan_1
                        )

                    )

                    2 ->binding.imgItsaslur.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.itsaslur2_2
                        )

                    )


                    3->binding.imgPuenteRomano.setImageDrawable(
                            ContextCompat.getDrawable(
                                this,
                                R.drawable.puentecompleto
                            )

                        )

                    4->binding.imgFundicion.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.fundicion_pobela
                        )

                    )

                    5-> binding.imgLaArena.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.irudia_arena_2
                        )

                    )

                    6-> binding.imgHermita.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.hermita_pobena_1
                        )

                    )

                    7-> binding.imgCastillo.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.castillo
                        )

                    )

                }
            }

        }
        binding.txtGamesCompleted.text = "$echos/7"
        if(echos == 7 || echos == 8){
            binding.txtGamesCompleted.text = "$echos/7!!"
            binding.txtGamesCompleted.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shine_complete))
        }



    }

}