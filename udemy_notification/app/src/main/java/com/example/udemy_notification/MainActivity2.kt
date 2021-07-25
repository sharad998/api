package com.example.udemy_notification

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        recieveInput()


    }
    private fun recieveInput(){
        val KEY_REPLY="key_reply"
        val intent=this.intent
        val remoteInput=RemoteInput.getResultsFromIntent(intent)
        if (remoteInput!=null){
            val inputString= remoteInput.getString(KEY_REPLY).toString()
            textView.setText(inputString)
            textView.textSize= 32F

            val channelID="com.example.udemy_notification.channel1"
            // need same channel id as the notifiaction from which this activity has been called
            val notificatioNID=45

            val repliedNotification= NotificationCompat
                .Builder(this,channelID)
                .setSmallIcon(android.R.drawable.arrow_down_float)
                .setContentText("your reply recieved")
                .build()
            val notificationManager:NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificatioNID,repliedNotification)


        }

    }
}