package com.mooveall.ui.forgotPassword

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


/**
 * Created by Arashjit Singh on 28/2/18.
 */
class ForgotPasswordViewModel : ViewModel() {

    private var handler: ForgotPasswordHandler? = null
    lateinit var loginRepo: ForgotPasswordRepository

    init {
        loginRepo = ForgotPasswordRepository()
    }


    internal fun setHandler(handler: ForgotPasswordHandler) {
        this.handler = handler
    }

    fun onSubmitClicked(view: View) {
        handler!!.onSubmitClicked()
    }

//    fun forgotPassword(reqBean: LoginReq): LiveData<SignUpResponse> {
//        return loginRepo.getForgotPasswordOTP(reqBean)
//    }

}