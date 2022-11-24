package com.example.retomuzkiz.burdinola

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.example.retomuzkiz.R
import com.example.retomuzkiz.databinding.ActivityBurdinolaVideoBinding

class BurdinolaVideoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBurdinolaVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_burdinola_video)
        binding = ActivityBurdinolaVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.burdinolavideo)

        //Para borrar la barra superior
        //this.supportActionBar!!.hide()

        val offlineUri = Uri.parse("android.resource://$packageName/${R.raw.pobalekoburdinola}")
        binding.burdinolavideo.setMediaController(mediaController)
        binding.burdinolavideo.setVideoURI(offlineUri)
        binding.burdinolavideo.requestFocus()
        binding.burdinolavideo.canPause()
        binding.burdinolavideo.canSeekBackward()
        binding.burdinolavideo.canSeekForward()
        binding.burdinolavideo.start()

        binding.btnStart.setOnClickListener {
            binding.burdinolavideo.start()
        }

        binding.btnJuego.setOnClickListener{
            startActivity(Intent(this, BurdinolaSopaActivity::class.java))
            finish()

        }




    }
}