package com.example.retomuzkiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.database
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.progressDb
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.userDb
import com.example.retomuzkiz.databinding.ActivityCrearClaseSocketBinding
import com.example.retomuzkiz.room.Game
import com.example.retomuzkiz.room.Progress
import com.example.retomuzkiz.room.TypeConverter
import com.example.retomuzkiz.room.Usuario

class ActivityCrearClaseSocket : AppCompatActivity() {
    private lateinit var binding: ActivityCrearClaseSocketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCrearClaseSocketBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        this.supportActionBar!!.hide()

        binding.btnCrearClase.setOnClickListener() {
            if (comprobarCampos()) {
                var user = Usuario("${database.usuarioDao.getAllUsers().size + 1}",
                    binding.txtNombreProfesor.text.toString(),
                    binding.txtNombreClase.text.toString(),
                    true)
                userDb.insertUser(user)
                val progress = Progress(
                    user.userId,
                    0,
                    0,
                    TypeConverter.someObjectListToString(cargarJuegos(user))
                )
                progressDb.insertProgress(progress)
                RetoGrupoCinco.mSocket.connect()
                RetoGrupoCinco.mSocket.emit("join server", user.name)
                RetoGrupoCinco.mSocket.emit("create room", binding.txtNombreClase.text.toString())
                RetoGrupoCinco.mSocket.emit("join room", binding.txtNombreClase.text.toString())
                showDialog(this,"","Bienvenido!")
                startActivity(Intent(this, MapsActivity::class.java).putExtra("user", user))
                setUser(user)
                finish()
            } else {
                showDialog(this,
                    " Algo no fue como debía. Recuerde rellenar todos los campos y proporcionar una contraseña válida",
                    "Error")
            }
        }
    }

    private fun comprobarCampos(): Boolean {
        var res = false
       var clases = userDb.getAllUsers()
        clases.forEach {
            if (!binding.txtNombreClase.text.toString().equals(it.userClass)) {
                if (binding.txtNombreProfesor.text.toString().isNotEmpty() && binding.txtNombreClase.text.toString().isNotEmpty()) {
                    if (binding.editTextTextPassword.text.toString().contentEquals("1234")) {
                        res = true
                    }
                }
            } else {
                return false
            }
        }

        return res
    }

    private fun cargarJuegos(user: Usuario) : List<Game> {
        var gameList = listOf(
            Game(getString(R.string.gameSanJuan),1, 0,false,
                RetoGrupoCinco.SITESNAMES.NOCHE_SAN_JUAN_IMG),
            Game(getString(R.string.gameItsaslurIbilbidea), 2,0,false,
                RetoGrupoCinco.SITESNAMES.ITSASLUR_IBILBIDEA_IMG),
            Game(getString(R.string.gamePuenteRomano), 3,0,false,
                RetoGrupoCinco.SITESNAMES.PUENTE_ROMANO_IMG),
            Game(getString(R.string.gameFundicion),4, 0,false,
                RetoGrupoCinco.SITESNAMES.POBENA_FUNDICION_IMG),
            Game(getString(R.string.gameLaArenaHondartza),5, 0,false,
                RetoGrupoCinco.SITESNAMES.PLAYA_LA_ARENA_IMG),
            Game(getString(R.string.gameHermitaDePobeña), 6,0,false,
                RetoGrupoCinco.SITESNAMES.POBENA_HERMITA_IMG),
            Game(getString(R.string.gameCastilloMuñatones),7, 0,false,
                RetoGrupoCinco.SITESNAMES.CASTILLO_MUNATONES_IMG))
        return gameList
    }
}