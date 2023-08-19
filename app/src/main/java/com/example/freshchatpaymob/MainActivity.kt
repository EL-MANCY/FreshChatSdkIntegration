package com.example.freshchatpaymob

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.freshchat.consumer.sdk.Freshchat
import com.freshchat.consumer.sdk.FreshchatConfig
import com.google.firebase.FirebaseApp
import java.util.*

class MainActivity : AppCompatActivity() {

    private var currentLocale: Locale = Locale.ENGLISH

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

        val freshchatConfig = FreshchatConfig(BuildConfig.FRESHCHAT_APP_ID, BuildConfig.FRESHCHAT_APP_KEY)
        freshchatConfig.domain = "msdk.freshchat.com"
        freshchatConfig.isCameraCaptureEnabled = true
        freshchatConfig.isGallerySelectionEnabled = true
        freshchatConfig.isResponseExpectationEnabled = true
        freshchatConfig.isTeamMemberInfoVisible = true
        freshchatConfig.isUserEventsTrackingEnabled = true
        Freshchat.getInstance(applicationContext)?.init(freshchatConfig)

        val chatButton = findViewById<Button>(R.id.chatButton)
        chatButton.setOnClickListener { Freshchat.showConversations(this) }

        val switchLanguageButton = findViewById<Button>(R.id.switchLanguageButton)
        switchLanguageButton.setOnClickListener {
            currentLocale = if (currentLocale == Locale.ENGLISH) Locale("ar") else Locale.ENGLISH
            val config = Configuration(resources.configuration)
            config.setLocale(currentLocale)
            resources.updateConfiguration(config, resources.displayMetrics)
            Freshchat.notifyAppLocaleChange(applicationContext)
            Freshchat.notifyAppLocaleChange(applicationContext)
            finish()
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            applicationContext.startActivity(intent)
        }

    }


}