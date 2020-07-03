package com.mooveall.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tour.traveling.travel.model.response.BookingHistoryResponse
import tour.traveling.travel.model.response.HotPlaceResponce
import tour.traveling.travel.model.response.LoginResponce
import tour.traveling.travel.model.response.ResultItemHistory
import tour.traveling.travel.ui.fragments.tablayout.FindJobsRepository

class FindJobsViewModel : ViewModel() {

    lateinit var mRepo: FindJobsRepository

    init {
        mRepo = FindJobsRepository()
    }

    fun callHistory(user_id:String): LiveData<BookingHistoryResponse> {
        return mRepo.callHistory(user_id)
    }
//
//    fun rateDriver(reqBean: RateDriverReq): LiveData<LoginResponse> {
//        return mRepo.rateDriver(reqBean)
//    }

}