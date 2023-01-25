package com.example.retomuzkiz.Ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.YourProgress.binding
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.currentUser
import com.example.retomuzkiz.currentProgress
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
            currentUser!!.userId,
            currentUser!!.name,
            currentUser!!.userClass,
            currentProgress!!.totalPuntuation
        )

        RetoGrupoCinco.mSocket.on("top Three") { args ->
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


        runOnUiThread {
            binding.txtFirstName.text = "${bestUser["userName"]}"
            binding.txtFirstClass.text = "${bestUser["userClass"]}"
            binding.txtFirstPuntuation.text = "${bestUser["totPuntuation"]}"

            binding.txtSecondName.text = "${secondUser["userName"]}"
            binding.txtSecondClass.text = "${secondUser["userClass"]}"
            binding.txtSecondPuntuation.text = "${secondUser["totPuntuation"]}"

            binding.txtThirdName.text = "${thirdUser["userName"]}"
            binding.txtThirdClass.text = "${thirdUser["userClass"]}"
            binding.txtThirdPuntuation.text = "${thirdUser["totPuntuation"]}"
        }
    }

}


