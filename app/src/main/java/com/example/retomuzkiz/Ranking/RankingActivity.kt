package com.example.retomuzkiz.Ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.currentUser
import com.example.retomuzkiz.currentProgress
import com.example.retomuzkiz.databinding.ActivityRankingBinding
import com.example.retomuzkiz.room.Usuario
import org.json.JSONObject

class RankingActivity : AppCompatActivity() {
    var binding = ActivityRankingBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.textView7.text = "RANKING ${currentUser!!.userClass}"
        var topThree = JSONObject()
        RetoGrupoCinco.mSocket.emit("get ranking", currentUser!!.userClass, currentProgress!!.totalPuntuation)
        RetoGrupoCinco.mSocket.on("top Three"){ args->
           topThree =  args[0] as JSONObject

        }
    }
}