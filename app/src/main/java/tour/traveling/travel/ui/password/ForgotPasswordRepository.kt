package com.mooveall.ui.forgotPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import network.AppRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Arashjit Singh on 2/4/18.
 */
class ForgotPasswordRepository {
//    fun getForgotPasswordOTP(req: LoginReq): LiveData<SignUpResponse> {
//        val data = MutableLiveData<SignUpResponse>()
//        AppRetrofit.instance.forgotPassword(req).enqueue(object : Callback<SignUpResponse> {
//            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
//                data.value = null
//            }
//
//            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
//                data.value = if (response != null && response.body() != null) response!!.body() else null
//            }
//        })
//
//        return data
//    }
}