package tour.traveling.travel.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import network.AppRetrofit
import tour.traveling.travel.model.response.OTPGenerateResponce
import retrofit2.Call
import retrofit2.Callback
import tour.traveling.travel.model.response.RegistrationResponce

class SignupReposatory {

    fun callOTP_Api(mobile: String): LiveData<OTPGenerateResponce> {
        val data = MutableLiveData<OTPGenerateResponce>()
        AppRetrofit.instance.callOTP_Api(mobile).enqueue(object :
            Callback<OTPGenerateResponce> {
            override fun onFailure(call: Call<OTPGenerateResponce>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<OTPGenerateResponce>,
                response: retrofit2.Response<OTPGenerateResponce>
            ) {

                if (response.isSuccessful){
                    data.value = if (response != null && response.body() != null) response!!.body() else null
                }
                else {
                    val gson = Gson()
                    val adapter = gson.getAdapter(OTPGenerateResponce::class.java)
                    if (response.errorBody() != null)
                        data.value = adapter.fromJson(response.errorBody()!!.string())
                }
            }
        })

        return data
    }


    fun callSignUp(user_name:String,mobile: String,user_mobile_otp:String,user_password:String,email:String): LiveData<RegistrationResponce> {
        val data = MutableLiveData<RegistrationResponce>()
        AppRetrofit.instance.callSignUp(user_name,mobile,user_mobile_otp,user_password,email).enqueue(object :
            Callback<RegistrationResponce> {
            override fun onFailure(call: Call<RegistrationResponce>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<RegistrationResponce>,
                response: retrofit2.Response<RegistrationResponce>
            ) {

                if (response.isSuccessful){
                    data.value = if (response != null && response.body() != null) response!!.body() else null
                }
                else {
                    val gson = Gson()
                    val adapter = gson.getAdapter(RegistrationResponce::class.java)
                    if (response.errorBody() != null)
                        data.value = adapter.fromJson(response.errorBody()!!.string())
                }
            }
        })

        return data
    }

}