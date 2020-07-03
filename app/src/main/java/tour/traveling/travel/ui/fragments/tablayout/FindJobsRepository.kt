package tour.traveling.travel.ui.fragments.tablayout;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import network.AppRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tour.traveling.travel.model.response.BookingHistoryResponse
import tour.traveling.travel.model.response.HotPlaceResponce
import tour.traveling.travel.model.response.LoginResponce
import tour.traveling.travel.model.response.ResultItemHistory


/**
 */
class FindJobsRepository {
    fun callHistory(user_id: String): LiveData<BookingHistoryResponse> {
        val data = MutableLiveData<BookingHistoryResponse>()
        AppRetrofit.instance.callHistory(user_id).enqueue(object : Callback<BookingHistoryResponse> {
            override fun onFailure(call: Call<BookingHistoryResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<BookingHistoryResponse>, response: Response<BookingHistoryResponse>) {
                data.value = if (response != null && response.body() != null) response!!.body() else null
            }
        })

        return data
    }

}