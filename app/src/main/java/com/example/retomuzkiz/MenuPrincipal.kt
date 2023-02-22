package com.example.retomuzkiz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retomuzkiz.clases.RetoGrupoCinco
import com.example.retomuzkiz.databinding.ActivityMenuPrincipalBinding
import com.example.retomuzkiz.room.Progress
import com.example.retomuzkiz.room.Game
import com.example.retomuzkiz.room.TypeConverter
import com.example.retomuzkiz.room.Usuario
import com.example.retomuzkiz.usuario.currentUser
import kotlin.random.Random
import kotlin.random.nextInt

class MenuPrincipal : AppCompatActivity() {
    lateinit var user:Usuario
    private lateinit var binding : ActivityMenuPrincipalBinding
    private lateinit var adaptadorUsuario: UsuariosAdapter
    val db = RetoGrupoCinco.database!!
    var dialogos = 0
    var dialogosClaseNoEncontrada= 0;
    var evento = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Para borrar la barra superior
        this.supportActionBar!!.hide()
        RetoGrupoCinco.mSocket.on("not existing room"){ args ->
            println(args[0])
            runOnUiThread(){
                if(dialogosClaseNoEncontrada<1){
                    showDialog(this, "No se ha encontrado ninguna clase", "Error")
                    dialogosClaseNoEncontrada++
                }else{
                    val intento = Intent(this, MapsActivity::class.java)
                    startActivity(intento)
                }
                dialogosClaseNoEncontrada = 0
            }
        }
        RetoGrupoCinco.mSocket.on("joined") { args ->
            if(dialogos<1) {
                if (!MapsActivity.isMapCreated) {

                        //setUser(user!!)
                    evento = true
                        val intento = Intent(this, MapsActivity::class.java)
                        startActivity(intento)
                    //finish()
                    dialogos++
                }

            }
        }
        dialogos = 0
        dialogosClaseNoEncontrada= 0
        binding.txtProfesor.setOnClickListener {
            startActivity(Intent(this,ActivityCrearClaseSocket::class.java))
        }

        binding.button.setOnClickListener {
            RetoGrupoCinco.mSocket.connect()
            dialogos = 0
            dialogosClaseNoEncontrada= 0
            var newUser = comprobarUsuario()
            val userName = binding.txtUsuario.text.toString()
            var userClass = binding.txtClase.text.toString()
            val totPuntuation = 0
            val gamesDone = 0
            val userId = "${Random.nextInt(0..1)*100}${userClass}${db.usuarioDao.getAllUsers().size}"
            val isProfesor = false
            if(newUser == null){
                user = Usuario(
                    userId,
                    userName,
                    userClass,
                    isProfesor
                )
                setUser( user )


            }else{

                //Si ComprobarUsuario ha encontrado algun usuario, seteamos
                user = newUser
                setUser(user)
            }
            insertarUser(currentUser)
            var room :String? = null

            RetoGrupoCinco.mSocket.emit("join server",binding.txtUsuario.text.toString())
            RetoGrupoCinco.mSocket.emit("join room",binding.txtClase.text.toString())

            }
            RetoGrupoCinco.mSocket.on("Salas") { args ->
                println(args[0])



        }

    }

    private fun insertarUser(user: Usuario): Usuario {
        val userName = binding.txtUsuario.text.toString()
        var userClass = binding.txtClase.text.toString()
        val totPuntuation = 0
        val gamesDone = 0
        val userId = "${Random.nextInt(0..1)*100}${userClass}${db.usuarioDao.getAllUsers().size}"
        val isProfesor = false



        db.usuarioDao.insertUser(user)
        val progress = Progress(
            user.userId,
            0,
            0,
            TypeConverter.someObjectListToString(cargarJuegos(user))
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
            Game(getString(R.string.gameCastilloMuñatones), 7, 0,false,
                RetoGrupoCinco.SITESNAMES.CASTILLO_MUNATONES_IMG))
        return gameList
    }

    override fun onBackPressed() {
       finish()
    }


    override fun onResume() {
        super.onResume()
//__________________________________________________________________________________________
        dialogos = 0
        val listaUsuarios = db.usuarioDao.getAllUsers()
        if(listaUsuarios.isNotEmpty()) {
            for (element in listaUsuarios) {
                println(element.toString())
            }
        }
        adaptadorUsuario = UsuariosAdapter(listaUsuarios){
            listar(it)
        }
        binding.rvJugadores.adapter = adaptadorUsuario
    }

    //______________________________________________________________________________________________

    private fun listar(user: Usuario) {

        setUser(user)
        RetoGrupoCinco.mSocket.connect()
        RetoGrupoCinco.mSocket.emit("join server",user.name)

        if(user.isProfessor){
            RetoGrupoCinco.mSocket.emit("create room",user.userClass)

        }



        RetoGrupoCinco.mSocket.emit("join room",user.userClass)
        if (!evento){
            val intento =
                Intent(this, MapsActivity::class.java)
            startActivity(intento)
            //finish()
        }
        RetoGrupoCinco.mSocket.on("Salas"){ args ->
            println(args[0])
       }
    }
}