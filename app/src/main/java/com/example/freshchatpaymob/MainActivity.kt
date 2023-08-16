package com.example.freshchatpaymob

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.freshchat.consumer.sdk.Freshchat
import com.freshchat.consumer.sdk.FreshchatConfig
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

        val freshchatConfig = FreshchatConfig(
            BuildConfig.FRESHCHAT_APP_ID,
            BuildConfig.FRESHCHAT_APP_KEY
        )
        freshchatConfig.domain = "msdk.freshchat.com"
        freshchatConfig.isCameraCaptureEnabled = true
        freshchatConfig.isGallerySelectionEnabled = true
        freshchatConfig.isResponseExpectationEnabled = true
        freshchatConfig.isTeamMemberInfoVisible = true
        freshchatConfig.isUserEventsTrackingEnabled = true
        Freshchat.getInstance(applicationContext)?.init(freshchatConfig)
        val chatButton = findViewById<Button>(R.id.chatButton)
        chatButton.setOnClickListener {
            Freshchat.showConversations(this)
        }
    }


}