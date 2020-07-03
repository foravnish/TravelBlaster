package tour.traveling.travel.ui.password

import androidx.databinding.DataBindingUtil
import com.mooveall.ui.forgotPassword.ForgotPasswordHandler
import kotlinx.android.synthetic.main.activity_web_view.view.*
import tour.traveling.travel.R
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.databinding.ActivityResetPasswordBinding
import tour.traveling.travel.utils.ValidationHelper

class ResetPasswordActivity : BaseActivity(), ForgotPasswordHandler {


    lateinit var binding: ActivityResetPasswordBinding
//    val viewModel by lazy { ViewModelProviders.of(mContext).get(ResetPasswordViewModel::class.java) }
    val mContext by lazy { this@ResetPasswordActivity }
    var isFromForgotPassword = false
    var isForHome = false

    override fun initUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password)
//        binding.password = viewModel
//        viewModel.setHandler(mContext)
        setToolbarWithBackIcon(binding.includedToolbar.toolbar, getString(R.string.forgot_password))
        //user came from forgot password screen

    }


//    private fun callApiToUpdatePassword() {
//
//        Utility.closeKeyboard(binding.root, mContext)
//
//        if (!ApplicationClass.isConnectedToInternet()) {
//            Utility.showNoInternetSnackbar(binding.root)
//            return
//        }
//
//        val updatePasswordReq = ChangePasswordReq("" + PrefUtils.getSharedPrefString(mContext, PrefConstants.PREF_USER_ID),
//            binding.oldPasswordEdtTxt.text.toString(),
//            binding.newPasswordEdtTxt.text.toString())
//
//        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)
//
//        viewModel.changeUserPassword(updatePasswordReq).observe(this, object : Observer<UserProfileResp> {
//            override fun onChanged(resp: UserProfileResp?) {
//                showLoadingView(false, binding.loadingView.loadingIndicator, binding.loadingView.container)
//                if (resp != null && resp.Result != null) {
//                    Utility.showSnackBar(binding.root, resp.Message)
//                    if (resp.Success) {
//                        binding.root.postDelayed(object : Runnable {
//                            override fun run() {
//                                finish()
//                            }
//                        }, 1000)
//
//                    }
//                }
//            }
//        })
//    }

//    private fun callApiToResetPassword() {
//        Utility.closeKeyboard(binding.root, mContext)
//
//        if (!ApplicationClass.isConnectedToInternet()) {
//            Utility.showNoInternetSnackbar(binding.root)
//            return
//        }
//
//        val updatePasswordReq = ChangePasswordReq("" + PrefUtils.getSharedPrefString(mContext, PrefConstants.PREF_USER_ID),
//            "",
//            binding.newPasswordEdtTxt.text.toString())
//
//        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)
//
//        viewModel.resetPassword(updatePasswordReq).observe(this, object : Observer<SignUpResponse> {
//            override fun onChanged(resp: SignUpResponse?) {
//                showLoadingView(false, binding.loadingView.loadingIndicator, binding.loadingView.container)
//                if (resp != null && resp.Result != null) {
//                    Utility.showSnackBar(binding.root, resp.Message)
//                    if (resp.Success) {
//                        binding.root.postDelayed(object : Runnable {
//                            override fun run() {
//                                redirectUserToLogin()
//                            }
//                        }, 1000)
//
//                    }
//                }
//            }
//        })
//    }

    fun isDataValid(): Boolean {
        //user came from within app
        if (!isFromForgotPassword)
        //check if password is filled
        {
            if (ValidationHelper.isDataFilled(binding.oldPasswordEdtTxt, "Old " + getString(R.string.error_enter_password), binding.root)) {
                //check length of password
                if (ValidationHelper.hasMinimumLength(binding.oldPasswordEdtTxt, resources.getInteger(R.integer.min_password_length), getString(R.string.err_old_password_length))) {
                    if (ValidationHelper.isDataFilled(binding.newPasswordEdtTxt, "New " + getString(R.string.error_enter_password), binding.root)) {
                        //check length of password
                        if (ValidationHelper.hasMinimumLength(binding.newPasswordEdtTxt, resources.getInteger(R.integer.min_password_length), getString(R.string.err_new_password_length))) {
                            return true
                        }
                    }
                }
            }
        }
        // user came from forgot password
        else {
            if (ValidationHelper.isDataFilled(binding.newPasswordEdtTxt, getString(R.string.error_enter_password), binding.root)) {
                if (ValidationHelper.hasMinimumLength(binding.newPasswordEdtTxt, resources.getInteger(R.integer.min_password_length), getString(R.string.err_password_length))) {
                    return true
                }
            }

        }
        return false
    }


    override fun onSubmitClicked() {
    }

}
