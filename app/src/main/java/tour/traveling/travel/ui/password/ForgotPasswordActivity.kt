package tour.traveling.travel.ui.password

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mooveall.ui.forgotPassword.ForgotPasswordHandler
import com.mooveall.ui.forgotPassword.ForgotPasswordViewModel
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import tour.traveling.travel.R
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.databinding.ActivityForgotPasswordBinding
import tour.traveling.travel.utils.ValidationHelper

class ForgotPasswordActivity : BaseActivity(), ForgotPasswordHandler {

    lateinit var binding: ActivityForgotPasswordBinding
    val mContext by lazy { this@ForgotPasswordActivity }
    val mViewModel by lazy { ViewModelProviders.of(this).get(ForgotPasswordViewModel::class.java) }
    var isForHome = false

    override fun initUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        setToolbarWithBackIcon(binding.includedToolbar.toolbar, getString(R.string.forgot_password))
//        binding.model = mViewModel
        mViewModel.setHandler(this)

    }

    override fun onSubmitClicked() {
        if (isInternetAvailable(binding.root, mContext)) {
            if (isDataValid())
                callResetPasswordApi()

        }
    }

    //reset password API
    fun callResetPasswordApi() {


//        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)
//        val req = LoginReq(binding.userMobileNumEdtTxt.text!!.trim().toString(), "", "", "")
//
//        mViewModel.forgotPassword(req).observe(this, object : Observer<SignUpResponse> {
//            override fun onChanged(resp: SignUpResponse?) {
//                showLoadingView(false, binding.loadingView.loadingIndicator, binding.loadingView.container)
//                if (resp != null && resp.Result != null) {
//                    Utility.showSnackBar(binding.root, resp.Message)
//                    if (resp.Success) {
//                        PrefUtils.setSharedPrefStringData(mContext, PrefConstants.PREF_USER_ID, "" + resp.Result.userId)
//                        showVerifyOTPDialogue()
//                    }
//                } else
//                    showServerErrorSnackbar(binding.root)
//            }
//        })
    }

//    fun showVerifyOTPDialogue() {
//        val dialog = FullScreenOtpDialogue()
//        val ft = mContext.getSupportFragmentManager().beginTransaction()
//        val args = Bundle()
//        args.putString(BundleConstants.MOBILE_NUMBER, binding.userMobileNumEdtTxt.text.toString())
//
//        if (isForHome)
//            args.putString(BundleConstants.REDIRECT_TO_HOME, "true")
//
//        dialog.arguments = args
//        dialog.isCancelable = false
//        dialog.show(ft, FullScreenOtpDialogue.TAG)
//    }

    fun isDataValid(): Boolean {
        //check if mobile number is filled
        if (ValidationHelper.isDataFilled(binding.userMobileNumEdtTxt, getString(R.string.err_user_name), binding.root)) {
            //check length of mobile number
            if (ValidationHelper.hasMinimumLength(binding.userMobileNumEdtTxt, resources.getInteger(R.integer.mobile_number_max_length), getString(R.string.err_mobile_length))) {
                return true
            }
        }
        return false
    }

}