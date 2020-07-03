package fragments

import android.app.AlertDialog
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.utils.Prefences
import com.accountapp.accounts.utils.Utility
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.mooveall.ui.fragment.settings.DriverSettingViewModel
import com.mooveall.ui.fragment.settings.DriverSettingsHandler
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import tour.traveling.travel.R
import tour.traveling.travel.base.BaseFragment
import tour.traveling.travel.databinding.FragmentSettingsBinding
import tour.traveling.travel.ui.login.LoginActivity
import tour.traveling.travel.ui.password.ResetPasswordActivity
import tour.traveling.travel.ui.profile.ProfileActivity
import tour.traveling.travel.ui.webview.WebViewActivity

/**
 * Created by Arashjit Singh on 28/9/18.
 */
class FragmentSettings : BaseFragment(), DriverSettingsHandler {

    companion object {
        val TAG = "FragmentSettings"

        fun newInstance(args: Bundle?): FragmentSettings {
            val fragment = FragmentSettings()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getFragmentTag(): String {
        return TAG
    }

    lateinit var binding: FragmentSettingsBinding
    val mViewModel by lazy { ViewModelProviders.of(this).get(DriverSettingViewModel::class.java) }
    val mContext by lazy { context }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        mViewModel.setHandler(this)
        binding.variable = mViewModel

//        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        binding.includedToolbar.toolbar.setTitle("Setting")


        return binding.root
    }

    override fun onProfileClicked() {
        val intent = Intent(activity!!, ProfileActivity::class.java)
        Utility.startActivity(context!!, intent)
    }

    override fun onPaymentMethodClicked() {
//        Utility.startActivity(mContext!!, Intent(mContext, PaymentActivity::class.java))
    }

    override fun changePassword() {
        Utility.startActivity(mContext!!, Intent(mContext, ResetPasswordActivity::class.java))
    }

    override fun review() {
        val appPackageName = mContext!!.packageName // package name of the app
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
        } catch (anfe: android.content.ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
        }

    }

    override fun giveFeedback() {
//        Utility.startActivity(mContext!!, Intent(mContext, FeedbackActivity::class.java))
    }

    override fun gotoTermsConditions() {
        val intent = Intent(mContext, WebViewActivity::class.java)
        intent.putExtra(BundleConstant.TITLE, "Terms & Conditions")
        intent.putExtra(BundleConstant.WEB_URL, "https://www.mooveall.com/terms-and-condition/")
        Utility.startActivity(mContext!!, intent)
    }

    override fun aboutUs() {
        val intent = Intent(mContext, WebViewActivity::class.java)
        intent.putExtra(BundleConstant.TITLE, "About Us")
        intent.putExtra(BundleConstant.WEB_URL, "https://www.mooveall.com/about-us/")
        Utility.startActivity(mContext!!, intent)
    }

    //logout user from app
    override fun logout() {
        openLogoutDialog()
    }

    private fun openLogoutDialog() {

        val dialogBuilder = AlertDialog.Builder(context)

        // set message of alert dialog
        dialogBuilder.setMessage("Do you want to Logout?")
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()

                Prefences.resetUserData(mContext!!)
                val intent = Intent(mContext, LoginActivity::class.java)
                Utility.startActivityWithLeftToRightAnimation(activity,intent)
                activity!!.finishAffinity()

            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        alert.show()

    }

    fun logoutUser() {

//        if (!ApplicationClass.isConnectedToInternet()) {
//            Utility.showNoInternetSnackbar(binding.root)
//            return
//        }
//
//        val req = VerifyOtpReq(PrefUtils.getSharedPrefString(mContext, PrefConstants.PREF_USER_ID), "")
//        Utility.showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)
//        mViewModel.logoutUser(req).observe(this, object : Observer<SignUpResponse> {
//            override fun onChanged(resp: SignUpResponse?) {
//                Utility.showLoadingView(false, binding.loadingView.loadingIndicator, binding.loadingView.container)
//                if (resp != null) {
//                    Utility.showSnackBar(binding.root, "" + resp.Message)
//                    if (resp!!.Success) {
//                        binding.root.postDelayed({
//                            try {
//                                //clear all notifications from tray
//                                if (activity != null) {
//                                    val notificationManager = activity!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//                                    notificationManager.cancelAll()
//                                }
//                            } catch (e: Exception) {
//                                e.printStackTrace()
//                            }
//
//                            val intent = Intent(mContext, LandingActivity::class.java)
//                            PrefUtils.logoutUser(context!!)
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                            Utility.startActivity(mContext!!, intent)
//                        }, 400)
//                    }
//                } else {
//                    Utility.showServerErrorSnackBar(binding.root)
//                }
//            }
//        })
    }
}