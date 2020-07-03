package com.mooveall.ui.fragment.settings

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


/**
 * Created by Arashjit Singh on 28/2/18.
 */
class DriverSettingViewModel : ViewModel() {

    private var handler: DriverSettingsHandler? = null
    lateinit var mRepo: SettingsRepository

    internal fun setHandler(handler: DriverSettingsHandler) {
        this.handler = handler
    }

    init {
        mRepo = SettingsRepository()
    }

//    fun logoutUser(reqBean: VerifyOtpReq): LiveData<SignUpResponse> {
//        return mRepo.logoutUser(reqBean)
//    }

    fun onProfileClicked(view: View) {
        handler!!.onProfileClicked()
    }

    fun onPaymentMethodClicked(view: View) {
        handler!!.onPaymentMethodClicked()
    }

    fun changePassword(view: View) {
        handler!!.changePassword()
    }

    fun review(view: View) {
        handler!!.review()
    }

    fun giveFeedback(view: View) {
        handler!!.giveFeedback()
    }

    fun gotoTermsConditions(view: View) {
        handler!!.gotoTermsConditions()
    }

    fun aboutUs(view: View) {
        handler!!.aboutUs()
    }

    fun logout(view: View) {
        handler!!.logout()
    }


}