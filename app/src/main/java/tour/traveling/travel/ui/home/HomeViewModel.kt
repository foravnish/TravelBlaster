package com.accountapp.accounts.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tour.traveling.travel.model.response.BannerListingResponce
import tour.traveling.travel.model.response.CategoryResponce
import tour.traveling.travel.model.response.HotPlaceResponce
import tour.traveling.travel.model.response.OTPGenerateResponce

class HomeViewModel : ViewModel(){
    lateinit var mRepo: HomeReposatory

    init {
        mRepo = HomeReposatory()
    }

    fun callBannerListing(): LiveData<BannerListingResponce> {
        return mRepo.callBannerListing()
    }

    fun callHotPlaces(): LiveData<HotPlaceResponce> {
        return mRepo.callHotPlaces()
    }

    fun callCategory(): LiveData<CategoryResponce> {
        return mRepo.callCategory()
    }



}