package com.ba.phsapps.repository

import com.ba.phsapps.data.CallbackRespone
import com.ba.phsapps.data.models.HealthHistory
import com.ba.phsapps.data.models.ServiceCenter
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.network.api.ServiceAPI
import retrofit2.http.Path

interface UserHistoryRepository {

   suspend fun getHistoryByUser(id:String):CallbackRespone<List<HealthHistory>>

    suspend fun healthHistoryAll(
    ):CallbackRespone<List<ServiceCenter>>

    suspend fun serviceCenter(
    ):CallbackRespone<List<ServiceCenter>>

    suspend fun getServiceUnderUser(
        @Path("id")id:String,
    ):CallbackRespone<List<UserModels>>


}

class UserHistoryImpl(val serviceAPI: ServiceAPI):UserHistoryRepository{
    override suspend fun getHistoryByUser(id: String): CallbackRespone<List<HealthHistory>> {
        return serviceAPI.searchHistory(id)
    }

    override suspend fun healthHistoryAll(): CallbackRespone<List<ServiceCenter>> {
        return serviceAPI.healthHistoryAll()
    }

    override suspend fun serviceCenter(): CallbackRespone<List<ServiceCenter>> {
        return serviceAPI.serviceCenter()
    }

    override suspend fun getServiceUnderUser(id: String): CallbackRespone<List<UserModels>> {

        return serviceAPI.getServiceUnderUser(id)
    }


}
