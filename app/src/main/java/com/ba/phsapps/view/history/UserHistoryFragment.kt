package com.ba.phsapps.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.databinding.FragmentHistoryBinding
import com.ba.phsapps.databinding.FragmentUserHistoryBinding
import com.ba.phsapps.view.login.LoginViewModels
import com.google.gson.Gson
import com.ssoft.common.BaseFragment
import com.ssoft.common.util.SharedPreferenceUtil
import org.koin.android.viewmodel.ext.android.viewModel

class UserHistoryFragment:BaseFragment() {

    private lateinit var adapters: UserHistoryAdapterAdapter
    lateinit var binding: FragmentHistoryBinding
    val viewModel: UserHistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        super.onViewReady(view, savedInstanceState)


        arguments?.let {

            it.getString("uid")?.let {
                showToast("aaaa")
                viewModel.getHistoryByUser("${it}")

            }?: kotlin.run {
                showToast("bbbb")

                val str =  SharedPreferenceUtil.getUser(requireContext())
                val user = Gson().fromJson<UserModels>(str, UserModels::class.java)
                viewModel.getHistoryByUser("${user.uIDcard}")

            }

        }?: kotlin.run {
            val str =  SharedPreferenceUtil.getUser(requireContext())
            val user = Gson().fromJson<UserModels>(str, UserModels::class.java)
            viewModel.getHistoryByUser("${user.uIDcard}")

        }


        initView()
        initObserv()
    }

    private fun initObserv() {

        viewModel.dataResp.observe(viewLifecycleOwner, Observer {
            adapters.setItem(it)


        })


    }


    fun initView(){

        adapters = UserHistoryAdapterAdapter(requireContext())
//        adapters.itemClickListener = this

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