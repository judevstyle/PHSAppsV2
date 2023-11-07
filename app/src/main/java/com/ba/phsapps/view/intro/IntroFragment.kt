package com.ba.phsapps.view.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ba.phsapps.R
import com.ba.phsapps.databinding.FragmentIntroBinding
import com.ssoft.common.BaseFragment

class IntroFragment :BaseFragment(){

    lateinit var binding: FragmentIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        super.onViewReady(view, savedInstanceState)

        initView()


    }

    private fun initView(){

        binding.loginAction.setOnClickListener {

            findNavController()
                .navigate(R.id.action_introFragment_to_loginFragment)

        }


    }

}