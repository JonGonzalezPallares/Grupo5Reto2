package com.example.retomuzkiz

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.retomuzkiz.databinding.ActivityMapsBinding
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Marker
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnMarkerClickListener, NavigationView.OnNavigationItemSelectedListener {
    private val keyPathsBehavior by lazy {
        BottomSheetBehavior.from(binding.bottomSheetKeyPaths.root).apply {
            peekHeight = resources.getDimensionPixelSize(androidx.appcompat.R.dimen.abc_list_item_height_material)
        }
    }
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    //protected lateinit var Listabooleanos : ArrayList<Boolean>
    //protected lateinit var Listacoodenadas : ArrayList<LatLng>
    lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // crear el servicio de geolocalizacion
        val Servicio = Intent(applicationContext, ServicioGeolocalizacion::class.java)
        cargarbooleanos()
        cargarcordenadas()
        //Servicio.putExtra("boleanos",Listabooleanos)
        //Servicio.putExtra("coordenadas",Listacoodenadas)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //__________________________________________________________________________________________
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        drawer = binding.drawerLayut
        toggle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close)
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        val navigationView : NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        //__________________________________________________________________________________________

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

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
        )

        mMap.addMarker(
            MarkerOptions()
                .position(pobalekoBurdinola)
                .title("Pobaleko Burdinola")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(pobenakoErmita)
                .title("Pobeñako Ermita")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(hondartzaArena)
                .title("La Arena hondartza")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(ibilbideItsaslur)
                .title("Itsaslur Ibilbidea")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(muniatonesGaztelua)
                .title("Muñatones Gaztelua")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(sanJuan)
                .title("San Juan Gaua")
        )

        //Ponemos una animacion para que no sea tan brusco el cambio
        val camara = CameraPosition.builder()
            .target(pobenakoErmita)
            .zoom(15F)
            .bearing(0F)
            .tilt(0F)
            .build()

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camara))

        mMap.setOnMarkerClickListener(this)
    }

    //Acciones que ocurren cada vez que pulsamos a un marcador
    override fun onMarkerClick(marker: Marker): Boolean {

        Toast.makeText(
            this,
            "titulo: "+marker.title+" posicion: "+marker.position,
            Toast.LENGTH_LONG)
            .show()

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
            //txtcontar.text = intent?.getStringExtra("contador")
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

    fun cargarcordenadas(){

        val PobeñakoErmita = LatLng(43.346497, -3.121751)
        //Listacoodenadas[0]= PobeñakoErmita
        //cargar las cordenadas que se usaran para las comprobaciones de cercanias
    }

    fun cargarbooleanos(){
        /*Listabooleanos[0]= false
        Listabooleanos[1]= false
        Listabooleanos[2]= false
        Listabooleanos[3]= false
        Listabooleanos[4]= false
        Listabooleanos[5]= false
        Listabooleanos[6]= false*/
    }

    //______________________________________________________________________________________________
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.m_home -> Toast.makeText(this," home", Toast.LENGTH_SHORT).show()
            R.id.m_link -> Toast.makeText(this," Link", Toast.LENGTH_SHORT).show()
            R.id.m_logout -> Toast.makeText(this," Logout", Toast.LENGTH_SHORT).show()
            R.id.m_perfil -> Toast.makeText(this," Perfil", Toast.LENGTH_SHORT).show()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    //______________________________________________________________________________________________
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    //______________________________________________________________________________________________
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    //______________________________________________________________________________________________
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}