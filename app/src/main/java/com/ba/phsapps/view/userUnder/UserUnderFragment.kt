package com.ba.phsapps.view.userUnder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ba.phsapps.R
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.databinding.FragmentUserUnderBinding
import com.ba.phsapps.view.medical.MedicalAdapter
import com.ba.phsapps.view.medical.ServiceCenterViewModel
import com.google.gson.Gson
import com.ssoft.common.BaseFragment
import com.ssoft.common.util.SharedPreferenceUtil
import org.koin.android.viewmodel.ext.android.viewModel

class UserUnderFragment:BaseFragment() {

    private lateinit var adapters: UserUnderAdapter
    val viewModel: UserUnderViewModel by viewModel()

    lateinit var binding:FragmentUserUnderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserUnderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        super.onViewReady(view, savedInstanceState)

        val str =  SharedPreferenceUtil.getUser(requireContext())
        val user = Gson().fromJson<UserModels>(str, UserModels::class.java)

        viewModel.getUnderUser("${user.uIDcard}")
        initView()
        initObserv()
    }

    private fun initObserv() {

        viewModel.dataResp.observe(viewLifecycleOwner, Observer {
            adapters.setItem(it)

        })


    }


    fun initView(){

        adapters = UserUnderAdapter(requireContext())
//        adapters.itemClickListener = this

        adapters.onClickListener = {event,data->

            when(event){

                EventUnder.History->{

                    val bundle = Bundle()
                    bundle.putString("uid",data.uIDcard)
                    findNavController()
                        .navigate(R.id.action_userUnderFragment_to_userHistoryFragment,bundle)
                }

                EventUnder.Add->{

                }


            }



        }

        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            isNestedScrollingEnabled = false
            adapter = adapters
        }

        binding.backAction.setOnClickListener {
            findNavController()
                .popBackStack()
        }

    }


}