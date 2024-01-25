package com.vk.salestestapp.Common

import com.vk.salestestapp.Network.APIService
import com.vk.salestestapp.Network.Retrofit


object Common {
    private const val BASE_URL ="https://us-central1-android-technical-task-eb823.cloudfunctions.net/"
    val getAPIService: APIService
        get() = Retrofit.getRetrofitClient(BASE_URL).create(APIService::class.java)
}