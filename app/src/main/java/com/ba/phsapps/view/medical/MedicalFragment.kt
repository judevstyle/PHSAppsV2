package com.ba.phsapps.view.medical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.ba.phsapps.databinding.FragmentHistoryBinding
import com.ba.phsapps.databinding.FragmentMedicalBinding
import com.ba.phsapps.databinding.FragmentUserHistoryBinding
import com.ba.phsapps.view.login.LoginViewModels
import com.ssoft.common.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MedicalFragment:BaseFragment() {

    private lateinit var adapters: MedicalAdapter
    lateinit var binding: FragmentMedicalBinding
    val viewModel: ServiceCenterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMedicalBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        super.onViewReady(view, savedInstanceState)
        viewModel.getHistoryByUser("1012300448789")
        initView()
        initObserv()
    }

    private fun initObserv() {

        viewModel.dataResp.observe(viewLifecycleOwner, Observer {
            adapters.setItem(it)


        })


    }


    fun initView(){

        adapters = MedicalAdapter(requireContext())
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