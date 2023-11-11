package com.ba.phsapps.view.userUnder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ba.phsapps.data.models.HealthHistory
import com.ba.phsapps.data.models.ServiceCenter
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.repository.UserHistoryRepository
import com.ba.phsapps.view.login.LoginUi
import com.ssoft.common.util.LogUtil
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class UserUnderViewModel(val userHistoryRepository: UserHistoryRepository):ViewModel() {



    val dataResp = MutableLiveData<List<UserModels>>()

    fun getUnderUser(id: String) {

        val coroutineExceptionHanlder = CoroutineExceptionHandler { _, throwable ->

//            LogUtil.showLogError("err","${throwable.message}")
//            _loginData.setEventValue(LoginUiCall.onError(throwable))
        }
        viewModelScope.launch(coroutineExceptionHanlder) {

            val resp = userHistoryRepository.getServiceUnderUser(id)

            dataResp.value = resp.data ?: ArrayList()
//

        }


    }


}