package com.ba.phsapps.network.api

import com.ba.phsapps.data.CallbackRespone
import com.ba.phsapps.data.models.Health
import com.ba.phsapps.data.models.HealthHistory
import com.ba.phsapps.data.models.ServiceCenter
import com.ba.phsapps.data.models.UserModels
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceAPI {

    @GET("User/Login/{user}/{pass}")
    suspend fun login(
        @Path("user")user:String,
        @Path("pass")pass:String
        ): CallbackRespone<UserModels>

    @GET("User/SearchUser/{user}")
    suspend fun searchUserIDcard(
        @Path("user")user:String,
    ): UserModels

    @GET("User/UserAll")
    suspend fun userAll(
        @Path("user")user:String,
    ): List<UserModels>

    @GET("User/GetServiceUser/{id}")
    suspend fun getServiceUser(
        @Path("id")id:Int,
    ): List<UserModels>

    @GET("User/GetUserforHealth/{id}")
    suspend fun getServiceUnderUser(
        @Path("id")id:String,
    ):CallbackRespone<List<UserModels>>

    @GET("HealthHistory/SearchHistory/{search}")
    suspend fun searchHistory(
        @Path("search")search:String,
    ): CallbackRespone<List<HealthHistory>>





    @GET("HealthHistory/HistoryAll")
    suspend fun healthHistoryAll(
    ):CallbackRespone<List<ServiceCenter>>

    @GET("ServiceCenter/HealthAll")
    suspend fun serviceCenter(
    ):CallbackRespone<List<ServiceCenter>>


    @FormUrlEncoded
    @POST("User/ResetPassword")
    suspend fun resetPassword(
        @Field("uIDcard")uIDcard:String,
        @Field("uPass")uPass:String
        ):CallbackRespone<UserModels>


    @FormUrlEncoded
    @POST("User/Register")
    suspend fun register(
        @Field("uIDCard")uIDCard:String,
        @Field("userviceCenterID")userviceCenterID:String,
        @Field("uRole")uRole:String,
        @Field("uTitle")uTitle:String,
        @Field("uName")uName:String,
        @Field("uAddress")uAddress:String,
        @Field("uPosition")uPosition:String,
        @Field("uSex")uSex:String,
        @Field("uMobile")uMobile:String,
        @Field("uPhone")uPhone:String,
        @Field("uMail")uMail:String,
        @Field("uPass")uPass:String,
        ):CallbackRespone<UserModels>


    @POST("HealthHistory/Save")
    suspend fun healthCreate(
        @Field("idCard")idCard:String,
        @Field("healthPresSure")healthPresSure:String,
        @Field("healthPulse")healthPulse:String,
        @Field("height")height:String,
        @Field("weight")weight:String,
        @Field("serviceCenterID")serviceCenterID:String,
        @Field("healtColor")healtColor:String,
        @Field("createBy")createBy:String
        ): List<HealthHistory>

}