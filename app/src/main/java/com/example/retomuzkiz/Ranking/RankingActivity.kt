package com.example.retomuzkiz.Ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.Jugador
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.mSocket
import com.example.retomuzkiz.databinding.ActivityRankingBinding
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

        //Para borrar la barra superior
        this.supportActionBar!!.hide()

        mSocket.emit(
            "get ranking",
            1
        )

        mSocket.on("top three"){ args->

            topThree = args[0] as JSONArray
            actualizarTopTres()
        }
    }

    private fun actualizarTopTres() {
        //1º Puesto
        val bestUser = topThree[0] as JSONObject

        //2º Puesto
        val secondUser = topThree[1] as JSONObject

        //3º Puesto
        val thirdUser = topThree[2] as JSONObject

        val listaJugadores = arrayListOf(
            Jugador(bestUser["userName"].toString(),bestUser["userClass"].toString(),bestUser["totPuntuation"].toString()),
            Jugador(secondUser["userName"].toString(),secondUser["userClass"].toString(),secondUser["totPuntuation"].toString()),
            Jugador(thirdUser["userName"].toString(),thirdUser["userClass"].toString(),thirdUser["totPuntuation"].toString())
        )

        runOnUiThread {
            binding.rvRanking.adapter = RVRankingAdapter(listaJugadores,this)
        }
    }
}