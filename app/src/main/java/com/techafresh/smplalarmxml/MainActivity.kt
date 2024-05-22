package com.techafresh.smplalarmxml

import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import de.coldtea.smplr.smplralarm.alarmNotification
import de.coldtea.smplr.smplralarm.channel
import de.coldtea.smplr.smplralarm.smplrAlarmSet


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val hour = 13
        val minute = 5


        val snoozeIntent = Intent(applicationContext, ActionReceiver::class.java).apply {
            action = "ACTION_SNOOZE"
            putExtra("HOUR", hour)
            putExtra("MINUTE", minute)
        }

        val dismissIntent = Intent(applicationContext, ActionReceiver::class.java).apply {
            action = "ACTION_DISMISS"
        }

        smplrAlarmSet(applicationContext) {
            hour { hour }
            min { minute }

            notification {
                alarmNotification {
                    smallIcon { R.drawable.ic_launcher_foreground }
                    title { "Alarm Title" }
                    message { "Alarm Message" }
                    bigText { "Alarm Big Text" }
                    firstButtonText { "Snooze" }
                    secondButtonText { "Dismiss" }
                    firstButtonIntent { snoozeIntent }
                    secondButtonIntent { dismissIntent }
                }
            }
            notificationChannel {
                channel {
                    importance { NotificationManager.IMPORTANCE_HIGH }
                    showBadge { false }
                    name { "Alarm Channel" }
                    description { "This notification channel is created by Techafresh" }
                }
            }
        }



    }
}