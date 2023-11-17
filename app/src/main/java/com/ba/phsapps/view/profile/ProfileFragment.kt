package com.ba.phsapps.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ba.phsapps.R
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.databinding.FragmentProfileBinding
import com.ba.phsapps.databinding.FragmentProfileUpdateBinding
import com.google.gson.Gson
import com.ssoft.common.BaseFragment
import com.ssoft.common.util.SharedPreferenceUtil

class ProfileFragment:BaseFragment() {

    lateinit var binding:FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        super.onViewReady(view, savedInstanceState)


    }


    override fun onResume() {
        super.onResume()
        initView()


    }
    fun initView(){


        binding.editAction.setOnClickListener {

            findNavController()
                .navigate(R.id.action_profileFragment_to_profileUpdateFragment)

        }

        binding.backAction.setOnClickListener {
            findNavController()
                .popBackStack()
        }


        val str =  SharedPreferenceUtil.getUser(requireContext())

        val user = Gson().fromJson<UserModels>(str, UserModels::class.java)


        binding.nameTV.text = user.uName ?: ""
        binding.addressTV.text = user.uAddress ?: ""
        binding.telTV.text = user.uPhone ?: ""
        binding.cardID.text = user.uIDcard ?: ""
        binding.ageTV.text = "${user.uAge ?: "-"}"

        val strSex = when(user.uSex){
            1 -> { "ชาย"}
            1 -> { "หญิง" }
            else -> { "ไม่ระบุ" }

        }

        binding.genderTV.text = strSex



    }


}