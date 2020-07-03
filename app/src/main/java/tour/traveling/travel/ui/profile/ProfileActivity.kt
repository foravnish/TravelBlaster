package tour.traveling.travel.ui.profile

import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.accountapp.accounts.utils.Prefences
import com.accountapp.accounts.utils.Utility
import tour.traveling.travel.R
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.databinding.ActivityProfileBinding
import tour.traveling.travel.ui.forms.FormHandler


class ProfileActivity : BaseActivity(),
    FormHandler {
    val TAG = "ProfileActivity"

    lateinit var binding: ActivityProfileBinding
    val mContext by lazy { this@ProfileActivity }
    val model by lazy { ViewModelProviders.of(this).get(ProfileViewModel::class.java) }
    var userImageUrl = ""
    val PLACE_AUTOCOMPLETE_REQUEST_CODE = 1
    var isProfileEditable = false

    override fun initUI() {
        binding = DataBindingUtil.setContentView(mContext, R.layout.activity_profile)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Profile"
        binding.toolbar.title = "Profile"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.viewModel = model
        model.setHandler(this)

        setDataFromPreferences()
//        if (isInternetAvailable(binding.root, mContext))
            //getUserProfile()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            R.id.edit_profile -> {
                val title = item.title
                if (title.equals("Edit")) {
                    item.title = "Save"
                    editUserProfile("Edit")
                } else {
                    item.title = "Edit"
                    editUserProfile("Save")
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun uploadImage() {
//        Utility.closeKeyboard(binding.root, mContext)
//        if (isProfileEditable) {
//            Utility.showImageChooserDialog(mContext)
//        }
    }

    override fun getAddressFromGoogle() {


    }


    fun editUserProfile(title: String) {
        if (title.equals("Edit")) {
            makeEditTextEditableOrNonEditable(true, binding.userNameEdtTxt)
            binding.userNameEdtTxt.requestFocus()
            binding.userNameEdtTxt.setSelection(binding.userNameEdtTxt.text.length)

            makeTextViewClickableOrNonClickable(true, binding.userAddressEdtTxt)
//            binding.paymentContainer.visibility = View.GONE

        } else {
            Utility.closeKeyboard(binding.root, mContext)
            makeEditTextEditableOrNonEditable(false, binding.userNameEdtTxt)
//            callApiToSaveProfile()

            makeTextViewClickableOrNonClickable(false, binding.userAddressEdtTxt)
//            binding.paymentContainer.visibility = View.VISIBLE
        }
    }

//    private fun getUserProfile() {
//        model.getUserProfile(PrefUtils.getSharedPrefString(mContext, PrefConstants.PREF_USER_ID)).observe(this, object : Observer<UserProfileResp> {
//            override fun onChanged(profileRep: UserProfileResp?) {
//                if (profileRep != null && profileRep.Result != null && profileRep.Result.size > 0) {
//                    val profileResp = profileRep.Result.get(0)
//                    Utility.setImageViaGlide(R.drawable.user_placeholder,
//                            "" + profileResp.profileImage,
//                            binding.profileImage,
//                            mContext)
//                    saveUserDataInPreferences(profileResp)
//                }
//            }
//        })
//    }

//    private fun callApiToSaveProfile() {
//        if (!isInternetAvailable(binding.root, mContext)) {
//            Utility.showNoInternetSnackbar(binding.root)
//            return
//        }
//        if (ValidationHelper.isContainDigit(binding.userNameEdtTxt,
//                        ValidationHelper.ALERT_TYPE.SNACK_BAR, getString(R.string.err_valid_user_name), true)) {
//            return
//        }
//
//        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)
//        val req = ProfileResult(
//                PrefUtils.getSharedPrefString(mContext, PrefConstants.PREF_USER_ID),
//                binding.userNameEdtTxt.text.toString(),
//                binding.userEmailEdtTxt.text.toString(),
//                binding.userMobileEdtTxt.text.toString(),
//                binding.userAddressEdtTxt.text.toString(),
//                userImageUrl)
//        model.editProfile(req).observe(this, object : Observer<LoginResponse> {
//            override fun onChanged(response: LoginResponse?) {
//                showLoadingView(false, binding.loadingView.loadingIndicator, binding.loadingView.container)
//                if (response != null && response.Result != null) {
//                    Utility.showSnackBar(binding.root, response.Message)
//                    if (response.Success)
//                        saveUserDataInPreferences(req)
//                }
//            }
//        })
//    }

    private fun setDataFromPreferences() {
        binding.userNameEdtTxt.setText("" + Prefences.getUserName(mContext))
        binding.userEmailEdtTxt.setText("" + Prefences.getUserEmailId(mContext))
        binding.userMobileEdtTxt.setText("" + Prefences.getUserMobile(mContext))
    }

//    private fun saveUserDataInPreferences(result: ProfileResult) {
//        PrefUtils.setSharedPrefStringData(mContext, PrefConstants.PREF_USER_NAME, "" + result.userName)
//        PrefUtils.setSharedPrefStringData(mContext, PrefConstants.PREF_USER_EMAIL, "" + result.email)
//        PrefUtils.setSharedPrefStringData(mContext, PrefConstants.PREF_MOBILE, "" + result.phone)
//        PrefUtils.setSharedPrefStringData(mContext, PrefConstants.PREF_USER_IMAGE, "" + result.profileImage)
//        PrefUtils.setSharedPrefStringData(mContext, PrefConstants.PREF_USER_ADDRESS, "" + result.address)
//        userImageUrl = "" + result.profileImage
//
//        setDataFromPreferences()
//    }

    private fun makeEditTextEditableOrNonEditable(isEditable: Boolean, editText: EditText) {
        editText.isClickable = isEditable
        editText.isFocusable = isEditable
        editText.isFocusableInTouchMode = isEditable

        isProfileEditable = isEditable
    }

    private fun makeTextViewClickableOrNonClickable(isClickable: Boolean, textView: TextView) {
        textView.isClickable = isClickable
        textView.isEnabled = isClickable
    }





}
