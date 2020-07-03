package tour.traveling.travel.signup

import android.app.Dialog
import android.content.Intent
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.accountapp.accounts.utils.Utility
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.utils.Prefences
import com.chaos.view.PinView
import tour.traveling.travel.R
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.databinding.ActivityRegistrationNewBinding
import tour.traveling.travel.model.response.OTPGenerateResponce
import tour.traveling.travel.model.response.RegistrationResponce
import tour.traveling.travel.ui.home.HomeActivity
import tour.traveling.travel.ui.signup.SignupViewModel
import tour.traveling.travel.ui.webview.WebViewActivity
import tour.traveling.travel.utils.ValidationHelper


class RegistrationActivity : BaseActivity() {

    lateinit var binding: ActivityRegistrationNewBinding

    val mViewModel by lazy { ViewModelProviders.of(mContext).get(SignupViewModel::class.java) }
    val mContext by lazy { this@RegistrationActivity }
    var otpVal: String = ""
    var status: String = ""
    var name = ""
    var mobile = ""
    var emailId = ""
    var password = ""

    override fun initUI() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration_new)


        binding.btnRegistration.setOnClickListener {

            name = binding.edtName.text.toString()
            mobile = binding.edtMobile.text.toString()
            emailId = binding.edtEmail.text.toString()
            password = binding.edtConfirmPassword.text.toString()

            if (isInternetAvailable(binding.root, mContext)) {

                if (isValidate()) {
                    hitOTPApi(name, mobile, emailId, password)
                }
            }
        }

        setClickableSpan()

    }

    private fun hitOTPApi(user_name: String, mobile: String, email: String, user_password: String) {

        Utility.closeKeyboard(binding.root, mContext)
        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)

        mViewModel.callOTP_Api(mobile)
            .observe(mContext, object : Observer<OTPGenerateResponce> {
                override fun onChanged(resp: OTPGenerateResponce?) {
                    showLoadingView(
                        false,
                        binding.loadingView.loadingIndicator,
                        binding.loadingView.container
                    )
                    if (resp != null) {
                        if (resp.status.equals("success")) {
                            openDialog()
                        } else {
                            Utility.showSnackBar(binding.root, "" + resp.message)
                        }

                    } else {
                        showServerErrorSnackbar(binding.root)
                    }
                }
            })

    }


    private fun setClickableSpan() {
        val SpanString = SpannableString(
//            "By Registering you agree to the Terms of Use and Privacy Policy"
            getString(R.string.signup_checkbox_message)
        )

        val termsAndCondition = object : ClickableSpan() {
            override fun onClick(textView: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra(BundleConstant.TITLE, "Terms & Conditions")
                intent.putExtra(
                    BundleConstant.WEB_URL,
                    "https://www.mooveall.com/terms-and-condition/"
                )
                Utility.startActivity(mContext, intent)
            }
        }

        val privacy = object : ClickableSpan() {
            override fun onClick(textView: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra(BundleConstant.TITLE, "Privacy Policy")
                intent.putExtra(BundleConstant.WEB_URL, "https://www.mooveall.com/privacy-policy/")
                Utility.startActivity(mContext, intent)
            }
        }


        //By Creating an account with our app, you have to read and agree to our Terms &amp; Conditions and Privacy Policy

        val termsAndConditionStartString = "By proceeding, you agree to Travel Blasters "
        val termsAndConditionEndString = termsAndConditionStartString + "Terms & Conditions"

        val privacyPolicyStartString = termsAndConditionEndString + " and "
        val privacyPolicyEndString = privacyPolicyStartString + "Privacy Policy"


        SpanString.setSpan(
            termsAndCondition, termsAndConditionStartString.length,
            termsAndConditionEndString.length, 0
        )
        SpanString.setSpan(
            privacy,
            privacyPolicyStartString.length,
            privacyPolicyEndString.length,
            0
        )

        SpanString.setSpan(
            ForegroundColorSpan(mContext.resources.getColor(R.color.blue2)),
            termsAndConditionStartString.length,
            termsAndConditionEndString.length,
            0
        )
        SpanString.setSpan(
            ForegroundColorSpan(mContext.resources.getColor(R.color.blue2)),
            privacyPolicyStartString.length,
            privacyPolicyEndString.length,
            0
        )

        SpanString.setSpan(
            UnderlineSpan(), termsAndConditionStartString.length,
            termsAndConditionEndString.length, 0
        )
        SpanString.setSpan(
            UnderlineSpan(),
            privacyPolicyStartString.length,
            privacyPolicyEndString.length,
            0
        )

        binding.txtCheckBox.movementMethod = LinkMovementMethod.getInstance()
        binding.txtCheckBox.setText(SpanString, TextView.BufferType.SPANNABLE)
        binding.txtCheckBox.isSelected = true
    }


    private fun openDialog() {

        val dialog = Dialog(this@RegistrationActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_otp_dialog)
        dialog.setCancelable(true)

        val btnSubmit = dialog.findViewById<View>(R.id.bt_submit) as Button
        val pinView = dialog.findViewById<View>(R.id.pinView) as PinView
        val getMobileNo = dialog.findViewById<View>(R.id.getMobileNo) as TextView
        getMobileNo.setText("" + binding.edtMobile.text.toString())
        pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                otpVal = s.toString()

            }
        })


        btnSubmit.setOnClickListener {

            Log.d("OTPVALUE", otpVal)

            if (otpVal == "") {
                ValidationHelper.showSnackBar(binding.root, getString(R.string.err_otp))
            } else if (otpVal.length < 6) {
                ValidationHelper.showSnackBar(binding.root, getString(R.string.err_invalid_itp))
            } else {
                Utility.closeKeyboard(binding.root, mContext)
                dialog.dismiss()
                hitSignUpAPi(otpVal)

            }


        }
        dialog.show()
    }


    private fun hitSignUpAPi(otpVal: String) {

        Utility.closeKeyboard(binding.root, mContext)
        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)

        mViewModel.callSignUp(
            "" + name,
            "" + mobile,
            "" + otpVal,
            "" + password,
            "" + emailId
        )
            .observe(mContext, object : Observer<RegistrationResponce> {
                override fun onChanged(resp: RegistrationResponce?) {
                    showLoadingView(
                        false,
                        binding.loadingView.loadingIndicator,
                        binding.loadingView.container
                    )
                    if (resp != null) {
                        if (resp.status.equals("success")) {
                            Utility.showSnackBar(binding.root, "" + resp.message)

                            binding.root.postDelayed({

                                Prefences.setIsLogin(mContext,true)
                                Prefences.setUserId(mContext,resp.result!!.get(0).id)
                                Prefences.setUserName(mContext,resp.result!!.get(0).name)
                                Prefences.setUserMobile(mContext,resp.result!!.get(0).mobile)
                                Prefences.setUserEmailId(mContext,resp.result!!.get(0).email)

                                val intent = Intent(this@RegistrationActivity, HomeActivity::class.java)
                                Utility.startActivityWithLeftToRightAnimation(this@RegistrationActivity,intent)
                                finish()
                            }, 1000)

                        } else {
                            Utility.showSnackBar(binding.root, "" + resp.message)
                        }
                    } else {
                        showServerErrorSnackbar(binding.root)
                    }
                }

            })

    }


    private fun isValidate(): Boolean {
        if (ValidationHelper.isDataFilled(
                binding.edtName,
                getString(R.string.err_name),
                binding.root
            )
        ) {
            if (ValidationHelper.isDataFilled(
                    binding.edtMobile,
                    getString(R.string.err_user_name),
                    binding.root
                )
            ) {

                if (ValidationHelper.isValidPassword(binding.edtPassword)) {
                    if (ValidationHelper.isDataFilled(
                            binding.edtPassword,
                            getString(R.string.err_password),
                            binding.root
                        )
                    ) {
                        if (ValidationHelper.isDataFilled(
                                binding.edtConfirmPassword,
                                getString(R.string.err_re_password),
                                binding.root
                            )
                        ) {
                            if (ValidationHelper.isValidPassword(binding.edtConfirmPassword)) {
                                if (ValidationHelper.validatePasswordSameFields(
                                        binding.edtPassword,
                                        binding.edtConfirmPassword,
                                        getString(R.string.err_same_password),
                                        binding.root
                                    )
                                ) {
                                    if (binding.termsConditionCheckbox.isChecked) {
                                        return true
                                    } else {
                                        ValidationHelper.showSnackBar(
                                            binding.root,
                                            getString(R.string.terms_conditions)
                                        )
                                    }

                                } else {
                                }
                            }

                        }
                    } else {
                        Utility.showSnackBar(binding.root, getString(R.string.err_invalid_password))
                    }
                } else {
                    Utility.showSnackBar(binding.root, getString(R.string.err_invalid_password))
                }
            }
        }
        return false
    }


}
