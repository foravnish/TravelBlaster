package tour.traveling.travel.model.response

data class ResultItemRegitration(val country: String = "",
                      val panFile: String = "",
                      val city: String = "",
                      val adhaarFile: String = "",
                      val mobile: String = "",
                      val created_at: String = "",
                      val type: String = "",
                      val created_by: String = "",
                      val email_otp: String = "",
                      val updated_at: String = "",
                      val mobile_otp: String = "",
                      val email_verify: String = "",
                      val name: String = "",
                      val id: String = "",
                      val state: String = "",
                      val passport_file: String = "",
                      val email: String = "",
                      val status: String = "")


data class RegistrationResponce(val result: List<ResultItemRegitration>?,
                                val message: String = "",
                                val status: String = "")


