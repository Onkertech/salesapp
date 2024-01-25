package com.vk.salestestapp.Network

import com.vk.salestestapp.Model.ProductModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @GET("getInventory")
    fun getMovieList(): Call<MutableList<ProductModel>>



}