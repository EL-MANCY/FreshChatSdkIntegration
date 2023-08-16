package com.example.freshchatpaymob

import android.util.Log
import com.freshchat.consumer.sdk.Freshchat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FreshChatFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Freshchat.getInstance(this).setPushRegistrationToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (Freshchat.isFreshchatNotification(message)) {
            Freshchat.handleFcmMessage(this, message)
        } else {
            Log.d("UnHandled", "Received a non-Freshchat notification")
        }
    }
}