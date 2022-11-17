package com.example.retomuzkiz

import android.Manifest
import android.R
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ServicioGeolocalizacion : Service() {
    private lateinit var fusedLocation : FusedLocationProviderClient
    protected lateinit var ubicacion : LatLng
    protected lateinit var Listabooleanos : ArrayList<Boolean>
    lateinit var job: Job
    lateinit  var puenteRomano : Location
    lateinit  var pobalekoBurdinola : Location
    lateinit  var pobenakoErmita : Location
    lateinit  var hondartzaArena : Location
    lateinit  var ibilbideItsaslur : Location
    lateinit  var muniatonesGaztelua : Location
    lateinit  var sanJuan : Location
    lateinit  var ubicacionact : Location



    val NOTIFICATION_ID = 1 // >= 1 !


    override fun onCreate() {
        //1

        puenteRomano = Location("puenteromano")
        puenteRomano.latitude = 43.316772
        puenteRomano.longitude = -3.119471


        //2

        pobalekoBurdinola=Location("pobalekoBurdinola")
        pobalekoBurdinola.latitude = 43.296111
        pobalekoBurdinola.longitude = -3.126113

        //3
        pobenakoErmita=Location("pobenakoErmita")
        pobenakoErmita.latitude = 43.346497
        pobenakoErmita.longitude = -3.121751
        //4

        hondartzaArena=Location("hondartzaArena")
        hondartzaArena.latitude = 43.349722
        hondartzaArena.longitude = -3.116389
        //5

        ibilbideItsaslur=Location("ibilbideItsaslur")
        ibilbideItsaslur.latitude = 43.331075
        ibilbideItsaslur.longitude = -3.117392
        //6

        muniatonesGaztelua=Location("muniatonesGaztelua")
        muniatonesGaztelua.latitude = 43.323611
        muniatonesGaztelua.longitude = -3.112503
        //7
        sanJuan=Location("sanJuan")
        sanJuan.latitude = 43.330278
        sanJuan.longitude = -3.129061






        super.onCreate()
        println("onCreate")
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocation.lastLocation.addOnSuccessListener { location->
            if(location!=null){
                ubicacion=LatLng(location.latitude,location.longitude)
            }
        }
        startForeground()


    }

    private fun startForeground() {
        println("startforeground")
        // OJO! Expresión ternaria! para asignar valor a channelId, no es un IF de sentencias
        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel()

            } else {
                // If earlier version channel ID is not used
                // https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)
                ""
            }
        println("startforeground2")
        val notificationBuilder = NotificationCompat.Builder(applicationContext, channelId )
        val notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.drawable.ic_input_add)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setContentTitle("Mi Servicio")
            .setContentText("Mi Servicio se está ejecutando en segundo plano")
            .build()
        startForeground(101, notification)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String{
        val channelId = "mi_servicio"
        val channelName = "Mi servicio en segundo plano"
        val chan = NotificationChannel(channelId,
            channelName, NotificationManager.IMPORTANCE_HIGH)
        chan.lightColor = Color.BLUE
        chan.importance = NotificationManager.IMPORTANCE_NONE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }


    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        //Cargar arra yde booleanos
        Listabooleanos = intent.getBooleanArrayExtra("booleanos") as ArrayList<Boolean>



        println("onstartcomand")
        job = GlobalScope.launch{
            while (true){
                //obtener la ubicacion actual
                fusedLocation.lastLocation.addOnSuccessListener { location->
                    if(location!=null){
                        ubicacion=LatLng(location.latitude,location.longitude)
                        var ubilat = ubicacion.latitude
                        var lon =ubicacion.longitude
                        ubicacionact = Location("ubicacionact")
                        ubicacionact.latitude = ubilat
                        ubicacionact.longitude = lon
                    }
                }

                // si se ha cancelado salimos del bucle
                if(job.isCancelled){

                    break
                }
                //comprobaciones de lso puntos de ubicacion
                if(ubicacionact.distanceTo(puenteRomano) < 50){
                    //comprobacion de distancia del punto 1
                    Listabooleanos[0]=true
                }else{
                    Listabooleanos[0]=false
                }


                if(ubicacionact.distanceTo(pobalekoBurdinola) < 50){
                    //comprobacion de distancia del punto 2
                    Listabooleanos[1]=true
                }else{
                    Listabooleanos[0]=false
                }
                if(ubicacionact.distanceTo(pobalekoBurdinola) > 50){
                    //comprobacion de distancia del punto 2
                    Listabooleanos[1]=false
                }else{
                    Listabooleanos[1]=false
                }
                if(ubicacionact.distanceTo(pobenakoErmita) < 50){
                    //comprobacion de distancia del punto 3
                    Listabooleanos[2]=true
                }else{
                    Listabooleanos[2]=false
                }
                if(ubicacionact.distanceTo(hondartzaArena) < 50){
                    //comprobacion de distancia del punto 4
                    Listabooleanos[3]=true
                }else{
                    Listabooleanos[3]=false
                }
                if(ubicacionact.distanceTo(ibilbideItsaslur) < 50){
                    //comprobacion de distancia del punto 5
                    Listabooleanos[4]=true
                }else{
                    Listabooleanos[4]=false
                }
                if(ubicacionact.distanceTo(muniatonesGaztelua) < 50){
                    //comprobacion de distancia del punto 6
                    Listabooleanos[5]=true
                }else{
                    Listabooleanos[5]=false
                }
                if(ubicacionact.distanceTo(sanJuan) < 50){
                    //comprobacion de distancia del punto 6
                    Listabooleanos[6]=true
                }else{
                    Listabooleanos[6]=false
                }


                Thread.sleep(5000)






                //devolucion de la lista de booleanos
                val senderIntent = Intent("broadcast")
                senderIntent.putExtra("Booleanoscomprobados",Listabooleanos)
                senderIntent.putExtra("ubicacionactual",ubicacion)
                LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(senderIntent)

            }


        }


        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
        stopForeground(true)
        job.cancel()


        stopSelf()
    }

    // obliga a implementar el método
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

}