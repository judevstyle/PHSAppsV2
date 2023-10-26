package com.ba.phsapps.data.models

data class HealthHistory(
    val createBy: String,
    val createDate: Any,
    val healtColor: String,
    val healthPresSure: String,
    val healthPulse: Int,
    val height: Int,
    val id: Int,
    val idCard: String,
    val modifyBy: Any,
    val modifyDate: Any,
    val serviceCenterID: Int,
    val weight: Int
)