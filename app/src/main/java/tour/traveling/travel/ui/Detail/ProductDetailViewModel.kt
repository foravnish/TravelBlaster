package tour.traveling.travel.ui.product

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tour.traveling.travel.model.response.CoupanCodeResponse
import tour.traveling.travel.model.response.LoginResponce

class ProductDetailViewModel : ViewModel() {
    private var handelar: ProductDetailHandler?=null

    lateinit var mRepo: ProductDetailRespository

    init {
        mRepo = ProductDetailRespository()
    }

    fun setHandelar(handelar: ProductDetailHandler){
        this.handelar=handelar
    }
    fun onClickBack(view: View){
        handelar!!.onClickBack()
    }

    fun onClickAddToCart(view: View){
        handelar!!.onClickAddToCart()
    }
    fun onClickBuyNow(view: View){
        handelar!!.onClickBuyNow()
    }


    fun onClickWishLish(view: View){
        handelar!!.onClickWishLish()
    }

    fun onClickshare(view: View){
        handelar!!.onClickshare()
    }


    fun onClickInformation(view: View){
        handelar!!.onClickInformation()
    }

    fun onClickChangePin(view: View){
        handelar!!.onClickChangePin()
    }

    fun onClickViewAll(view: View){
        handelar!!.onClickViewAll()
    }


    fun callRefrrealValidation(referer_code: String): LiveData<CoupanCodeResponse> {
        return mRepo.callRefrrealValidation(referer_code)
    }


    fun callCoupanValidation(coupon_code: String): LiveData<CoupanCodeResponse> {
        return mRepo.callCoupanValidation(coupon_code)
    }

    fun callCheckOutApi(user_id: String, number_of_person: String,journey_date: String, package_id: String,package_itinerary: String, coupon_code: String,referer_code: String, package_final_price: String,category_type: String): LiveData<LoginResponce> {
        return mRepo.callCheckOutApi(user_id, number_of_person,journey_date,package_id,package_itinerary,coupon_code,referer_code,package_final_price,category_type)
    }

    fun callCancelPacakge(user_id: String, package_id: String,category_type: String): LiveData<LoginResponce> {
        return mRepo.callCancelPacakge(user_id, package_id,category_type)
    }

}