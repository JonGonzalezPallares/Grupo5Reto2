package com.example.retomuzkiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.prefs
import com.example.retomuzkiz.databinding.ActivityMenuPrincipalBinding
import com.example.retomuzkiz.room.Usuario

class MenuPrincipal : AppCompatActivity() {

    private lateinit var binding : ActivityMenuPrincipalBinding
    private lateinit var adaptadorUsuario: UsuariosAdapter
    val db = RetoGrupoCinco.database!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Para borrar la barra superior
        this.supportActionBar!!.hide()



        binding.button.setOnClickListener {
            comprobarUsuario()
            val intento = Intent(this, MapsActivity::class.java)
            startActivity(intento)
        }

    }

    private fun comprobarUsuario() {
       var nameIntroduced = binding.txtUsuario.text
        var classIntroduced = binding.txtClase.text
        var userList = db.usuarioDao.getAllUsers()
        userList.forEach(){user ->
            if (user.name.equals(nameIntroduced)){
                if(user.userClass.equals(classIntroduced)){

                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
//__________________________________________________________________________________________

        val listaUsuarios = db.usuarioDao.getAllUsers()
       if(!listaUsuarios.isEmpty()) {
           for (i in 0..listaUsuarios.size) {
               println(listaUsuarios[i].toString())
           }
       }
        adaptadorUsuario = UsuariosAdapter(listaUsuarios){
            listar(it.name)
        }
                binding.rvJugadores.adapter = adaptadorUsuario
    }

    //______________________________________________________________________________________________
    // guarda en sharedFreferences nombre pasado en parametro y llama la activity maps
    private fun listar(nombre: String) {
        prefs.saveUser(nombre)
        val intento = Intent(this, MapsActivity::class.java)
        startActivity(intento)
    }

    //______________________________________________________________________________________________
    // recoge nombre del usuario, crea objeto usuario y se guarda en la base de datos
    private fun guardarUsuario() {
        val userName = binding.txtUsuario.text.toString()
        val totPuntuation = 0
        val gamesDone = 0
        val userId = "1"
        val isProfesor = false
        var userClass = "AAAA"

        val user = Usuario(
            userId,
            userName,
            totPuntuation,
           userClass,
            gamesDone,
            isProfesor
        )
      //  database!!.usuarioDao.insertUser(user)
        prefs.saveUser(userName)
    }
}