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
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ServicioGeolocalizacion : Service() {
    private lateinit var fusedLocation : FusedLocationProviderClient
    private lateinit var Listabooleanos : ArrayList<Boolean>
    lateinit var job: Job
    lateinit  var puenteRomano : Location
    lateinit  var pobalekoBurdinola : Location
    lateinit  var pobenakoErmita : Location
    lateinit  var hondartzaArena : Location
    lateinit  var ibilbideItsaslur : Location
    lateinit  var muniatonesGaztelua : Location
    lateinit  var sanJuan : Location
    lateinit  var ubicacionact : Location
    private var booleano0 = false
    private var booleano1 = false
    private var booleano2 = false
    private var booleano3 = false
    private var booleano4 = false
    private var booleano5 = false
    private var booleano6 = false



    val NOTIFICATION_ID = 1 // >= 1 !

        //-------------------------------------------------------------------------------
    override fun onCreate() {

        //creacion de las cordenadas a comprobar
        fusedLocation= LocationServices.getFusedLocationProviderClient(applicationContext)
        ubicacionact = Location("ubicacionact")
        //1
        Listabooleanos = arrayListOf<Boolean>()
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

        //-------------------------------------------------------------------------------

        super.onCreate()
        println("onCreate")
        //permisos de ubicacion
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
        //inicializar la ubicacion
        //si quitamos estas lineas deja de funcionar por alguna razon
        fusedLocation.lastLocation.addOnSuccessListener { location->

            if(location!=null){
                ubicacionact=LatLng(location.latitude,location.longitude)

            }
        }
        startForeground()


    }

    //-------------------------------------------------------------------------------

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
    //-------------------------------------------------------------------------------

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
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingPermission")

    //-------------------------------------------------------------------------------
    // ejecutcion del servicio
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        //Cargar array de booleanos
        Listabooleanos = arrayListOf<Boolean>()

        Listabooleanos.add(intent.getBooleanExtra("boleano0",false))
        Listabooleanos.add(intent.getBooleanExtra("boleano1",false))
        Listabooleanos.add(intent.getBooleanExtra("boleano2",false))
        Listabooleanos.add(intent.getBooleanExtra("boleano3",false))
        Listabooleanos.add(intent.getBooleanExtra("boleano4",false))
        Listabooleanos.add(intent.getBooleanExtra("boleano5",false))
        Listabooleanos.add(intent.getBooleanExtra("boleano6",false))

        //funcion para obtener de manera continua la ubicacion actual
        //Anotacion: lastlocation no se actualizar, hay que usar currentlocation
        fun actualizarubi(){
            fusedLocation.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token

                override fun isCancellationRequested() = false
            }).addOnSuccessListener {
                if(it!==null){
                    ubicacionact.latitude=it.latitude
                    ubicacionact.longitude=it.longitude
                }
            }
            fusedLocation.lastLocation.addOnSuccessListener { location->
                if(location!=null){
                   ubicacion=LatLng(location.latitude,location.longitude)
                }
            }
        }

        job = GlobalScope.launch{
            //-------------------------------------------------------------------------------
            //ejecucion de deteccion por cercania
            while (true){
                println("ejecucion servicio nivel while")
                //limpieza de cache para prevenir errores varios
                clearchache.deleteCache(applicationContext)
                //obtener la ubicacion actual
                actualizarubi()
          // si se ha cancelado salimos del bucle
                if(job.isCancelled){
                    break
                }
                //comprobaciones de los puntos de ubicacion
                if(ubicacionact.distanceTo(puenteRomano).toInt() < 75){
                    Listabooleanos[0]= true
                }
                if(ubicacionact.distanceTo(puenteRomano).toInt() >  75){
                    Listabooleanos[0]=false
                }
                if(ubicacionact.distanceTo(pobalekoBurdinola).toInt() <   75){
                    Listabooleanos[1]=true
                }
                if(ubicacionact.distanceTo(pobalekoBurdinola).toInt() >   75){
                    Listabooleanos[1]=false
                }
                if(ubicacionact.distanceTo(pobenakoErmita).toInt() <   75){
                    Listabooleanos[2]=true
                }
                if(ubicacionact.distanceTo(pobenakoErmita).toInt() >   75){
                    Listabooleanos[2]=false
                }
                if(ubicacionact.distanceTo(hondartzaArena).toInt() <  75){
                    Listabooleanos[3]=true
                }
                if(ubicacionact.distanceTo(hondartzaArena).toInt() >  75){
                    Listabooleanos[3]=false
                }
                if(ubicacionact.distanceTo(ibilbideItsaslur).toInt() <  75){
                    Listabooleanos[4]=true
                }
                if(ubicacionact.distanceTo(ibilbideItsaslur).toInt() >  75){
                    Listabooleanos[4]=false
                }
                if(ubicacionact.distanceTo(muniatonesGaztelua).toInt() <  75){
                    Listabooleanos[5] =true
                }
                if(ubicacionact.distanceTo(muniatonesGaztelua).toInt() >  75){
                    Listabooleanos[5] =false
                }
                if(ubicacionact.distanceTo(sanJuan).toInt() <  75){
                    Listabooleanos[6] =true
                }
                if(ubicacionact.distanceTo(sanJuan).toInt() >  75){
                    Listabooleanos[6] = false
                }
                Thread.sleep(2500)

                //devolucion de la lista de booleanos
                booleano0 = Listabooleanos[0]
                booleano1 = Listabooleanos[1]
                booleano2 = Listabooleanos[2]
                booleano3 = Listabooleanos[3]
                booleano4 = Listabooleanos[4]
                booleano5 = Listabooleanos[5]
                booleano6 = Listabooleanos[6]
                //devolucion de los booleanos comprobados
                val senderIntent = Intent("broadcast")
                senderIntent.putExtra("Booleano0",booleano0)
                senderIntent.putExtra("Booleano1",booleano1)
                senderIntent.putExtra("Booleano2",booleano2)
                senderIntent.putExtra("Booleano3",booleano3)
                senderIntent.putExtra("Booleano4",booleano4)
                senderIntent.putExtra("Booleano5",booleano5)
                senderIntent.putExtra("Booleano6",booleano6)
                senderIntent.putExtra("ubicacionactual",ubicacion)
                LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(senderIntent)

            }


        }


        return START_STICKY
    }

    //-------------------------------------------------------------------------------

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
        stopForeground(true)
        job.cancel()


        stopSelf()
    }

    //-------------------------------------------------------------------------------

    // obliga a implementar el método
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

}