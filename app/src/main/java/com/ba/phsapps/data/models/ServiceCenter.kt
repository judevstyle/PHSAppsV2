package com.ba.phsapps.data.models

data class ServiceCenter(
    val createBy: String,
    val createDate: String,
    val id: Int,
    val modifyBy: String,
    val modifyDate: String,
    val serviceAddress: String,
    val serviceName: String,
    val servicePhone: String,
    val serviceRemark: String
)