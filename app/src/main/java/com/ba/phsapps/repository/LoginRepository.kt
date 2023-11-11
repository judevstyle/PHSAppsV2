package com.ba.phsapps.repository

import com.ba.phsapps.data.CallbackRespone
import com.ba.phsapps.data.models.ServiceCenter
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.network.api.ServiceAPI
import retrofit2.http.Field

interface LoginRepository {

    suspend fun login(user: String, pass: String): CallbackRespone<UserModels>

    suspend fun resetPassword(
        uIDcard: String,
        uPass: String
    ): CallbackRespone<UserModels>


    suspend fun register(
        uIDCard: String,
        userviceCenterID: String,
        uRole: String,
        uTitle: String,
        uName: String,
        uAddress: String,
        uPosition: String,
        uSex: String,
        uMobile: String,
        uPhone: String,
        uMail: String,
        uPass: String,
    ): CallbackRespone<UserModels>

    suspend fun serviceCenter(
    ):CallbackRespone<List<ServiceCenter>>
}

class LoginImpl(val serviceAPI: ServiceAPI) : LoginRepository {
    override suspend fun login(user: String, pass: String): CallbackRespone<UserModels> {
        return serviceAPI.login(user, pass)
    }

    override suspend fun resetPassword(
        uIDcard: String,
        uPass: String
    ): CallbackRespone<UserModels> {
        return serviceAPI.resetPassword(uIDcard, uPass)
    }

    override suspend fun register(
        uIDCard: String,
        userviceCenterID: String,
        uRole: String,
        uTitle: String,
        uName: String,
        uAddress: String,
        uPosition: String,
        uSex: String,
        uMobile: String,
        uPhone: String,
        uMail: String,
        uPass: String
    ): CallbackRespone<UserModels> {
        return serviceAPI.register(uIDCard, userviceCenterID, uRole, uTitle, uName, uAddress, uPosition, uSex, uMobile, uPhone, uMail, uPass)
    }

    override suspend fun serviceCenter(): CallbackRespone<List<ServiceCenter>> {
        return serviceAPI.serviceCenter()

    }


}
