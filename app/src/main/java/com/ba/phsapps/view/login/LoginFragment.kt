package com.ba.phsapps.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ba.phsapps.R
import com.ba.phsapps.databinding.FragmentLoginBinding
import com.ssoft.common.BaseFragment

class LoginFragment : BaseFragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        super.onViewReady(view, savedInstanceState)

        binding.loginAction.setOnClickListener {

            findNavController()
                .navigate(R.id.action_loginFragment_to_mainFragment)

        }


    }
}