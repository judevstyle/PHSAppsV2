package com.ba.phsapps.view.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ba.phsapps.data.models.ServiceCenter
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.repository.LoginRepository
import com.ba.phsapps.view.login.LoginUi
import com.ssoft.common.util.LogUtil
import com.taitos.nup.common.LiveEvent
import com.taitos.nup.common.MutableLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class RegisterViewModels(val loginRepository: LoginRepository) : ViewModel() {

    val dataResp = MutableLiveData<List<ServiceCenter>>()


    private val _loginData = MutableLiveEvent<LoginUi>()
    val onLoginData: LiveEvent<LoginUi> = _loginData

    init {
        getService()
    }

    fun register(
        uIDCard: String,
        userviceCenterID: Int,
        uRole: Int,
        uTitle: Int,
        uName: String,
        uLName: String,
        uAddress: String,
        uPosition: String,
        uSex: Int,
        uMobile: String,
        uPhone: String,
        mail: String,
        pass: String,
        passCF: String,
        age: String

    ) {


        val coroutineExceptionHanlder = CoroutineExceptionHandler { _, throwable ->

            LogUtil.showLogError("err", "${throwable.message}")
//            _loginData.setEventValue(LoginUiCall.onError(throwable))
        }
        viewModelScope.launch(coroutineExceptionHanlder) {

//            val request = ChatRequest(pid,null,"...",userTo!!.uid ?: "",3)

            if (uRole == -1)
                _loginData.setEventValue(LoginUi.onValidate("เลือกประเภทผู้ใช้งาน"))
           else if (userviceCenterID == -1)
                _loginData.setEventValue(LoginUi.onValidate("เลือกสถานพยาบาล"))
           else if (uIDCard.isEmpty())
                _loginData.setEventValue(LoginUi.onValidate("ระบุเลขบัตรประชาชน"))
            if (uTitle == -1)
                _loginData.setEventValue(LoginUi.onValidate("เลือกคำนำหน้า"))

            else if (uName.isEmpty())
                _loginData.setEventValue(LoginUi.onValidate("ระบุชื่อ"))
            else if (uLName.isEmpty())
                _loginData.setEventValue(LoginUi.onValidate("ระบุนามสกุล"))
            if (uSex == -1)
                _loginData.setEventValue(LoginUi.onValidate("เลือกเพศ"))
            else if (age.isEmpty())
                _loginData.setEventValue(LoginUi.onValidate("ระบุอายุ"))
            else if (uPhone.isEmpty())
                _loginData.setEventValue(LoginUi.onValidate("ระบุเบอร์โทรติดต่อ"))
            else if (uAddress.isEmpty())
                _loginData.setEventValue(LoginUi.onValidate("ระบุที่อยู่"))
            else if (pass.length < 1) {
                _loginData.setEventValue(LoginUi.onValidate("ระบุรหัสผ่าน"))
            } else if (!pass.equals(passCF)) {
                _loginData.setEventValue(LoginUi.onValidate("ตรวจสอบรหัสผ่านยืนยัน"))
            } else {
                _loginData.setEventValue(LoginUi.onLoading)

                val resp = loginRepository.register(uIDCard,"${dataResp.value!!.get(userviceCenterID).id}"
                ,"${uRole+1}"
                    ,"${uTitle+1}"
                    ,uName+" "+uLName
                    ,uAddress.toString()
                    ,uPosition.toString()
                    ,"${uSex+1}",age
                    ,uMobile
                    ,uPhone.toString()
                    ,mail
                    ,pass
                )

                if (resp.data != null)
                    _loginData.setEventValue(LoginUi.onSuccess(resp.data))
                else
                    _loginData.setEventValue(LoginUi.onValidate("เกิดข้อผิดพลาด"))



//            textChat.value = ""
//
                LogUtil.showLogError("auth", resp.toString())
//            add("https://growestate-assets.s3.ap-southeast-1.amazonaws.com/${data.data.path}")
            }
        }
    }



    fun updateProfile(
        uIDCard: String,
        userviceCenterID: Int,
        uRole: Int,
        uTitle: Int,
        uName: String,
        uAddress: String,
        uPosition: String,
        uSex: Int,
        uMobile: String,
        uPhone: String,
        mail: String,
        age: String,pass:String

    ) {


        val coroutineExceptionHanlder = CoroutineExceptionHandler { _, throwable ->

            LogUtil.showLogError("err", "${throwable.message}")
//            _loginData.setEventValue(LoginUiCall.onError(throwable))
        }
        viewModelScope.launch(coroutineExceptionHanlder) {

//            val request = ChatRequest(pid,null,"...",userTo!!.uid ?: "",3)

            if (uRole == -1)
                _loginData.setEventValue(LoginUi.onValidate("เลือกประเภทผู้ใช้งาน"))
            else if (userviceCenterID == -1)
                _loginData.setEventValue(LoginUi.onValidate("เลือกสถานพยาบาล"))
            else if (uIDCard.isEmpty())
                _loginData.setEventValue(LoginUi.onValidate("ระบุเลขบัตรประชาชน"))
            if (uTitle == -1)
                _loginData.setEventValue(LoginUi.onValidate("เลือกคำนำหน้า"))
            else if (uName.isEmpty())
                _loginData.setEventValue(LoginUi.onValidate("ระบุชื่อ"))
            if (uSex == -1)
                _loginData.setEventValue(LoginUi.onValidate("เลือกเพศ"))
            else if (age.isEmpty())
                _loginData.setEventValue(LoginUi.onValidate("ระบุอายุ"))
            else if (uPhone.isEmpty())
                _loginData.setEventValue(LoginUi.onValidate("ระบุเบอร์โทรติดต่อ"))
            else if (uAddress.isEmpty())
                _loginData.setEventValue(LoginUi.onValidate("ระบุที่อยู่"))
            else {
                _loginData.setEventValue(LoginUi.onLoading)

                val resp = loginRepository.profileUpdate(uIDCard,"${dataResp.value!!.get(userviceCenterID).id}"
                    ,"${uRole+1}"
                    ,"${uTitle+1}"
                    ,uName
                    ,uAddress.toString()
                    ,uPosition.toString()
                    ,"${uSex+1}",age
                    ,uMobile
                    ,uPhone.toString()
                    ,mail,pass
                )

                if (resp.data != null)
                    _loginData.setEventValue(LoginUi.onSuccess(resp.data))
                else
                    _loginData.setEventValue(LoginUi.onValidate("เกิดข้อผิดพลาด"))



//            textChat.value = ""
//
                LogUtil.showLogError("auth", resp.toString())
//            add("https://growestate-assets.s3.ap-southeast-1.amazonaws.com/${data.data.path}")
            }
        }
    }



    fun getService() {

        val coroutineExceptionHanlder = CoroutineExceptionHandler { _, throwable ->

//            LogUtil.showLogError("err","${throwable.message}")
//            _loginData.setEventValue(LoginUiCall.onError(throwable))
        }
        viewModelScope.launch(coroutineExceptionHanlder) {

//            val request = ChatRequest(pid,null,"...",userTo!!.uid ?: "",3)
//            _loginData.setEventValue(LoginUi.onLoading)

            val resp = loginRepository.serviceCenter()

            dataResp.value = resp.data ?: ArrayList()

//            textChat.value = ""
//
            LogUtil.showLogError("userrrr", resp.toString())
//            add("https://growestate-assets.s3.ap-southeast-1.amazonaws.com/${data.data.path}")

        }


    }


}

