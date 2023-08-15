package com.example.freshchatpaymob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.freshchat.consumer.sdk.Freshchat
import com.freshchat.consumer.sdk.FreshchatConfig

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val freshchatConfig = FreshchatConfig("e85b0e65-50c0-416f-a74a-f572755ca002", "5f478d19-a586-4969-960f-5350193ac7ae")
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