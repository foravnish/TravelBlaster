package network

/**
 * Created by Avnish
 */
object NetworkConstants {

    const val BASE_URL: String = "http://spinof.in/travel/public/api/"
    //   Note:  Be remember Base URL must be change in 'network_security_config.xml' file as well.

    const val OTP_GENERATE = "user-registration-otp-generate"
    const val SIGNUP = "user-registration"
    const val SIGN_IN = "user-login"
    const val BANNER_LISTING = "banner-list"
    const val HOT_PLACES = "hot-place-list"
    const val CATEGORY = "product-list"
    const val REFFERAL_CODE = "verify_referer"
    const val COUPON_CODE = "verify_coupon"
    const val CHECKOUT = "package_purchase"
    const val BOOKING_HISTORY="package_purchase_history"
    const val CANCEL_PACKAGE="package_purchase_cancel"
}
