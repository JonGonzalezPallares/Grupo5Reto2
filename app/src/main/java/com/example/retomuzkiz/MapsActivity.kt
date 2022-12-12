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
import android.content.res.Configuration
import android.os.Handler
import android.os.Looper
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import com.example.retomuzkiz.clases.Actividad
import androidx.core.graphics.drawable.toDrawable
import com.example.retomuzkiz.MapsActivity.SITESNAMES.CASTILLO_MUNATONES
import com.example.retomuzkiz.MapsActivity.SITESNAMES.ITSASLUR_IBILBIDEA
import com.example.retomuzkiz.MapsActivity.SITESNAMES.NOCHE_SAN_JUAN
import com.example.retomuzkiz.MapsActivity.SITESNAMES.PLAYA_LA_ARENA
import com.example.retomuzkiz.MapsActivity.SITESNAMES.POBENA_FUNDICION
import com.example.retomuzkiz.MapsActivity.SITESNAMES.POBENA_HERMITA
import com.example.retomuzkiz.MapsActivity.SITESNAMES.PUENTE_ROMANO
import com.example.retomuzkiz.clases.OptionsMenuActivity
import com.example.retomuzkiz.databinding.ActivityMapsBinding
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
    object SITESNAMES {
        lateinit var POBENA_FUNDICION:String
        var POBENA_FUNDICION_IMG_1 = "fundicion_pobela"
        lateinit var POBENA_HERMITA :String
        var POBENA_HERMITA_IMG = "irudiapobena1"
        lateinit var ITSASLUR_IBILBIDEA : String
        var ITSASLUR_IBILBIDEA_IMG_1 = "itsaslur1_2"
        var ITSASLUR_IBILBIDEA_IMG_2 = "itsaslur2_1"
        var ITSASLUR_IBILBIDEA_IMG_3 = "itsaslur2_2"
        lateinit var PLAYA_LA_ARENA :String
        var PLAYA_LA_ARENA_IMG = "irudia_arena_2"
        lateinit var PUENTE_ROMANO :String
        var PUENTE_ROMANO_IMG = "puentecompleto"
        lateinit var CASTILLO_MUNATONES :String
        var CASTILLO_MUNATONES_IMG = "irudia_pobena_1"
        lateinit var NOCHE_SAN_JUAN :String
        var NOCHE_SAN_JUAN_IMG = "irudia_san_juan"
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
    lateinit var toggle : ActionBarDrawerToggle



    override fun onCreate(savedInstanceState: Bundle?) {


        this.supportActionBar!!.hide()

        /* Inicializacion variablees */
        POBENA_FUNDICION = resources.getString(R.string.gameFundicion)
        POBENA_HERMITA = resources.getString(R.string.gameHermitaDePobeña)
        ITSASLUR_IBILBIDEA = resources.getString(R.string.gameItsaslurIbilbidea)
        NOCHE_SAN_JUAN = resources.getString(R.string.gameSanJuan)
        PUENTE_ROMANO = resources.getString(R.string.gamePuenteRomano)
        CASTILLO_MUNATONES = resources.getString(R.string.gameCastilloMuñatones)
        PLAYA_LA_ARENA = resources.getString(R.string.gameLaArenaHondartza)
        /*Inicio Servicio Geolacilazacion*/
        Servicio = Intent(applicationContext, ServicioGeolocalizacion::class.java)
        listabooleanos = arrayListOf()
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        guideMode()
        val navview : NavigationView = findViewById(R.id.lateralmenu)
        //menu lateral
        binding.Navegation.setOnClickListener{
            keyPathsBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            if(!navegacion){
                navegacion = true
                if(!iniciarguiado){
                    val guide = findViewById<View>(R.id.m_Modoguiado)
                    val free = findViewById<View>(R.id.m_Modolibre)
                    guide.isEnabled = false

                    free.isEnabled = true
                    iniciarguiado = true
                }
            }
            else{
                navegacion = false
            }
            menuanimation()

        }

        //______________________________________________________________________________________________
        //funciones del menu
        navview.setNavigationItemSelectedListener { menu ->
            when(menu.itemId) {

                R.id.m_ranking -> {
                    Toast.makeText(this, " b", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.m_logout -> {
                    true
                }
                R.id.m_Modoguiado -> {
                    //Carcar el modo guiado
                    val guide = findViewById<View>(R.id.m_Modoguiado)
                    val free = findViewById<View>(R.id.m_Modolibre)
                    guideMode()
                    guide.isEnabled = false
                    free.isEnabled = true
                    warning("", "Ahoras estas en el modo guiado")
                    true
                }
                R.id.m_Modolibre -> {
                    val guide = findViewById<View>(R.id.m_Modoguiado)
                    val free = findViewById<View>(R.id.m_Modolibre)
                    //cargar el modo libre
                    freeMode()
                    guide.isEnabled = true
                    free.isEnabled = false
                    warning("", "Ahoras estas en el modo libre")
                    true
                }
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

        //Añadimos los marcadores al mapa
        mMap.addMarker(
            MarkerOptions()
                .position(puenteRomano)
                .title(PUENTE_ROMANO)
                .snippet("0")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(pobalekoBurdinola)
                .title(POBENA_FUNDICION)
                .snippet("1")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(pobenakoErmita)
                .title(POBENA_HERMITA)
                .snippet("2")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(hondartzaArena)
                .title(PLAYA_LA_ARENA)
                .snippet("3")
        )

        mMap.addMarker(

            MarkerOptions()
                .position(ibilbideItsaslur)
                .title(ITSASLUR_IBILBIDEA)
                .snippet("4")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(muniatonesGaztelua)
                .title(CASTILLO_MUNATONES)
                .snippet("5")
        )

        mMap.addMarker(

            MarkerOptions()
                .position(sanJuan)
                .title(NOCHE_SAN_JUAN)
                .snippet("6")
        )

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
                    .zoom(15F)
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
                        .zoom(15F)
                        .bearing(0F)
                        .tilt(0F)
                        .build()

                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camara))
                }
            }
        }
    }

    //Acciones que ocurren cada vez que pulsamos a un marcador
    override fun onMarkerClick(marker: Marker): Boolean {
        val numero = marker.snippet.toString().toInt()
        println(numero)
        if (listabooleanos[numero]) {
            //esto pasa si estas cerca de la ubicacion
//            binding.bottomSheetKeyPaths.keyPathsRecyclerView.adapter =
//                RvDesplegableAdapter(, this)
            keyPathsBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

            Toast.makeText(
                this,
                "titulo: " + marker.title + " posicion: " + marker.position,
                Toast.LENGTH_LONG
            )
                .show()
            println("titulo: " + marker.title + " posicion: " + marker.position)
        } else {
            //esto pasa si estas legos de la ubicacion
            Toast.makeText(
                this,
                "titulo: " + " estas muy lejos del punto" + " posicion: " + marker.title,
                Toast.LENGTH_LONG
            )
                .show()
            println("titulo: " + " estas muy lejos del punto" + " posicion: " + marker.title)
        }

        println("titulo: "+marker.title+" posicion: "+marker.position)

        val actividades = listOf(
            listOf(Actividad(POBENA_FUNDICION, SITESNAMES.POBENA_FUNDICION_IMG_1 )),
            listOf(Actividad(POBENA_HERMITA, SITESNAMES.POBENA_HERMITA_IMG)),
            listOf(Actividad(PUENTE_ROMANO, SITESNAMES.PUENTE_ROMANO_IMG)),
            listOf(Actividad(PLAYA_LA_ARENA, SITESNAMES.PLAYA_LA_ARENA_IMG)),
            listOf(Actividad(ITSASLUR_IBILBIDEA, SITESNAMES.ITSASLUR_IBILBIDEA_IMG_1)),
            listOf(Actividad(CASTILLO_MUNATONES, SITESNAMES.CASTILLO_MUNATONES_IMG)),
            listOf(Actividad(NOCHE_SAN_JUAN, SITESNAMES.NOCHE_SAN_JUAN_IMG)))

        for (i in 0..6){
            if(marker.title.equals(actividades[i][0].name)){
                binding.bottomSheetKeyPaths.keyPathsRecyclerView.adapter = RvDesplegableAdapter(
                    actividades[i], this
                )
            }
        }

        keyPathsBehavior.state = BottomSheetBehavior.STATE_EXPANDED

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
            println("aa $listabooleanos")
        }
    }

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter("broadcast")
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onPause() {

        super.onPause()
        val intentFilter = IntentFilter("broadcast")
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }
    //______________________________________________________________________________________________
    // funcion del modo libre
    private fun freeMode() {
        stopService(Servicio)
        // fundcion de espra de 3s antes de cambiar las variables
        Handler(Looper.myLooper() ?: return).postDelayed({
            listabooleanos[0] = true
            listabooleanos[1] = true
            listabooleanos[2] = true
            listabooleanos[3] = true
            listabooleanos[4] = true
            listabooleanos[5] = true
            listabooleanos[6] = true
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
        println(listabooleanos)

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
    //______________________________________________________________________________________________

    //funciones del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.m_ranking -> Toast.makeText(this, " b", Toast.LENGTH_SHORT).show()

            R.id.m_logout -> {
            }

            R.id.m_Modoguiado -> {
                //Carcar el modo guiado
                guideMode()
                warning("", "Ahoras estas en el modo guiado")
            }

            R.id.m_Modolibre -> {
                //cargar el modo libre
                freeMode()
                warning("", "Ahoras estas en el modo libre")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //______________________________________________________________________________________________
    //funcion del aviso
    private fun warning(titulo: String, mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
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