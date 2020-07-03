package tour.traveling.travel.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import network.AppRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tour.traveling.travel.model.response.CoupanCodeResponse
import tour.traveling.travel.model.response.LoginResponce

class ProductDetailRespository {

    fun callRefrrealValidation(referer_code: String): LiveData<CoupanCodeResponse> {
        val data = MutableLiveData<CoupanCodeResponse>()
        AppRetrofit.instance.callRefrrealValidation(referer_code).enqueue(object : Callback<CoupanCodeResponse> {
            override fun onFailure(call: Call<CoupanCodeResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<CoupanCodeResponse>, response: Response<CoupanCodeResponse>) {

                if (response.isSuccessful){
                    data.value = if (response != null && response.body() != null) response!!.body() else null
                }
                else {
                    val gson = Gson()
                    val adapter = gson.getAdapter(CoupanCodeResponse::class.java)
                    if (response.errorBody() != null)
                        data.value = adapter.fromJson(response.errorBody()!!.string())
                }
            }
        })

        return data
    }


    fun callCoupanValidation(coupon_code: String): LiveData<CoupanCodeResponse> {
        val data = MutableLiveData<CoupanCodeResponse>()
        AppRetrofit.instance.callCoupanValidation(coupon_code).enqueue(object : Callback<CoupanCodeResponse> {
            override fun onFailure(call: Call<CoupanCodeResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<CoupanCodeResponse>, response: Response<CoupanCodeResponse>) {

                if (response.isSuccessful){
                    data.value = if (response != null && response.body() != null) response!!.body() else null
                }
                else {
                    val gson = Gson()
                    val adapter = gson.getAdapter(CoupanCodeResponse::class.java)
                    if (response.errorBody() != null)
                        data.value = adapter.fromJson(response.errorBody()!!.string())
                }
            }
        })

        return data
    }


    fun callCheckOutApi(user_id: String, number_of_person: String,journey_date: String, package_id: String,package_itinerary: String, coupon_code: String,referer_code: String, package_final_price: String,category_type: String): LiveData<LoginResponce> {
        val data = MutableLiveData<LoginResponce>()
        AppRetrofit.instance.callCheckOutApi(user_id, number_of_person,journey_date,package_id,package_itinerary,coupon_code,referer_code,package_final_price,category_type).enqueue(object : Callback<LoginResponce> {
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

    fun callCancelPacakge(user_id: String, package_id: String,category_type: String): LiveData<LoginResponce> {
        val data = MutableLiveData<LoginResponce>()
        AppRetrofit.instance.callCancelPacakge(user_id, package_id,category_type).enqueue(object : Callback<LoginResponce> {
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


}