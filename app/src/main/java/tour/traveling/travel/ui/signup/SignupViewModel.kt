package tour.traveling.travel.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tour.traveling.travel.model.response.OTPGenerateResponce
import tour.traveling.travel.model.response.RegistrationResponce

class SignupViewModel: ViewModel() {

    lateinit var mRepo: SignupReposatory

    init {
        mRepo = SignupReposatory()
    }


    fun callOTP_Api(mobile: String): LiveData<OTPGenerateResponce> {
        return mRepo.callOTP_Api(mobile)
    }

    fun callSignUp(user_name:String,mobile: String,user_mobile_otp:String,user_password:String,email:String): LiveData<RegistrationResponce> {
        return mRepo.callSignUp(user_name,mobile,user_mobile_otp,user_password,email)
    }


}