package com.ba.phsapps.view.resetPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ba.phsapps.R
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.databinding.FragmentResetPasswordBinding
import com.ba.phsapps.view.login.LoginUi
import com.ba.phsapps.view.login.LoginViewModels
import com.google.gson.Gson
import com.ssoft.common.BaseFragment
import com.ssoft.common.util.SharedPreferenceUtil
import com.taitos.nup.common.EventObserver
import org.koin.android.viewmodel.ext.android.viewModel

class ResetPasswordFragment:BaseFragment() {

    val viewModel: ResetPasswordViewModels by viewModel()

    private lateinit var binding: FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        super.onViewReady(view, savedInstanceState)

        initView()
        observ()
    }

    private fun initView() {
        val str =  SharedPreferenceUtil.getUser(requireContext())

        val user = Gson().fromJson<UserModels>(str, UserModels::class.java)

        binding.cancelAction.setOnClickListener {
            findNavController()
                .popBackStack()
        }

        binding.saveAction.setOnClickListener {

            viewModel.resetPass(user.uIDcard,binding.passET.text.toString(),binding.passCF.text.toString())
//            viewModel.resetPass("1440900134568","123456789","123456789")

        }



    }

    fun observ(){

        viewModel.onLoginData.observe(viewLifecycleOwner,EventObserver{state:LoginUi->

            when(state){
                is LoginUi.onLoading->{
                    showProgressDialog()
                }
                is LoginUi.onValidate->{

                    hideDialog()
                    showToast(state.msg)
                }
                is LoginUi.onError->{
                    hideDialog()
                    showToast("เกิดข้อผิดพลาด กรุณาลองใหม่อีกครั้ง")

                }
                is LoginUi.onSuccess->{

                    hideDialog()
                    showToast("บันทึกข้อมูลเรียบร้อย")

                    findNavController()
                        .popBackStack()
                }


            }


        })


    }

}