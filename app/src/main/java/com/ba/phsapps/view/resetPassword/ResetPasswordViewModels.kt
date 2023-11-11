package com.ba.phsapps.view.resetPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.repository.LoginRepository
import com.ba.phsapps.view.login.LoginUi
import com.google.gson.Gson
import com.ssoft.common.util.LogUtil
import com.ssoft.common.util.SharedPreferenceUtil
import com.taitos.nup.common.LiveEvent
import com.taitos.nup.common.MutableLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ResetPasswordViewModels(val loginRepository: LoginRepository) : ViewModel() {


    private val _loginData = MutableLiveEvent<LoginUi>()
    val onLoginData: LiveEvent<LoginUi> = _loginData



    fun resetPass(id:String,pass:String,passCF:String){



        val coroutineExceptionHanlder = CoroutineExceptionHandler { _, throwable ->

            LogUtil.showLogError("err","${throwable.message}")
//            _loginData.setEventValue(LoginUiCall.onError(throwable))
        }
        viewModelScope.launch(coroutineExceptionHanlder) {

//            val request = ChatRequest(pid,null,"...",userTo!!.uid ?: "",3)


            if (pass.length < 1){
                _loginData.setEventValue(LoginUi.onValidate("ระบุรหัสผ่านใหม่"))

            }
            else if (!pass.equals(passCF)){
                _loginData.setEventValue(LoginUi.onValidate("ตรวจสอบรหัสผ่านยืนยัน"))

            }else{

                _loginData.setEventValue(LoginUi.onLoading)


                val resp = loginRepository.resetPassword(id, pass)

                if (resp.data == null)
                    _loginData.setEventValue(LoginUi.onValidate("ตรวจสอบ User หรือ Password"))
                else
                    _loginData.setEventValue(LoginUi.onSuccess(resp.data))


//            textChat.value = ""
//
                LogUtil.showLogError("auth",resp.toString())

            }



//            add("https://growestate-assets.s3.ap-southeast-1.amazonaws.com/${data.data.path}")

        }
    }


}

