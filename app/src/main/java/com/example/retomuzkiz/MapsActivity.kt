package com.example.retomuzkiz

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.retomuzkiz.databinding.ActivityMapsBinding
<<<<<<< Updated upstream
=======
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.model.*
import com.google.android.material.navigation.NavigationView
>>>>>>> Stashed changes

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    protected lateinit var Listabooleanos : ArrayList<Boolean>
<<<<<<< Updated upstream
    protected lateinit var Listacoodenadas : ArrayList<LatLng>
=======

    lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
>>>>>>> Stashed changes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // crear el servicio de geolocalizacion
        val Servicio = Intent(applicationContext, ServicioGeolocalizacion::class.java)
        cargarbooleanos()
<<<<<<< Updated upstream
        cargarcordenadas()
        Servicio.putExtra("boleanos",Listabooleanos)
        Servicio.putExtra("coordenadas",Listacoodenadas)



=======
        Servicio.putExtra("booleanos",Listabooleanos)
>>>>>>> Stashed changes


        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
<<<<<<< Updated upstream
=======

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

>>>>>>> Stashed changes
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        //43.346497,-3.121751
        val PobeñakoErmita = LatLng(43.346497, -3.121751)
        mMap.addMarker(MarkerOptions().position(PobeñakoErmita).title("Pobeñako Ermita"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PobeñakoErmita))
    }

<<<<<<< Updated upstream


















    // ESTO DEVUELVE EL OBJETO QUE QUERAMOS
=======
    // ESTO DEVUELVE EL OBJETO QUE QUERAMOS DEL SERVICIO
>>>>>>> Stashed changes
    val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            // AQUI TIENE QUE IR EL ARRAY DE BOOLEANO DE COMPROBACION DE LOS PUNTOS DE INTERES
            Listabooleanos = intent.getBooleanArrayExtra("Booleanoscomprobados") as ArrayList<Boolean>
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

<<<<<<< Updated upstream
    fun cargarcordenadas(){

        val PobeñakoErmita = LatLng(43.346497, -3.121751)
        Listacoodenadas[0]= PobeñakoErmita
        //cargar las cordenadas que se usaran para las comprobaciones de cercanias


    }
    fun cargarbooleanos(){

=======

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
    fun cargarbooleanos(){
        // array de boolanos
>>>>>>> Stashed changes
        Listabooleanos[0]= false
        Listabooleanos[1]= false
        Listabooleanos[2]= false
        Listabooleanos[3]= false
        Listabooleanos[4]= false
        Listabooleanos[5]= false
        Listabooleanos[6]= false
<<<<<<< Updated upstream
=======
    }
>>>>>>> Stashed changes

    }
}