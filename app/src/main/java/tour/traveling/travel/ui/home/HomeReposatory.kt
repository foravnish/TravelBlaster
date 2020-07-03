package com.accountapp.accounts.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import network.AppRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tour.traveling.travel.model.response.BannerListingResponce
import tour.traveling.travel.model.response.CategoryResponce
import tour.traveling.travel.model.response.HotPlaceResponce
import tour.traveling.travel.model.response.OTPGenerateResponce

class HomeReposatory {

    fun callBannerListing(): LiveData<BannerListingResponce> {
        val data = MutableLiveData<BannerListingResponce>()
        AppRetrofit.instance.callBannerListing().enqueue(object :
            Callback<BannerListingResponce> {
            override fun onFailure(call: Call<BannerListingResponce>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<BannerListingResponce>, response: Response<BannerListingResponce>) {
                data.value = if (response != null && response.body() != null) response.body() else null

            }
        })

        return data
    }

// Hot Places
    fun callHotPlaces(): LiveData<HotPlaceResponce> {
        val data = MutableLiveData<HotPlaceResponce>()
        AppRetrofit.instance.callHotPlaces().enqueue(object :
            Callback<HotPlaceResponce> {
            override fun onFailure(call: Call<HotPlaceResponce>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<HotPlaceResponce>, response: Response<HotPlaceResponce>) {
                data.value = if (response != null && response.body() != null) response.body() else null

            }
        })

        return data
    }


    // Category List
    fun callCategory(): LiveData<CategoryResponce> {
        val data = MutableLiveData<CategoryResponce>()
        AppRetrofit.instance.callCategory().enqueue(object :
            Callback<CategoryResponce> {
            override fun onFailure(call: Call<CategoryResponce>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<CategoryResponce>, response: Response<CategoryResponce>) {
                data.value = if (response != null && response.body() != null) response.body() else null

            }
        })

        return data
    }

}