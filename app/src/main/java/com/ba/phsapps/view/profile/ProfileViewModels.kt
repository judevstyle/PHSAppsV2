package com.ba.phsapps.view.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.repository.LoginRepository
import com.ssoft.common.util.LogUtil
import com.taitos.nup.common.LiveEvent
import com.taitos.nup.common.MutableLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ProfileViewModels(val loginRepository: LoginRepository) : ViewModel() {


    private val _loginData = MutableLiveEvent<LoginUi>()
    val onLoginData: LiveEvent<LoginUi> = _loginData



    fun profile(uid:String){


//        val coroutineExceptionHanlder = CoroutineExceptionHandler { _, throwable ->
//
//            LogUtil.showLogError("err","${throwable.message}")
////            _loginData.setEventValue(LoginUiCall.onError(throwable))
//        }
//        viewModelScope.launch(coroutineExceptionHanlder) {
//
////            val request = ChatRequest(pid,null,"...",userTo!!.uid ?: "",3)
//            _loginData.setEventValue(LoginUi.onLoading)
//
//            val resp = loginRepository.login(user, pass)
//
//            if (resp.data == null)
//                _loginData.setEventValue(LoginUi.onValidate("ตรวจสอบ User หรือ Password"))
//            else
//                _loginData.setEventValue(LoginUi.onSuccess(resp.data))
//
//
////            textChat.value = ""
////
//            LogUtil.showLogError("auth",resp.toString())
////            add("https://growestate-assets.s3.ap-southeast-1.amazonaws.com/${data.data.path}")
//
//        }
    }


}

sealed class LoginUi {

    data class onSuccess(val data: UserModels) : LoginUi()
    data class onError(val throwable: Throwable) : LoginUi()
    data class onValidate(val msg: String) : LoginUi()

    object onLoading : LoginUi()

}
