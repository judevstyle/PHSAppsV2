package com.ba.phsapps.network.api

import com.ba.phsapps.data.models.Health
import com.ba.phsapps.data.models.HealthHistory
import com.ba.phsapps.data.models.UserModels
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceAPI {

    @GET("User/Login/{user}/{pass}")
    suspend fun login(
        @Path("user")user:String,
        @Path("pass")pass:String
        ): UserModels

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
    suspend fun getServiceUser(
        @Path("id")id:String,
    ): List<UserModels>

    @GET("HealthHistory/SearchHistory/{search}")
    suspend fun searchHistory(
        @Path("search")search:String,
    ): List<UserModels>


    @GET("HealthHistory/HistoryAll")
    suspend fun healthHistoryAll(
    ): List<HealthHistory>

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