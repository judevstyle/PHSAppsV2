package com.ba.phsapps.view.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.ba.phsapps.R
import com.ba.phsapps.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {


    lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?



        val navController = navHostFragment?.navController

        navController?.addOnDestinationChangedListener { _, destination, _ ->


            when (destination.id) {

                R.id.mainFragment -> {
                    finish()
//                    LogUtil.showLogError("dkdk","--dkolws")
                }
//                R.id.propertyAttactFragment -> {
//                    titleTV.text = "เลือก Property"
//
////                    LogUtil.showLogError("dkdk","--dkolws")
//                }

            }
        }
        super.onBackPressed()


    }

}