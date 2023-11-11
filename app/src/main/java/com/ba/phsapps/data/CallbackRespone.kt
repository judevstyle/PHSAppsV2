package com.ba.phsapps.data


data class CallbackRespone<T>(
    val `data`: T,
    val statusCode: Int,
    val message:String
)