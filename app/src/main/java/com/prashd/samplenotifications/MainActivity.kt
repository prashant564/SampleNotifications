package com.prashd.samplenotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.prashd.samplenotifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel(getString(R.string.notification_channel_id),
        getString(R.string.notification_channel_name))
        binding.btnSendNotification.setOnClickListener {
            val notificationManager = ContextCompat.getSystemService(this,
                NotificationManager::class.java) as NotificationManager
            notificationManager.sendNotification("This is a test description",this)
        }
    }

    fun createNotificationChannel(channelId:String, channelName: String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelId,channelName,
            NotificationManager.IMPORTANCE_HIGH).apply {
                setShowBadge(false)
            }
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "This is a test notification!!"

            val notificationManager = getSystemService(
                    NotificationManager::class.java
            )

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}