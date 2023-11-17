package com.ba.phsapps.view.profileUpdate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ba.phsapps.R
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.databinding.FragmentProfileUpdateBinding
import com.ba.phsapps.view.login.LoginUi
import com.ba.phsapps.view.register.RegisterViewModels
import com.google.gson.Gson
import com.ssoft.common.BaseFragment
import com.ssoft.common.util.LogUtil
import com.ssoft.common.util.SharedPreferenceUtil
import com.taitos.nup.common.EventObserver
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileUpdateFragment : BaseFragment() {

    val viewModel: RegisterViewModels by viewModel()

    var titleIndex = -1
    var typeIndex = -1
    var hosIndex = -1
    var genderIndex = -1
    lateinit var binding: FragmentProfileUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        super.onViewReady(view, savedInstanceState)
        onserv()

        initView()
    }

    fun onserv() {
        val str = SharedPreferenceUtil.getUser(requireContext())

        val user = Gson().fromJson<UserModels>(str, UserModels::class.java)

        viewModel.dataResp.observe(viewLifecycleOwner, Observer {
            val strings: List<String> = it.map { it.serviceName }


            var index = 0
            for (item in it) {

                if (user.userviceCenterID == item.id) {
                    hosIndex = index
                    binding.hosAutoCompleteTextView.setText(item.serviceName ?: "")
                    break
                }
                index++
            }


            val adapter = ArrayAdapter(
                requireContext(), R.layout.component_list_item, strings
            )
            binding.hosAutoCompleteTextView.setAdapter(adapter);

        })

//        binding.hosAutoCompleteTextView
//            .setOnItemClickListener(object :
//                AdapterView.OnItemClickListener {
//                override fun onItemClick(
//                    p0: AdapterView<*>?,
//                    p1: View?,
//                    p2: Int,
//                    p3: Long
//                ) {
//                    hosIndex = p2
//
//                }
//
//            });


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
                    showToast("บันทึกการเปลี่ยนแปลง")

                    SharedPreferenceUtil.updateUser(requireContext(), Gson().toJson(state.data))
                    hideDialog()
                    findNavController()
                        .popBackStack()

                }


            }


        })

    }

    private fun initView() {

        val gender = arrayListOf<String>("ชาย", "หญิง")
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

        val title = arrayListOf<String>("นาย", "นาง", "นางสาว")
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

        val type = arrayListOf<String>("อาสาสมัครสาธารณสุข", "ผู้สูงวัย")
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

        val str = SharedPreferenceUtil.getUser(requireContext())

        val user = Gson().fromJson<UserModels>(str, UserModels::class.java)


        try {
            LogUtil.showLogError("sjps", "${user.toString()}")
            typeIndex = user.uRole - 1
            titleIndex = user.uTitle - 1
            genderIndex = user.uSex - 1

            binding.typeAutoCompleteTextView.setText("${type.get(user.uRole - 1)}")
            binding.titleAutoCompleteTextView.setText("${title.get(user.uTitle - 1)}")
            binding.genderAutoCompleteTextView.setText("${gender.get(user.uSex - 1)}")

            binding.apply {
                idCardET.setText("${user.uIDcard}")
                fnameET.setText("${user.uName}")
                addressET.setText("${user.uAddress}")
                telET.setText("${user.uPhone}")
                mailET.setText("${user.uMail}")
                ageET.setText("${user.uAge ?: 0}")
            }


            binding.registerAction.setOnClickListener {

                viewModel.updateProfile(
                    binding.idCardET.text.toString(),
                    hosIndex,
                    typeIndex,
                    titleIndex,
                    binding.fnameET.text.toString(),
                    binding.addressET.text.toString(),
                    "",
                    genderIndex,
                    "",
                    binding.telET.text.toString(),
                    binding.mailET.text.toString(),
                    binding.ageET.text.toString(),user.uPass ?: "1234"
                )

            }


        } catch (ex: Exception) {
            showToast("เกิดข้อผิดพลาด")
        }


    }


}