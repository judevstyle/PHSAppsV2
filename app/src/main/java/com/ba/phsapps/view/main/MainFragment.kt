package com.ba.phsapps.view.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ba.phsapps.R
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.databinding.FragmentMainBinding
import com.google.gson.Gson
import com.ssoft.common.BaseFragment
import com.ssoft.common.util.SharedPreferenceUtil
import androidx.annotation.NonNull
import com.ssoft.common.util.LogUtil

import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
@RuntimePermissions
class MainFragment : BaseFragment() {
    lateinit var binding:FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        super.onViewReady(view, savedInstanceState)
        binding.settingAction1.setOnClickListener {

            findNavController()
                .navigate(R.id.action_mainFragment_to_profileFragment)

        }
        initView()
        requestNotificationWithPermissionCheck()


    }

    @SuppressLint("MissingPermission")
    @NeedsPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun requestNotification() {
        LogUtil.showLogError("NeedsPermission", "on")

    }


    fun initView(){

       val str =  SharedPreferenceUtil.getUser(requireContext())

        val user = Gson().fromJson<UserModels>(str,UserModels::class.java)




        binding.nameTV.text = user.uName
        binding.levelTV.text = "ระดับความเสี่ยง : ${user.uStatus ?: "-"}"
        binding.ageTV.text = "อายุ : -"


        if (user.uRole == 1){
            binding.menuimg1.setImageResource(R.drawable.menu1_under)
        }else{
            binding.menuimg1.setImageResource(R.drawable.ic_menu_history)

        }



        binding.menu1.setOnClickListener {
            if (user.uRole == 1){
                findNavController()
                    .navigate(R.id.action_mainFragment_to_userUnderFragment)
            }else{
                findNavController()
                    .navigate(R.id.action_mainFragment_to_userHistoryFragment)


            }

        }
        binding.menu2.setOnClickListener {

            findNavController()
                .navigate(R.id.action_mainFragment_to_medicalFragment)

        }

        binding.menu3.setOnClickListener {

            findNavController()
                .navigate(R.id.action_mainFragment_to_newsFragment)

        }
        binding.menu4.setOnClickListener {

            findNavController()
                .navigate(R.id.action_mainFragment_to_resetPasswordFragment)

        }

        binding.sosAction.setOnClickListener {
            callPhoneWithPermissionCheck()

        }
//
//        binding.settingAction.setOnClickListener {
//
//
//        }


    }

    @SuppressLint("MissingPermission")
    @NeedsPermission(Manifest.permission.CALL_PHONE)
    fun callPhone() {

        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1669"))
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated method
        onRequestPermissionsResult(requestCode, grantResults)
    }


}