package tour.traveling.travel.ui.login

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.accountapp.accounts.ui.login.LoginViewModel
import com.accountapp.accounts.utils.Prefences
import com.accountapp.accounts.utils.Utility
import tour.traveling.travel.R
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.databinding.ActivityLoginNewBinding
import tour.traveling.travel.model.response.LoginResponce
import tour.traveling.travel.signup.RegistrationActivity
import tour.traveling.travel.ui.home.HomeActivity
import tour.traveling.travel.ui.password.ForgotPasswordActivity
import tour.traveling.travel.utils.ValidationHelper

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginNewBinding

    val mViewModel by lazy { ViewModelProviders.of(mContext).get(LoginViewModel::class.java) }
    val mContext by lazy { this@LoginActivity }


    override fun initUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_new)

//        binding.loginHandelar=mViewModel
//        mViewModel.setHandler(mContext)

        setListener()
        forgotPassword()

        binding.loginGoogleImgVw.setOnClickListener {
            Log.d("dfdfdxgdg","google")
        }
        binding.loginFacebookImgVw.setOnClickListener {
            Log.d("dfdfdxgdg","facebook")
        }
    }

    private fun forgotPassword() {

        binding.txtForgotPassword.setOnClickListener {
            Utility.startActivityWithLeftToRightAnimation(this@LoginActivity, Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }

    }
    // 9814076123
    private fun callLoginAPi(userName: String, password: String) {

        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)
        mViewModel.callLogin(userName, password)
            .observe(mContext, object : Observer<LoginResponce> {
                override fun onChanged(resp: LoginResponce?) {
                       showLoadingView(false, binding.loadingView.loadingIndicator, binding.loadingView.container)
                    if (resp != null) {
                        if (resp.status.equals("success")) {

                            Prefences.setIsLogin(mContext,true)
                            Prefences.setUserId(mContext,resp.result!!.get(0).id)
                            Prefences.setUserName(mContext,resp.result!!.get(0).name)
                            Prefences.setUserMobile(mContext,resp.result!!.get(0).mobile)
                            Prefences.setUserEmailId(mContext,resp.result!!.get(0).email)

                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            Utility.startActivityWithLeftToRightAnimation(this@LoginActivity,intent)
                            finish()

                        } else {
                            Utility.showSnackBar(binding.root, ""+resp.message)
                        }
                    } else {
                        showServerErrorSnackbar(binding.root)
                    }
                }

            })

    }

    private fun setListener() {
        binding.btnLogin.setOnClickListener(View.OnClickListener {
            Utility.closeKeyboard(binding.root, mContext)

//            Utility.startActivityWithLeftToRightAnimation(this@LoginActivity, Intent(this@LoginActivity, HomeActivity::class.java))



            if (isInternetAvailable(binding.root, mContext)) {
                if (isValidate()) {
                    callLoginAPi(binding.edtMobile.text.toString(), binding.edtPassword.text.toString())
                }
            }

        })
//
        binding.txtRegister.setOnClickListener(View.OnClickListener {
            Utility.startActivityWithLeftToRightAnimation(this@LoginActivity, Intent(this@LoginActivity, RegistrationActivity::class.java))

        })
//    }

//    override fun onRestart() {
//        super.onRestart()
//        binding.fab.visibility = View.GONE
//    }

//    override fun onResume() {
//        super.onResume()
//        binding.fab.visibility = View.VISIBLE
//    }


    }

    private fun isValidate(): Boolean {

        if (ValidationHelper.isDataFilled(
                binding.edtMobile,
                getString(R.string.err_user_name),
                binding.root )) {
            if (ValidationHelper.isDataFilled(
                    binding.edtPassword,
                    getString(R.string.err_password), binding.root)){
                return true
            }
        }

        return false
    }

}
