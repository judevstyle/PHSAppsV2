package com.ba.phsapps.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ba.phsapps.R
import com.ba.phsapps.databinding.FragmentLoginBinding
import com.google.gson.Gson
import com.ssoft.common.BaseFragment
import com.ssoft.common.util.SharedPreferenceUtil
import com.taitos.nup.common.EventObserver
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    lateinit var binding: FragmentLoginBinding

    val viewModel: LoginViewModels by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        super.onViewReady(view, savedInstanceState)

        binding.loginAction.setOnClickListener {
            viewModel.login("1111335485", "1234")

//            findNavController()
//                .navigate(R.id.action_loginFragment_to_mainFragment)

        }
        observ()

    }

    fun observ() {

        viewModel.onLoginData.observe(viewLifecycleOwner, EventObserver { state: LoginUi ->

            when (state) {
                is LoginUi.onLoading -> {
                    showProgressDialog()
                }

                is LoginUi.onValidate -> {

                    hideDialog()
                    showToast(state.msg)
                }

                is LoginUi.onError -> {
                    hideDialog()
                    showToast("เกิดข้อผิดพลาด")

                }

                is LoginUi.onSuccess -> {

                    SharedPreferenceUtil.updateUser(requireContext(), Gson().toJson(state.data))
                    hideDialog()
                    findNavController()
                        .navigate(R.id.action_loginFragment_to_mainFragment)

                }


            }


        })


    }
}