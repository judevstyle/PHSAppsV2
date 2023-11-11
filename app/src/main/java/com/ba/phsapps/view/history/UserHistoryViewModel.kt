package com.ba.phsapps.view.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ba.phsapps.data.models.HealthHistory
import com.ba.phsapps.repository.UserHistoryRepository
import com.ba.phsapps.view.login.LoginUi
import com.ssoft.common.util.LogUtil
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class UserHistoryViewModel(val userHistoryRepository: UserHistoryRepository):ViewModel() {



    val dataResp = MutableLiveData<List<HealthHistory>>()

    fun getHistoryByUser(id: String) {

        val coroutineExceptionHanlder = CoroutineExceptionHandler { _, throwable ->

//            LogUtil.showLogError("err","${throwable.message}")
//            _loginData.setEventValue(LoginUiCall.onError(throwable))
        }
        viewModelScope.launch(coroutineExceptionHanlder) {

//            val request = ChatRequest(pid,null,"...",userTo!!.uid ?: "",3)
//            _loginData.setEventValue(LoginUi.onLoading)

            val resp = userHistoryRepository.getHistoryByUser(id)

            dataResp.value = resp.data ?: ArrayList()

//            textChat.value = ""
//
            LogUtil.showLogError("userrrr", resp.toString())
//            add("https://growestate-assets.s3.ap-southeast-1.amazonaws.com/${data.data.path}")

        }


    }


}