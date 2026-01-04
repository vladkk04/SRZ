package com.electro.fish.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Start
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import com.electro.fish.feature.fishingSession.session.presentation.R
import dagger.hilt.android.AndroidEntryPoint

enum class FishingSessionAction {
    START, STOP
}


@AndroidEntryPoint
class FishingSessionService: Service() {

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            FishingSessionAction.START.name -> startSession(2)
            FishingSessionAction.STOP.name -> stopSession()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun startSession(fishCount: Int) {
        val notification = NotificationCompat.Builder(this, "fishing_session")
            .setContentTitle("Fishing Session")
            .setContentText("Fish caught: $fishCount")

            // --- NEW: STATUS CHIP & COLOR ---
            .setSubText("Tracking") // 1. This appears next to App Name (like a status chip)
            .setColor(Color.White.toArgb())  // 2. This colors the Small Icon and the App Name/SubText

            // --------------------------------
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Don't forget this!
            .setWhen(System.currentTimeMillis())
            .setUsesChronometer(true)
            .setShowWhen(true)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)

            // Optional: If you want to show more lines of text (Status + Fish Count)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Fish caught: $fishCount\nStatus: Active and recording"))

            .build()


        startForeground(1, notification)
    }

    private fun stopSession() {

        stopSelf()
    }

    private fun createNotificationChannel() {

        val name = "fd"
        val descriptionText = "getString(R.string.channel_description)"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel("fishing_session", name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}