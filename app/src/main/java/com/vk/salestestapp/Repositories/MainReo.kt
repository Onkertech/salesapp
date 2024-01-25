package com.vk.salestestapp.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vk.salestestapp.Common.Common
import com.vk.salestestapp.Model.ProductModel
import com.vk.salestestapp.Network.APIService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainReo {
    private val apiService: APIService
    companion object{
        private const val TAG ="MainReo"
    }
    init {
        apiService= Common.getAPIService
     }

    val getMovieModelLiveData:MutableLiveData<MutableList<ProductModel>>
        get() {
            val data:MutableLiveData<MutableList<ProductModel>> =
            MutableLiveData<MutableList<ProductModel>>()

            apiService.getMovieList().enqueue(object :
                Callback<MutableList<ProductModel>> {
                override fun onResponse(
                    call: Call<MutableList<ProductModel>>,
                    response: Response<MutableList<ProductModel>>
                ) {
                    Log.e(TAG,"onResponse:"+response.code())
                    if (response.isSuccessful){
                        data.value =response.body()
                    }else{
                        data!!.value=null
                    }
                }

                override fun onFailure(call: Call<MutableList<ProductModel>>, t: Throwable) {
                    Log.e(TAG,"onFailure:"+t.message)
                    data!!.value=null
                }

            })
            return data
        }

}