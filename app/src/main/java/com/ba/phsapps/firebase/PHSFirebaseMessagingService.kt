package com.ba.phsapps.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ba.phsapps.MainActivity
import com.ba.phsapps.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


class PHSFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "iCONSFirebaseService"

    val GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL"

    private fun scheduleJob() {}

    private fun sendRegistrationToServer(str: String) {}

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        Log.e(TAG, "From: " + remoteMessage.from)
        Log.e("sendNotification ", "${remoteMessage.data.toString()}--${ remoteMessage.notification!!.title}")
        if (remoteMessage.data.size > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.data)
            handleNow(remoteMessage.notification, remoteMessage.data)
            return
        }
//        Log.e(TAG, "Message Notification Body: " + remoteMessage.notification!!.body)
        sendNotification(
            remoteMessage.notification!!.title, remoteMessage.notification!!
                .body, remoteMessage.notification!!.body, remoteMessage.notification!!.body
        )
    }

    override fun onNewToken(str: String) {
        Log.d(TAG, "Refreshed token: $str")
        sendRegistrationToServer(str)
    }

    private fun handleNow(notification: RemoteMessage.Notification?, map: Map<String, String?>) {
        var str: String?
        var str2: String?
        val str3: String?
        Log.e(TAG, "handleNow...")
        var str4: String? = ""
        if (notification != null) {
            str2 = notification.title
            str = notification.body
        } else {
            str = str4
            str2 = str
        }
        if (map.size > 0) {
            str2 = map["title"]
            str = map["body"]
            val str5 = if (map["type"] != null) map["type"] else str4
            if (map["message"] != null) {
                str4 = map["message"]
            }
            str3 = str4
            str4 = str5
        } else {
            str3 = str4
        }
        Log.e("TAG", "sendNotification...title: $str2, body: $str, type: $str4, message: $str3")


        //   Log.e("state", "${AvtivityActive.isMainActivty()}")
//        if (AvtivityActive.isMainActivty()) {
//            val intent = Intent("openNotification")
//            intent.putExtra("type","otp")
//            intent.putExtra("message",str3)
//
//            sendBroadcast(intent)
//        } else
          sendNotification(str2, str, str4, str3)


    }

    private fun sendNotification(str: String?, str2: String?, str3: String?, str4: String?) {
        Log.e(TAG, "sendNotification... ${str4.toString()}")
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("pushnotification", "YES")
        intent.putExtra("type", str2)
        intent.putExtra("message", str4)
        var string = getBitmapfromUrl( Uri.parse("https://images.unsplash.com/photo-1633332755192-727a05c4013d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8dXNlcnxlbnwwfHwwfHx8MA%3D%3D&w=1000&q=80"))

        val contentIntent = NotificationCompat.Builder(this as Context, "Message")
            .setSmallIcon(R.mipmap.ic_launcher as Int)
            .setContentTitle(str)
            .setContentText(str2)
            .setAutoCancel(true)


            .setSound(RingtoneManager.getDefaultUri(2))
            .setLargeIcon(string)
            .setContentIntent(
                PendingIntent.getActivity(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .setGroup("aaa")
            .build()

//        val groupNotificationBuilder = NotificationCompat.Builder(this, "Message")
//            .setSmallIcon(R.mipmap.ic_launcher as Int).setContentTitle(str)
//            .setContentText(str2).setAutoCancel(true)
//            .setSound(RingtoneManager.getDefaultUri(2))
//            .setLargeIcon(imageBitmap)
//            .setContentIntent(
//                PendingIntent.getActivity(
//                    this,
//                    0,
//                    intent,
//                    PendingIntent.FLAG_UPDATE_CURRENT
//                )
//            )
////            .setGroup("aaa")
////            .setGroupSummary(true)
//            .build()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        System.currentTimeMillis().toInt()
//        NotificationManagerCompat.from(this).apply {
//            notify(System.currentTimeMillis().toInt(), contentIntent)
//            // ไว้สำหรับแยก Group Notification
//            notificationManager.notify(1, contentIntent)
//
//        }
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    "Message",
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
        }
        val dateNow = Calendar.getInstance().timeInMillis

        Log.e("id", "${dateNow.toInt()}")


    }

    private fun getBitmapfromUrl(imageUrl: Uri?): Bitmap? {
        return try {
            val url = URL(imageUrl?.toString())
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
            null
        }
    }
}