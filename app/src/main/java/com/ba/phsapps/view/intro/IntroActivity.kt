package com.ba.phsapps.view.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ba.phsapps.R
import com.ba.phsapps.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {


    lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}