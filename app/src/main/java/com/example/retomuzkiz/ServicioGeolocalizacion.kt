package com.example.retomuzkiz

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ServicioGeolocalizacion : Service() {
    private lateinit var fusedLocation : FusedLocationProviderClient
    protected lateinit var Listabooleanos : ArrayList<Boolean>
    protected lateinit var Listacoodenadas : ArrayList<LatLng>
    lateinit var job: Job

    val NOTIFICATION_ID = 1 // >= 1 !


    override fun onCreate() {

        super.onCreate()
        println("onCreate")

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


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Listabooleanos = intent.getBooleanArrayExtra("boleanos") as ArrayList<Boolean>
        Listacoodenadas = intent.getParcelableArrayListExtra<LatLng>("coordenadas") as ArrayList<LatLng>
        var sec = intent.getStringExtra("segundos")!!.toInt()
        println("onstartcomand")
        job = GlobalScope.launch{
            //BULCE DE COMPROBACION DE BOOLEANOS
            while (true){
                // si se ha cancelado salimos del bucle
                if(job.isCancelled){

                    break
                }


                Thread.sleep(1000)
                // AQUI TIENE QEU IR LA COMPROBACION DE BOOLEANOS





                //devolucion de la lista de booleanos
                val senderIntent = Intent("broadcast")
                senderIntent.putExtra("Booleanoscomprobados",Listabooleanos)
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