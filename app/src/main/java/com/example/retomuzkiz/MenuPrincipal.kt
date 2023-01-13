package com.example.retomuzkiz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.prefs
import com.example.retomuzkiz.databinding.ActivityMenuPrincipalBinding
import com.example.retomuzkiz.room.Progress
import com.example.retomuzkiz.gastelua.ActivityGaztelua
import com.example.retomuzkiz.room.Usuario
import kotlin.random.Random
import kotlin.random.nextInt

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


        binding.txtProfesor.setOnClickListener(){
            startActivity(Intent(this,ActivityCrearClaseSocket::class.java))

        }
        binding.button.setOnClickListener {
            var user = comprobarUsuario()
            if(user  != null){
                val intento = Intent(this, MapsActivity::class.java).putExtra("user", user)
                startActivity(intento)
            }
            else{
                user = insertarUser()
                val intento = Intent(this, MapsActivity::class.java).putExtra("user", user)
                startActivity(intento)
            }
            RetoGrupoCinco.mSocket.connect()
            RetoGrupoCinco.mSocket.emit("join server",user?.name)
            RetoGrupoCinco.mSocket.emit("join room",user?.userClass)







        }

    }

    private fun insertarUser(): Usuario {
        val userName = binding.txtUsuario.text.toString()
        var userClass = binding.txtClase.text.toString()
        val totPuntuation = 0
        val gamesDone = 0
        val userId = "${Random.nextInt(0..1)*100}${userClass}${db.usuarioDao.getAllUsers().size}"
        val isProfesor = false


        val user = Usuario(
            userId,
            userName,
            userClass,
            isProfesor
        )
        db.usuarioDao.insertUser(user)
        val progress = Progress(
            user.userId,
            0,
            0,
            0
        )
        db.progressDao.insertProgress(progress)
        // prefs.saveUser(userName)
        return user
    }


    private fun comprobarUsuario():Usuario?{
        var nameIntroduced = binding.txtUsuario.text
        var classIntroduced = binding.txtClase.text
        var usuario : Usuario?
        var userList = db.usuarioDao.getAllUsers()
        userList.forEach(){user ->
            if (user.name.contentEquals(nameIntroduced) ){
                if(user.userClass.contentEquals(classIntroduced)){
                    usuario = user
                    return user
                }
            }

        }

        return null
    }


    override fun onResume() {
        super.onResume()
//__________________________________________________________________________________________

        val listaUsuarios = db.usuarioDao.getAllUsers()
        if(!listaUsuarios.isEmpty()) {
            for (i in 0 until listaUsuarios.size) {
                println(listaUsuarios[i].toString())
            }
        }
        adaptadorUsuario = UsuariosAdapter(listaUsuarios){
            listar(it)
        }
        binding.rvJugadores.adapter = adaptadorUsuario
    }

    //______________________________________________________________________________________________
    // guarda en sharedFreferences nombre pasado en parametro y llama la activity maps
    private fun listar(user: Usuario) {
        //prefs.saveUser(nombre)
        val intento = Intent(this, MapsActivity::class.java).putExtra("user", user)
        startActivity(intento)
        RetoGrupoCinco.mSocket.connect()
        RetoGrupoCinco.mSocket.emit("join server",user?.name)

        RetoGrupoCinco.mSocket.emit("join room",user?.userClass)

    }


}