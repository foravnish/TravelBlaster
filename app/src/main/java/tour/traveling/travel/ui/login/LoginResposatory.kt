package tour.traveling.travel.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import network.AppRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tour.traveling.travel.model.response.LoginResponce

class LoginResposatory  {
    fun callLogin(phone_number: String, password: String): LiveData<LoginResponce> {
        val data = MutableLiveData<LoginResponce>()
        AppRetrofit.instance.callLogin(phone_number, password).enqueue(object : Callback<LoginResponce> {
            override fun onFailure(call: Call<LoginResponce>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<LoginResponce>, response: Response<LoginResponce>) {

                if (response.isSuccessful){
                    data.value = if (response != null && response.body() != null) response!!.body() else null
                }
                else {
                    val gson = Gson()
                    val adapter = gson.getAdapter(LoginResponce::class.java)
                    if (response.errorBody() != null)
                        data.value = adapter.fromJson(response.errorBody()!!.string())
                }
            }
        })

        return data
    }



//    fun callForgotPassword(phone_number: String): LiveData<LoginResponse> {
//        val data = MutableLiveData<LoginResponse>()
//        AppRetrofit.instance.callForgotPassword(phone_number).enqueue(object : Callback<LoginResponse> {
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                data.value = null
//            }
//
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//
//                if (response.isSuccessful){
//                    data.value = if (response != null && response.body() != null) response!!.body() else null
//                }
//                else {
//                    val gson = Gson()
//                    val adapter = gson.getAdapter(LoginResponse::class.java)
//                    if (response.errorBody() != null)
//                        data.value = adapter.fromJson(response.errorBody()!!.string())
//                }
//            }
//        })
//
//        return data
//    }

}