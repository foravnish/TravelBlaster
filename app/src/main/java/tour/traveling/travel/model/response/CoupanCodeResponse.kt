package tour.traveling.travel.model.response

data class ResultItemCouponCode(val coupon_code: String = "",
                      val id: Int = 0,
                      val createdDate: String = "",
                      val createdById: Int = 0,
                      val coupon_name: String = "",
                      val coupon_type: String = "",
                      val coupon_value: Int = 0,
                      val coupon_limit: String = "",
                      val status: Int = 0)


data class CoupanCodeResponse(val result: List<ResultItemCouponCode>?,
                              val message: String = "",
                              val status: String = "")


