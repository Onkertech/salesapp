package com.vk.salestestapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vk.salestestapp.Model.ProductModel
import com.vk.salestestapp.Repositories.MainReo


class MainActivityViewModel:ViewModel() {
    private val mainReo: MainReo

    val getMovieList:LiveData<MutableList<ProductModel>>
        get() = mainReo.getMovieModelLiveData

    init {
          mainReo = MainReo()
        }
   }