package com.example.retomuzkiz

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.os.Handler
import android.os.Looper
import android.service.notification.NotificationListenerService.Ranking
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.core.graphics.drawable.toDrawable
import com.example.retomuzkiz.Ranking.RankingActivity
import com.example.retomuzkiz.YourProgress.ProgressActivity

import com.example.retomuzkiz.clases.OptionsMenuActivity
import com.example.retomuzkiz.clases.RetoGrupoCinco

import com.example.retomuzkiz.clases.RetoGrupoCinco.Companion.progressDb
import com.example.retomuzkiz.databinding.ActivityMapsBinding
import com.example.retomuzkiz.informacion.MasInformacion
import com.example.retomuzkiz.profesor.ProfesorMode
import com.example.retomuzkiz.room.Usuario
import com.example.retomuzkiz.usuario.currentUser
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.navigation.NavigationView

class MapsActivity : OptionsMenuActivity(), OnMapReadyCallback, OnMarkerClickListener {
    companion object{
       var isJoined = false
        var isMapCreated = false
    }

    private val keyPathsBehavior by lazy {
        BottomSheetBehavior.from(binding.bottomSheetKeyPaths.root).apply {
            peekHeight = resources.getDimensionPixelSize(androidx.appcompat.R.dimen.abc_list_item_height_material)
        }
    }

    private lateinit var binding: ActivityMapsBinding
    private lateinit var listabooleanos: ArrayList<Boolean>
    private lateinit var localizacion: FusedLocationProviderClient
    private var booleano0 = false
    private var booleano1 = false
    private var booleano2 = false
    private var booleano3 = false
    private var booleano4 = false
    private var booleano5 = false
    private var booleano6 = false
    private var navegacion = false
    private var iniciarguiado = false
    private lateinit var Servicio: Intent
    val db = RetoGrupoCinco.database
    lateinit var user:Usuario
    override fun onDestroy() {
        super.onDestroy()
        RetoGrupoCinco.mSocket.disconnect()
        stopService(Servicio)
        MapsActivity.isMapCreated = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
       // user = intent.getParcelableExtra("user")!!

        this.supportActionBar!!
            .hide()

        /* Inicializacion variablees */
        isMapCreated = true
        /*Inicio Servicio Geolacilazacion*/
        Servicio = Intent(applicationContext, ServicioGeolocalizacion::class.java)
        listabooleanos = arrayListOf()
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Cambiado Para Pruebas
        //guideMode()
        freeMode()
        val navView : NavigationView = binding.lateralmenu
        //menu lateral
        val header = navView.getHeaderView(0)
        var totPuntuacion =  header.findViewById<TextView>(R.id.menuTxtPuntuacion)
        println(progressDb.getUserProgress(currentUser!!.userId).totalPuntuation)
        totPuntuacion.text = "Puntuacion: ${progressDb.getUserProgress(currentUser!!.userId).totalPuntuation.toString()}"

        var nombreUser =  header.findViewById<TextView>(R.id.menuTxtUser)
        nombreUser.text = currentUser!!.name
        RetoGrupoCinco.mSocket.on("gameCompleted"){ args->
            runOnUiThread(){
                totPuntuacion.text = "Puntuacion: ${progressDb.getUserProgress(currentUser!!.userId).totalPuntuation.toString()}"
            }
        }

        binding.vista.setOnClickListener{
            binding.vista.visibility = View.GONE
            binding.informacion.visibility = View.GONE
            binding.fbPosicion.visibility = View.VISIBLE
            navegacion=false
            menuanimation()
        }

        binding.informacion.setOnClickListener{
            val intento = Intent(this, MasInformacion::class.java)
            startActivity(intento)
        }

        binding.Navegation.setOnClickListener{

            keyPathsBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            if(navegacion){
                binding.vista.visibility = View.GONE
                binding.informacion.visibility = View.GONE
                binding.fbPosicion.visibility = View.VISIBLE
                navegacion = false
            }else{
                binding.vista.visibility = View.VISIBLE
                if(!currentUser.isProfessor){
                    val profesor = findViewById<View>(R.id.m_Profesor)
                    profesor.isClickable = false
                    profesor.alpha = 0.2F
                }
                navegacion = true
                if(!iniciarguiado){
                    iniciarguiado = true
                }
                binding.fbPosicion.visibility = View.GONE
                binding.informacion.visibility = View.VISIBLE
            }
            menuanimation()
        }

        //______________________________________________________________________________________________
        //funciones del menu
        navView.setNavigationItemSelectedListener { menu ->

            when(menu.itemId) {
                //Para activar el modo libre
                R.id.m_Modolibre -> {
                    val guide = findViewById<View>(R.id.m_Modoguiado)
                    val free = findViewById<View>(R.id.m_Modolibre)
                    //cargar el modo libre
                    freeMode()
                    guide.isEnabled = true
                    free.isEnabled = false
                    warning("", this.applicationContext.resources.getString(R.string.txtLibre))
                    true
                }

                //Para activar al modo guiado
                R.id.m_Modoguiado -> {
                    //Carcar el modo guiado
                    val guide = findViewById<View>(R.id.m_Modoguiado)
                    val free = findViewById<View>(R.id.m_Modolibre)
                    guideMode()
                    guide.isEnabled = false
                    free.isEnabled = true
                    warning("", this.applicationContext.resources.getString(R.string.txtGuiado))
                    true
                }

                //Para entrar como profesor
                R.id.m_Profesor -> {
                    val intento = Intent(this, ProfesorMode::class.java)
                    startActivity(intento)
                    true
                }

                //Para ir al ranking
                R.id.m_ranking -> {
                    //Toast.makeText(this, " b", Toast.LENGTH_SHORT).show()
                    val intento = Intent(this, RankingActivity::class.java)
                    startActivity(intento)
                    true
                }

                //Para ir al ranking
                R.id.m_Puntuacion -> {
                    //Toast.makeText(this, " b", Toast.LENGTH_SHORT).show()
                    val intento = Intent(this, ProgressActivity::class.java)
                    startActivity(intento)
                    true
                }


                //Para ir a sobre nosotros
                R.id.m_About -> {
                    val intento = Intent(this, Nosotros::class.java)
                    startActivity(intento)
                    true
                }

                //Para hacer logout
                R.id.m_logout -> {
                    if (keyPathsBehavior.state != BottomSheetBehavior.STATE_HIDDEN){
                        keyPathsBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                    }else{
                        finish()
                    }
                    true
                }

                //Cosa que sino explota
                else -> {false}
            }
        }

        val a = R.drawable.fondo_degradado
        this.supportActionBar!!.setBackgroundDrawable(a.toDrawable())

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        localizacion = LocationServices.getFusedLocationProviderClient(applicationContext)

        //Estado por defecto del desplegable inferior
        keyPathsBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(mMap: GoogleMap) {

        //Creacion de los markadores
        val puenteRomano = LatLng(43.316772, -3.119471)
        val pobalekoBurdinola = LatLng(43.296111, -3.126113)
        val pobenakoErmita = LatLng(43.346497, -3.121751)
        val hondartzaArena = LatLng(43.349722, -3.116389)
        val ibilbideItsaslur = LatLng(43.331075, -3.117392)
        val muniatonesGaztelua = LatLng(43.323611, -3.112503)
        val sanJuan = LatLng(43.330278, -3.129061)

        val posiciones = listOf(puenteRomano, pobalekoBurdinola, pobenakoErmita, hondartzaArena, ibilbideItsaslur, muniatonesGaztelua, sanJuan)
        val titulos = listOf(
            getString(R.string.gamePuenteRomano),
            getString(R.string.gameFundicion),
            getString(R.string.gameHermitaDePobeña),
            getString(R.string.gameLaArenaHondartza),
            getString(R.string.gameItsaslurIbilbidea),
            getString(R.string.gameCastilloMuñatones),
            getString(R.string.gameSanJuan)
        )
        var snippet = 0
        mMap.uiSettings.isZoomControlsEnabled = true

        posiciones.forEach {
            mMap.addMarker(
                MarkerOptions()
                    .position(it)
                    .title(titulos[snippet])
                    .snippet(snippet.toString())
            )
            snippet++
        }

        mMap.setOnMarkerClickListener(this)

        //Tenemos que pedir permisos
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1
            )
            return
        }

        //Guardamos la posicion en la que estamos actualmente en el mapa
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        localizacion.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                //Cogemos la posicion de donde hayamos clicado
                val ubicacion = LatLng(location.latitude, location.longitude)

                //Ponemos una animacion para que no sea tan brusco el cambio
                val camara = CameraPosition.builder()
                    .target(ubicacion)
                    .zoom(13F)
                    .bearing(0F)
                    .tilt(0F)
                    .build()

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camara))
            }
        }

        //Accion del boton flotante
        binding.fbPosicion.setOnClickListener {
            //Guardamos la posicion en la que estamos actualmente en el mapa
            localizacion.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    //Cogemos la posicion de donde hayamos clicado
                    val ubicacion = LatLng(location.latitude, location.longitude)

                    //Ponemos una animacion para que no sea tan brusco el cambio
                    val camara = CameraPosition.builder()
                        .target(ubicacion)
                        .zoom(13F)
                        .bearing(0F)
                        .tilt(0F)
                        .build()

                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camara))
                }
            }
        }

        /*binding.map.setOnClickListener{
            if (navegacion){
                navegacion=false
                menuanimation()
            }
        }*/
    }

    //Acciones que ocurren cada vez que pulsamos a un marcador
    override fun onMarkerClick(marker: Marker): Boolean {
        val numero = marker.snippet.toString().toInt()

        val user: Usuario = currentUser!!

        val actividades = db.gameDao.getAllGames()

        if (listabooleanos[numero]) {
            actividades.forEach { game ->
                if(marker.title.equals(game.gameName)){
                    binding.bottomSheetKeyPaths.keyPathsRecyclerView.adapter = RvDesplegableAdapter(
                        game, user,this
                    )
                }
            }

            keyPathsBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            //esto pasa si estas legos de la ubicacion
            Toast.makeText(
                this,
                this.applicationContext.resources.getString(R.string.txtLejos),
                Toast.LENGTH_LONG
            )
                .show()
        }



        /*
        Devolvemos "false" para indicar que no queremos consumir el evento, indicandole asi que queremos
        que ocurra el evento por defecto. Que la camara se mueva al marcador seleccionado y lo centra
         */
        return false
    }

    // ESTO DEVUELVE EL OBJETO QUE QUERAMOS
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // AQUI TIENE QUE IR EL ARRAY DE BOOLEANO DE COMPROBACION DE LOS PUNTOS DE INTERES
            listabooleanos[0] = intent.getBooleanExtra("Booleano0", false)
            listabooleanos[1] = intent.getBooleanExtra("Booleano1", false)
            listabooleanos[2] = intent.getBooleanExtra("Booleano2", false)
            listabooleanos[3] = intent.getBooleanExtra("Booleano3", false)
            listabooleanos[4] = intent.getBooleanExtra("Booleano4", false)
            listabooleanos[5] = intent.getBooleanExtra("Booleano5", false)
            listabooleanos[6] = intent.getBooleanExtra("Booleano6", false)
        }
    }

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter("broadcast")
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        stopService(Servicio)
        keyPathsBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        IntentFilter("broadcast")
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }
    override fun onBackPressed(){
        if (keyPathsBehavior.state != BottomSheetBehavior.STATE_HIDDEN){
            keyPathsBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }else{
            finish()
        }
    }
    //______________________________________________________________________________________________
    // funcion del modo libre
    private fun freeMode() {
        stopService(Servicio)
        // fundcion de espra de 3s antes de cambiar las variables
        Handler(Looper.myLooper() ?: return).postDelayed({
           if (listabooleanos.isEmpty()){
               listabooleanos.add(true)
               listabooleanos.add(true)
               listabooleanos.add(true)
               listabooleanos.add(true)
               listabooleanos.add(true)
               listabooleanos.add(true)
               listabooleanos.add(true)
           }else{
               listabooleanos[0] = true
               listabooleanos[1] = true
               listabooleanos[2] = true
               listabooleanos[3] = true
               listabooleanos[4] = true
               listabooleanos[5] = true
               listabooleanos[6] = true
           }
        iniciarguiado = false
        }, 3000)
    }

    //______________________________________________________________________________________________
    //funcion del modo guiado
    private fun guideMode() {

        // crear el servicio de geolocalizacion
        listabooleanos.add(false)
        listabooleanos.add(false)
        listabooleanos.add(false)
        listabooleanos.add(false)
        listabooleanos.add(false)
        listabooleanos.add(false)
        listabooleanos.add(false)

        Servicio.putExtra("boleano0", booleano0)
        Servicio.putExtra("boleano1", booleano1)
        Servicio.putExtra("boleano2", booleano2)
        Servicio.putExtra("boleano3", booleano3)
        Servicio.putExtra("boleano4", booleano4)
        Servicio.putExtra("boleano5", booleano5)
        Servicio.putExtra("boleano6", booleano6)
        startService(Servicio)
    }

    //______________________________________________________________________________________________
    //funcion del aviso
    private fun warning(titulo: String, mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton(this.applicationContext.resources.getString(R.string.Aceptar), null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //______________________________________________________________________________________________
    //funcion de animacion del menu
    private fun menuanimation(){
        val mSlideLeft = Slide()
        val layout = findViewById<CoordinatorLayout>(R.id.drawer_layut)
        mSlideLeft.slideEdge = Gravity.START
        TransitionManager.beginDelayedTransition(layout,mSlideLeft)
        binding.lateralmenu.isVisible = navegacion
    }
}