package com.ba.phsapps.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ba.phsapps.MainActivity
import com.ba.phsapps.R
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.view.intro.IntroActivity
import com.ba.phsapps.view.login.AuthActivity
import com.google.gson.Gson
import com.ssoft.common.util.SharedPreferenceUtil
import java.util.Timer
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer("SettingUp", false).schedule(2000) {

            val str =  SharedPreferenceUtil.getUser(this@SplashActivity)

            str?.let {
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                finish()
            }?: kotlin.run {
                startActivity(Intent(this@SplashActivity,AuthActivity::class.java))
                finish()
            }

//            val user = Gson().fromJson<UserModels>(str, UserModels::class.java)






        }

    }
}