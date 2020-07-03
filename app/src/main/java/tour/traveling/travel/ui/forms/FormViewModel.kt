package tour.traveling.travel.ui.forms

import android.view.View
import androidx.lifecycle.ViewModel
import tour.traveling.travel.ui.forms.FormHandler

/**
 */
class FormViewModel : ViewModel() {

    private var handler: FormHandler? = null
    lateinit var mRepository: FormRepository

    internal fun setHandler(handler: FormHandler) {
        this.handler = handler
    }

    init {
        mRepository = FormRepository()
    }

    fun uploadImage(view: View) {
        handler!!.uploadImage()
    }

    fun getAddressFromGoogle(view: View) {
        handler!!.getAddressFromGoogle()
    }

//    fun getUserProfile(userId: String): LiveData<UserProfileResp> {
//        return mRepository.getProfile(userId)
//    }
//
//    fun editProfile(req: ProfileResult): LiveData<LoginResponse> {
//        return mRepository.saveUserProfile(req)
//    }
//
//    fun getDriverProfile(userId: Int): LiveData<DriverProfileResponse> {
//        return mRepository.getDriverProfile(userId)
//    }

}