package com.example.udemy_notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val channelID="com.example.udemy_notification.channel1"
    private lateinit var notificationManager:NotificationManager
    private val KEY_REPLY="key_reply"
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID,"DemoChannel","this is DEMO")




        button1.setOnClickListener(View.OnClickListener {
            dispalyNotification()
        })
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    private fun dispalyNotification(){

        val intent=Intent(this,MainActivity2::class.java)
        val pendingIntent:PendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        //for click enabling whole notification use  setContentIntent()
       // when building the notification


        //reply action
        val remoteInput:androidx.core.app.RemoteInput=RemoteInput.Builder(KEY_REPLY).run {
            setLabel("Insert your name here")
            build()
        }

        val replyAction:NotificationCompat.Action=NotificationCompat.Action.Builder(0,"REPLY",pendingIntent).addRemoteInput(remoteInput).build()

        //action button 1 detail
        val detail_intent=Intent(this,detailsActivity::class.java)
        val detail_pendingIntent:PendingIntent=PendingIntent.getActivity(this,0,detail_intent,PendingIntent.FLAG_UPDATE_CURRENT)

        val detail_action:NotificationCompat.Action=NotificationCompat.Action.Builder(0,"Details",detail_pendingIntent).build()

        //action 2 setting
        val setting_intent=Intent(this,SettingsActivity::class.java)
        val setting_pendingIntent:PendingIntent= PendingIntent.getActivity(this,0,setting_intent,PendingIntent.FLAG_UPDATE_CURRENT)
        val setting_action:NotificationCompat.Action=NotificationCompat.Action.Builder(0,"settings",setting_pendingIntent).build()


        val notificationID=45
        val notification=NotificationCompat.Builder(this@MainActivity,channelID)
                .setContentTitle("Demo Title")
                .setContentText("This is demo Notification")
                .setSmallIcon(android.R.drawable.alert_dark_frame)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
               .addAction(detail_action).addAction(setting_action)
            .addAction(replyAction)
            .build()
        notificationManager?.notify(notificationID,notification)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(id: String, name:String, channelDescription:String){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val importanceLevel= NotificationManager.IMPORTANCE_HIGH
            val channel:NotificationChannel=NotificationChannel(id,name,importanceLevel)
            channel.description=channelDescription
            notificationManager?.createNotificationChannel(channel)
        }


    }
}