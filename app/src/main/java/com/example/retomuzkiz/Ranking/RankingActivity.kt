package com.example.retomuzkiz.Ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.Jugador
import com.example.retomuzkiz.YourProgress.binding
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.mSocket
import com.example.retomuzkiz.currentProgress
import com.example.retomuzkiz.currentUser
import com.example.retomuzkiz.databinding.ActivityRankingBinding
import com.example.retomuzkiz.room.Usuario
import org.json.JSONArray
import org.json.JSONObject

class RankingActivity : AppCompatActivity() {
    companion object {


        var topThree = JSONArray()

    }
    lateinit var binding:ActivityRankingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RetoGrupoCinco.mSocket.emit(
            "get ranking",
            1
        )
        mSocket.on("top three"){ args->

            topThree = args[0] as JSONArray
            actualizarTopTres()
        }




    }
    fun actualizarTopTres() {

        //1º Puesto
        val bestUser = topThree[0] as JSONObject

        //2º Puesto
        val secondUser = topThree[1] as JSONObject
        //3º Puesto
        val thirdUser = topThree[2] as JSONObject
        var listaJugadores = arrayListOf<Jugador>(
            Jugador(bestUser["userName"].toString(),bestUser["userClass"].toString(),bestUser["totPuntuation"].toString()),
            Jugador(secondUser["userName"].toString(),secondUser["userClass"].toString(),secondUser["totPuntuation"].toString()),
            Jugador(thirdUser["userName"].toString(),thirdUser["userClass"].toString(),thirdUser["totPuntuation"].toString())
        )
        //var listaTop = topThree as Array<JSONObject>

        RetoGrupoCinco.mSocket.on("top three") { args ->
            topThree = args[0] as JSONArray
            actualizarTopTres()

        }

     runOnUiThread {
         binding.rvRanking.adapter = RVRankingAdapter(listaJugadores,this)
//            binding.txtFirstName.text = "${bestUser["userName"]}"
//            binding.txtFirstClass.text = "${bestUser["userClass"]}"
//            binding.txtFirstPuntuation.text = "${bestUser["totPuntuation"]}"
//
//            binding.txtSecondName.text = "${secondUser["userName"]}"
//            binding.txtSecondClass.text = "${secondUser["userClass"]}"
//            binding.txtSecondPuntuation.text = "${secondUser["totPuntuation"]}"
//
//            binding.txtThirdName.text = "${thirdUser["userName"]}"
//            binding.txtThirdClass.text = "${thirdUser["userClass"]}"
//            binding.txtThirdPuntuation.text = "${thirdUser["totPuntuation"]}"
        }
    }

}


