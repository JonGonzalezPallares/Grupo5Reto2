package com.example.retomuzkiz

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.example.retomuzkiz.clases.OptionsMenuActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.retomuzkiz.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Marker
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView

class MapsActivity : OptionsMenuActivity(), OnMapReadyCallback, OnMarkerClickListener, NavigationView.OnNavigationItemSelectedListener {
    private val keyPathsBehavior by lazy {
        BottomSheetBehavior.from(binding.bottomSheetKeyPaths.root).apply {
            peekHeight = resources.getDimensionPixelSize(androidx.appcompat.R.dimen.abc_list_item_height_material)
        }
    }
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    protected lateinit var Listabooleanos : ArrayList<Boolean>
    private lateinit var  ubicacion:LatLng
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
    protected lateinit var Servicio : Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        Servicio = Intent(applicationContext, ServicioGeolocalizacion::class.java)
        Listabooleanos = arrayListOf<Boolean>()
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
            val camara = CameraPosition.builder()
                .target(pobenakoErmita)
                .zoom(15F)
                .bearing(0F)
                .tilt(0F)
                .build()

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camara))
        }
        keyPathsBehavior.state = BottomSheetBehavior.STATE_HIDDEN


        binding.btnDesplegableTest.setOnClickListener(){
        }
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
                .title("Pobaleko zubi erromanikoa")
                .snippet("0")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(pobalekoBurdinola)
                .title("Pobaleko Burdinola")
                .snippet("1")

        )

        mMap.addMarker(
            MarkerOptions()
                .position(pobenakoErmita)
                .title("Pobeñako Ermita")
                .snippet("2")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(hondartzaArena)
                .title("La Arena hondartza")
                .snippet("3")
        )

        mMap.addMarker(

            MarkerOptions()
                .position(ibilbideItsaslur)
                .title("Itsaslur Ibilbidea")
                .snippet("4")


        )

        mMap.addMarker(
            MarkerOptions()
                .position(muniatonesGaztelua)
                .title("Muñatones Gaztelua")
                .snippet("5")
        )

        mMap.addMarker(

            MarkerOptions()
                .position(sanJuan)
                .title("San Juan Gaua")
                .snippet("6")
        )

        mMap.setOnMarkerClickListener(this)

        //Tenemos que pedir permisos
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 1)
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
            if(location != null){
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
                if(location != null){
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
        var numero = marker.snippet.toString().toInt()
        println(numero)
        if(Listabooleanos[numero!!] == true){
            Toast.makeText(
                this,
                "titulo: "+marker.title+" posicion: "+marker.position,
                Toast.LENGTH_LONG)
                .show()
            println("titulo: "+marker.title+" posicion: "+marker.position)
        }
        else{
            Toast.makeText(
                this,
                "titulo: "+" estas muy lejos del punto"+" posicion: "+marker.title,
                Toast.LENGTH_LONG)
                .show()
            println("titulo: "+" estas muy lejos del punto"+" posicion: "+marker.title)

        /*Toast.makeText(
        Toast.makeText(
            this,
            "titulo: "+marker.title+" posicion: "+marker.position+" snipet: "+marker.snippet.toString().toInt(),
            Toast.LENGTH_LONG)
            .show()
        println("titulo: "+marker.title+" posicion: "+marker.position+" snipet: "+marker.snippet.toString().toInt())*/
        }
        println("titulo: "+marker.title+" posicion: "+marker.position)
        binding.bottomSheetKeyPaths.keyPathsRecyclerView.adapter = rvDesplegableAdepter(listOf(Actividad(marker.title.toString(),"")))
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
                    Listabooleanos[0]= intent.getBooleanExtra("Booleano0",false)
                    Listabooleanos[1]= intent.getBooleanExtra("Booleano1",false)
                    Listabooleanos[2]= intent.getBooleanExtra("Booleano2",false)
                    Listabooleanos[3]= intent.getBooleanExtra("Booleano3",false)
                    Listabooleanos[4]= intent.getBooleanExtra("Booleano4",false)
                    Listabooleanos[5]= intent.getBooleanExtra("Booleano5",false)
                    Listabooleanos[6]= intent.getBooleanExtra("Booleano6",false)
                    println("aa"+Listabooleanos)
                    ubicacion = intent.getParcelableExtra<LatLng>("ubicacionactual") as LatLng
        }
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter("broadcast")
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,intentFilter)
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*if (toggle.onOptionsItemSelected(item)){
            return true
        }*/
        return super.onOptionsItemSelected(item)
    }
    //______________________________________________________________________________________________
    // funcion del modo libre
    fun modolibre(){
        stopService(Servicio)
        Listabooleanos[0]= true
        Listabooleanos[1]= true
        Listabooleanos[2]= true
        Listabooleanos[3]= true
        Listabooleanos[4]= true
        Listabooleanos[5]= true
        Listabooleanos[6]= true

    }
    //______________________________________________________________________________________________
    //funcion del modo guiado
    fun modogiado(){
        // crear el servicio de geolocalizacion


        Listabooleanos.add(false)
        Listabooleanos.add(false)
        Listabooleanos.add(false)
        Listabooleanos.add(false)
        Listabooleanos.add(false)
        Listabooleanos.add(false)
        Listabooleanos.add(false)
        println(Listabooleanos)

        Servicio.putExtra("boleano0",booleano0)
        Servicio.putExtra("boleano1",booleano1)
        Servicio.putExtra("boleano2",booleano2)
        Servicio.putExtra("boleano3",booleano3)
        Servicio.putExtra("boleano4",booleano4)
        Servicio.putExtra("boleano5",booleano5)
        Servicio.putExtra("boleano6",booleano6)


        startService(Servicio)



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}