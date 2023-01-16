package com.example.retomuzkiz

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import androidx.annotation.RequiresApi
import androidx.room.util.StringUtil
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.database
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.progressDb
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.userDb
import com.example.retomuzkiz.databinding.ActivityCrearClaseSocketBinding
import com.example.retomuzkiz.room.Progress
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
                    0
                )
                progressDb.insertProgress(progress)
                RetoGrupoCinco.mSocket.connect()
                RetoGrupoCinco.mSocket.emit("join server", user.name)
                RetoGrupoCinco.mSocket.emit("join room", binding.txtNombreClase.text.toString())
                RetoGrupoCinco.mSocket.on("Salas"){ args ->
                    println(args[0])
                }
                showDialog(this,"","Bienvenido!")
                startActivity(Intent(this, MapsActivity::class.java).putExtra("user", user))
                finish()
            } else {
                showDialog(this,
                    " Algo no fue como debía. Recuerde rellenar todos los campos y proporcionar una contraseña válida",
                    "Error")
            }

        }
        //
    }

    private fun comprobarCampos(): Boolean {
        var res: Boolean = false
       var clases =  RetoGrupoCinco. userDb.getAllUsers()
        clases.forEach(
            {
                if(!binding.txtNombreClase.text.toString().equals(it.userClass)){
                    if (binding.txtNombreProfesor.text.toString().isNotEmpty() && binding.txtNombreClase.text.toString().isNotEmpty() )
                    {
                        if (binding.editTextTextPassword.text.toString().contentEquals("1234")) {
                            res = true
                        }
                    }
                }else{
                    return false
                }

            }
        )
        return res
    }
}