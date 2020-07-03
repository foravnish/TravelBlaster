package network

import retrofit2.Call
import retrofit2.http.*
import tour.traveling.travel.model.response.*


/**
 * Created by Avnish on 2/4/18.
 */
interface AppService {


    //    //OTP Generate API
    @FormUrlEncoded
    @POST(NetworkConstants.OTP_GENERATE)
    fun callOTP_Api(
        @Field("user_mobile") user_mobile: String
    ): Call<OTPGenerateResponce>


    //    //signup API
    @FormUrlEncoded
    @POST(NetworkConstants.SIGNUP)
    fun callSignUp(
        @Field("user_name") user_name: String,
        @Field("user_mobile") mobile: String,
        @Field("user_mobile_otp") user_mobile_otp: String,
        @Field("user_password") user_password: String,
        @Field("user_email") email: String
    ): Call<RegistrationResponce>

    //
//
//    //Login API
    @FormUrlEncoded
    @POST(NetworkConstants.SIGN_IN)
    fun callLogin(
        @Field("user_mobile") phone_number: String,
        @Field("user_password") Pass: String
    ): Call<LoginResponce>

    // Home Banner List
    @GET(NetworkConstants.BANNER_LISTING)
    fun callBannerListing(): Call<BannerListingResponce>

    // Hot Places
    @GET(NetworkConstants.HOT_PLACES)
    fun callHotPlaces(): Call<HotPlaceResponce>


    // Categoty
    @GET(NetworkConstants.CATEGORY)
    fun callCategory(): Call<CategoryResponce>


    @FormUrlEncoded
    @POST(NetworkConstants.REFFERAL_CODE)
    fun callRefrrealValidation(
        @Field("referer_code") referer_code: String
    ): Call<CoupanCodeResponse>


    @FormUrlEncoded
    @POST(NetworkConstants.COUPON_CODE)
    fun callCoupanValidation(
        @Field("coupon_code") coupon_code: String
    ): Call<CoupanCodeResponse>


    @FormUrlEncoded
    @POST(NetworkConstants.CHECKOUT)
    fun callCheckOutApi(
        @Field("user_id") user_id: String,
        @Field("number_of_person") number_of_person: String,
        @Field("journey_date") journey_date: String,
        @Field("package_id") package_id: String,
        @Field("package_itinerary") package_itinerary: String,
        @Field("coupon_code") coupon_code: String,
        @Field("referer_code") referer_code: String,
        @Field("package_final_price") package_final_price: String,
        @Field("category_type") category_type: String
    ): Call<LoginResponce>


    @FormUrlEncoded
    @POST(NetworkConstants.BOOKING_HISTORY)
    fun callHistory(
        @Field("user_id") user_id: String
    ): Call<BookingHistoryResponse>


    @FormUrlEncoded
    @POST(NetworkConstants.CANCEL_PACKAGE)
    fun callCancelPacakge(
        @Field("user_id") user_id: String,
        @Field("package_id") package_id: String,
        @Field("category_type") category_type: String
    ): Call<LoginResponce>


}

