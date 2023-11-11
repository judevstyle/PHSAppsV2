package com.ba.phsapps.view.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ba.phsapps.R
import com.ba.phsapps.databinding.FragmentRegisterBinding
import com.ba.phsapps.view.login.LoginUi
import com.ba.phsapps.view.login.LoginViewModels
import com.google.gson.Gson
import com.ssoft.common.BaseFragment
import com.ssoft.common.util.SharedPreferenceUtil
import com.taitos.nup.common.EventObserver
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment:BaseFragment() {
    private lateinit var binding: FragmentRegisterBinding

    val viewModel: RegisterViewModels by viewModel()
    var titleIndex = -1
    var typeIndex = -1
    var hosIndex = -1
    var genderIndex = -1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        super.onViewReady(view, savedInstanceState)


        initView()
        onserv()
    }

    private fun initView() {

       val gender = arrayListOf<String>("ชาย","หญิง")
        val adaptergender = ArrayAdapter(
            requireContext(), R.layout.component_list_item, gender
        )
        binding.genderAutoCompleteTextView.setAdapter(adaptergender);
        binding.genderAutoCompleteTextView
            .setOnItemClickListener(object :
                AdapterView.OnItemClickListener {
                override fun onItemClick(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long
                ) {
                    genderIndex = p2

                }

            });

        val title = arrayListOf<String>("นาย","นาง","นางสาว")
        val adaptertitle = ArrayAdapter(
            requireContext(), R.layout.component_list_item, title
        )
        binding.titleAutoCompleteTextView.setAdapter(adaptertitle);
        binding.titleAutoCompleteTextView
            .setOnItemClickListener(object :
                AdapterView.OnItemClickListener {
                override fun onItemClick(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long
                ) {
                    titleIndex = p2

                }

            });

        val type = arrayListOf<String>("อาสาสมัครสาธารณสุข","ผู้สูงวัย")
        val adaptertype = ArrayAdapter(
            requireContext(), R.layout.component_list_item, type
        )
        binding.typeAutoCompleteTextView.setAdapter(adaptertype);
        binding.typeAutoCompleteTextView
            .setOnItemClickListener(object :
                AdapterView.OnItemClickListener {
                override fun onItemClick(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long
                ) {
                    typeIndex = p2

                }

            });
        binding.hosAutoCompleteTextView
            .setOnItemClickListener(object :
                AdapterView.OnItemClickListener {
                override fun onItemClick(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long
                ) {
                    hosIndex = p2

                }

            });



        binding.registerAction.setOnClickListener {





            viewModel.register(binding.idCardET.text.toString()
            ,hosIndex
                ,typeIndex
                ,titleIndex
                ,binding.fnameET.text.toString()
                ,binding.lnameET.text.toString()
                ,binding.addressET.text.toString()
                ,""
                ,genderIndex
                ,""
                ,binding.telET.text.toString()
                ,binding.mailET.text.toString()
                ,binding.passwordET.text.toString()
                ,binding.passCFET.text.toString()

            )




        }


    }

    fun onserv(){

        viewModel.dataResp.observe(viewLifecycleOwner, Observer {
            val strings: List<String> = it.map { it.serviceName }
            val adapter = ArrayAdapter(
                requireContext(), R.layout.component_list_item, strings
            )
            binding.hosAutoCompleteTextView.setAdapter(adapter);

        })

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
                        .navigate(R.id.action_registerFragment_to_mainFragment)

                }


            }


        })

    }




}