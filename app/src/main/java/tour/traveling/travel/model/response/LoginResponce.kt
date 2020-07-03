package tour.traveling.travel.model.response

data class LoginResponce(val result: List<ResultItem>?,
                         val message: String = "",
                         val status: String = "")


data class ResultItem(val country: String = "",
                      val panFile: String = "",
                      val city: String = "",
                      val adhaarFile: String = "",
                      val mobile: String = "",
                      val createdAt: String = "",
                      val type: String = "",
                      val createdBy: String = "",
                      val emailOtp: String = "",
                      val updatedAt: String = "",
                      val mobileOtp: String = "",
                      val emailVerify: String = "",
                      val name: String = "",
                      val id: String = "",
                      val state: String = "",
                      val passportFile: String = "",
                      val email: String = "",
                      val status: String = "")


