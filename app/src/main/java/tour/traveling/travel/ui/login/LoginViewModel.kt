package com.accountapp.accounts.ui.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tour.traveling.travel.model.response.LoginResponce
import tour.traveling.travel.ui.login.LoginHnadelar
import tour.traveling.travel.ui.login.LoginResposatory

class LoginViewModel: ViewModel() {
    lateinit var mRepo: LoginResposatory
    private var handler: LoginHnadelar? = null
    init {
        mRepo = LoginResposatory()
    }

    internal fun setHandler(handler: LoginHnadelar) {
        this.handler = handler
    }
    fun onFacebookClicked(view: View){
        this.handler!!.onFacebookClick()
    }

    fun onGmail(view: View){
        this.handler!!.onGmail()
    }

    fun callLogin(phone_no: String,pass:String): LiveData<LoginResponce> {
        return mRepo.callLogin(phone_no,pass)
    }
//
//    fun callForgotPassword(phone_no: String): LiveData<LoginResponse> {
//        return mRepo.callForgotPassword(phone_no)
//    }

}