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
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import com.example.retomuzkiz.Burdinola.BurdinolaVideoActivity
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

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnMarkerClickListener, NavigationView.OnNavigationItemSelectedListener {
    object SITES_NAMES {
        var POBENA_FUNDICION = "Fundicion de Pobeña"
        var POBENA_FUNDICION_IMG = "irudia_pobena_1"
        var POBENA_HERMITA = "Hermita de Pobeña"
        var POBENA_HERMITA_IMG = "irudia_pobena_1"
        var ITSASLUR_IBILBIDEA= "Paseo Itsaslur"
        var ITSASLUR_IBILBIDEA_IMG = ""

        var PLAYA_LA_ARENA = "Playa La Arena"
        var PLAYA_LA_ARENA_IMG = "irudia_pobena_1"
        var PUENTE_ROMANO = "Puente Romano de Pobeña"
        var PUENTE_ROMANO_IMG = "irudia_pobena_1"
        var CASTILLO_MUNATONES = "Castillo de Muñatones"
        var CASTILLO_MUNATONES_IMG = "irudia_pobena_1"
        var NOCHE_SAN_JUAN = "Castillo de Muñatones"
        var NOCHE_SAN_JUAN_IMG = "irudia_pobena_1"
    }
    private val keyPathsBehavior by lazy {
        BottomSheetBehavior.from(binding.bottomSheetKeyPaths.root).apply {
            peekHeight =
                resources.getDimensionPixelSize(androidx.appcompat.R.dimen.abc_list_item_height_material)
        }
    }
    private lateinit var binding: ActivityMapsBinding
    private lateinit var listabooleanos: ArrayList<Boolean>
    private lateinit var ubicacion: LatLng
    lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var localizacion: FusedLocationProviderClient
    protected var booleano0 = false
    protected var booleano1 = false
    protected var booleano2 = false
    protected var booleano3 = false
    protected var booleano4 = false
    protected var booleano5 = false
    protected var booleano6 = false
    protected lateinit var Servicio: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        Servicio = Intent(applicationContext, ServicioGeolocalizacion::class.java)
        listabooleanos = arrayListOf<Boolean>()
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        modogiado()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        localizacion = LocationServices.getFusedLocationProviderClient(applicationContext)

        //Accion del boton flotante
        binding.fbPosicion.setOnClickListener {
            val pobenakoErmita = LatLng(43.346497, -3.121751)
            //Ponemos una animacion para que no sea tan brusco el cambio
            /*val camara = CameraPosition.builder()
                .target(pobenakoErmita)
                .zoom(15F)
                .bearing(0F)
                .tilt(0F)
                .build()*/

            //mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camara))
        }

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
                .title(SITES_NAMES.PUENTE_ROMANO)
        )

        mMap.addMarker(
            MarkerOptions()
                .position(pobalekoBurdinola)
                .title(SITES_NAMES.POBENA_FUNDICION)
        )

        mMap.addMarker(
            MarkerOptions()
                .position(pobenakoErmita)
                .title(SITES_NAMES.POBENA_HERMITA)
        )

        mMap.addMarker(
            MarkerOptions()
                .position(hondartzaArena)
                .title(SITES_NAMES.PLAYA_LA_ARENA)
        )

        mMap.addMarker(

            MarkerOptions()
                .position(ibilbideItsaslur)
                .title(SITES_NAMES.ITSASLUR_IBILBIDEA)
        )

        mMap.addMarker(
            MarkerOptions()
                .position(muniatonesGaztelua)
                .title(SITES_NAMES.CASTILLO_MUNATONES)
        )

        mMap.addMarker(

            MarkerOptions()
                .position(sanJuan)
                .title(SITES_NAMES.NOCHE_SAN_JUAN)
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

                //Añadimos un marcador donde hayamos clickado
                //mMap.addMarker(MarkerOptions().position(ubicacion))

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
        if (Listabooleanos[numero!!] == true) {
            //esto pasa si estas cerca de la ubicacion
            binding.bottomSheetKeyPaths.keyPathsRecyclerView.adapter =
                rvDesplegableAdepter(listOf(Actividad(marker.title.toString(), "")), this)
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

            /*Toast.makeText(
        Toast.makeText(
            this,
            "titulo: "+marker.title+" posicion: "+marker.position+" snipet: "+marker.snippet.toString().toInt(),
            Toast.LENGTH_LONG)
            .show()
        println("titulo: "+marker.title+" posicion: "+marker.position+" snipet: "+marker.snippet.toString().toInt())*/
        }
        //println("titulo: " + marker.title + " posicion: " + marker.position)

        println("titulo: "+marker.title+" posicion: "+marker.position)

       //

        var actividades = listOf<List<Actividad>>(
            listOf(Actividad(SITES_NAMES.POBENA_FUNDICION, SITES_NAMES.POBENA_FUNDICION_IMG)),
            listOf(Actividad(SITES_NAMES.POBENA_HERMITA, SITES_NAMES.POBENA_FUNDICION_IMG)),
            listOf(Actividad(SITES_NAMES.PUENTE_ROMANO, SITES_NAMES.POBENA_FUNDICION_IMG)),
            listOf(Actividad(SITES_NAMES.PLAYA_LA_ARENA, SITES_NAMES.POBENA_FUNDICION_IMG)),
            listOf(Actividad(SITES_NAMES.ITSASLUR_IBILBIDEA, SITES_NAMES.POBENA_FUNDICION_IMG)),
            listOf(Actividad(SITES_NAMES.CASTILLO_MUNATONES, SITES_NAMES.POBENA_FUNDICION_IMG)),
            listOf(Actividad(SITES_NAMES.NOCHE_SAN_JUAN, SITES_NAMES.POBENA_FUNDICION_IMG)),
            )
       for (i in 0..6){
        if(marker.title.equals(actividades[i].get(0).name)){
            binding.bottomSheetKeyPaths.keyPathsRecyclerView.adapter = rvDesplegableAdepter(actividades.get(i))

        }
        }






        keyPathsBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        /*
        Devolvemos "false" para indicar que no queremos consumir el evento, indicandole asi que queremos
        que ocurra el evento por defecto. Que la camara se mueva al marcador seleccionado y lo centra
         */
        return false
    }


    // ESTO DEVUELVE EL OBJETO QUE QUERAMOS
    val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
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
            ubicacion = intent.getParcelableExtra<LatLng>("ubicacionactual") as LatLng
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
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        //toggle.syncState()
    }

    //______________________________________________________________________________________________
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //toggle.onConfigurationChanged(newConfig)
    }

    //______________________________________________________________________________________________
    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*if (toggle.onOptionsItemSelected(item)){
            return true
        }*/
        return super.onOptionsItemSelected(item)
    }*/
    //______________________________________________________________________________________________
    // funcion del modo libre
    fun modolibre() {
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
    fun modogiado() {
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
    //funciones del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var guiado = R.id.m_Modoguiado
        when (item.itemId) {
            R.id.m_home -> Toast.makeText(this, " a", Toast.LENGTH_SHORT).show()
            R.id.m_ranking -> Toast.makeText(this, " b", Toast.LENGTH_SHORT).show()
            R.id.m_logout -> {


            }
            R.id.m_Modoguiado -> {
                //Carcar el modo guiado
                modogiado()
                aviso("", "Ahoras estas en el modo guiado")
            }
            R.id.m_Modolibre -> {
                //cargar el modo libre
                modolibre()
                aviso("", "Ahoras estas en el modo libre")

            }
        }
        return super.onOptionsItemSelected(item)
    }

    //______________________________________________________________________________________________
    //funcion del aviso
    fun aviso(titulo: String, mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}