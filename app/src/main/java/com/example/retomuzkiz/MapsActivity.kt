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
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.retomuzkiz.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    protected lateinit var Listabooleanos : ArrayList<Boolean>
    protected lateinit var Listacoodenadas : ArrayList<LatLng>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // crear el servicio de geolocalizacion
        val Servicio = Intent(applicationContext, ServicioGeolocalizacion::class.java)
        cargarbooleanos()
        cargarcordenadas()
        Servicio.putExtra("boleanos",Listabooleanos)
        Servicio.putExtra("coordenadas",Listacoodenadas)





        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
        Listacoodenadas[0]= PobeñakoErmita
        //cargar las cordenadas que se usaran para las comprobaciones de cercanias


    }
    fun cargarbooleanos(){

        Listabooleanos[0]= false
        Listabooleanos[1]= false
        Listabooleanos[2]= false
        Listabooleanos[3]= false
        Listabooleanos[4]= false
        Listabooleanos[5]= false
        Listabooleanos[6]= false

    }
}