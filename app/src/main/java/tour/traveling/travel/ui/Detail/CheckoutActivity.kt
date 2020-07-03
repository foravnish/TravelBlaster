package tour.traveling.travel.ui.Detail

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.utils.Prefences
import com.accountapp.accounts.utils.Utility
import tour.traveling.travel.R
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.callback.AlertCallBack
import tour.traveling.travel.constants.AppConstants
import tour.traveling.travel.databinding.ActivityCheckoutBinding
import tour.traveling.travel.model.response.CoupanCodeResponse
import tour.traveling.travel.model.response.LoginResponce
import tour.traveling.travel.model.response.ResultItemBanner
import tour.traveling.travel.ui.home.HomeActivity
import tour.traveling.travel.ui.product.ProductDetailViewModel


class CheckoutActivity : BaseActivity() , AlertCallBack{
    lateinit var binding: ActivityCheckoutBinding
    val mContext by lazy { this@CheckoutActivity }
    var mData: ResultItemBanner? = null

    val mViewModel by lazy { ViewModelProviders.of(mContext).get(ProductDetailViewModel::class.java) }

    var member: String = "1"
    var totalPrice:Float=0.0f
    var finalPrice:Float=0.0f
    var pricePerPerson: Float = 0.0f
    var couponCodeValue:String=""
    var referralCodeValue:String=""
    override fun initUI() {
        binding = DataBindingUtil.setContentView(mContext, R.layout.activity_checkout)
        setToolbarWithBackIcon(binding.toolbar, "Checkout")

        Utility.setViewCallback(this)
        mData = intent.getSerializableExtra(BundleConstant.DETAIL_DATA) as ResultItemBanner?

        val intent = intent

//        date=intent.getStringExtra("date")
//        member=intent.getStringExtra("member")
        val price = intent.getFloatExtra("price", 0.0f)


//        binding.txtdateOfTravelling.setText(""+date)
//        binding.txtNoPerPerson.setText(""+member+" Persons")
        binding.txtPricePerPerson.setText("र " + price)

        binding.txtTitle.setText("" + mData!!.package_name)
        binding.txtStartingFrom.setText("Starting from " + mData!!.flight_from)

        val totalMember = member.toInt()
        pricePerPerson = price.toFloat()
        totalPrice = pricePerPerson * totalMember

        finalPrice=totalPrice
        binding.txtTotalPrice.setText("र " + totalPrice)
        
        
        buttonClickListener()

    }

    private fun buttonClickListener() {
        binding.txtEdit.setOnClickListener {
            val intent = Intent(mContext, SelectBookingDetail::class.java)
            startActivityForResult(intent, 101)
        }
        binding.txtContinueToPayent.setOnClickListener {
            if (!BundleConstant.SelectedDate.isEmpty()) {
                callCheckOutApi()
            }else{
                Utility.showSnackBar(binding.root,"Please select date")
            }
        }
        
        binding.btnApplyRefferal.setOnClickListener {
            if (TextUtils.isEmpty(binding.edtReferral.text.toString())){
                Utility.showSnackBar(binding.root,"Please enter Referral Code")
            }else {
                Utility.closeKeyboard(binding.root,mContext)
                callReferralValidationApi(binding.edtReferral.text.toString())
            }
        }
        
        binding.btnApplyCoupan.setOnClickListener {
            if (TextUtils.isEmpty(binding.edtCoupanCode.text.toString())){
                Utility.showSnackBar(binding.root,"Please enter Coupon Code")
            }else {
                Utility.closeKeyboard(binding.root,mContext)
                callCoupanValidationApi(binding.edtCoupanCode.text.toString())
            }

        }
        
    }

    private fun callCheckOutApi() {
        Log.d("IteneraryData",""+ AppConstants.iteData)
        val convertedItenanryIds: String = android.text.TextUtils.join(",", AppConstants.iteData)
        Log.d("IteneraryDataComma",""+convertedItenanryIds)

        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)
        mViewModel.callCheckOutApi(
            ""+Prefences.getUserId(mContext),
            ""+BundleConstant.SeletedPerson,
            ""+BundleConstant.SelectedDate,
            ""+mData!!.id,
            ""+convertedItenanryIds,
            ""+couponCodeValue,
            ""+referralCodeValue,
            ""+finalPrice,
            "package")

            .observe(mContext, object : Observer<LoginResponce> {
                override fun onChanged(resp: LoginResponce?) {
                    showLoadingView(false, binding.loadingView.loadingIndicator, binding.loadingView.container)
                    if (resp != null) {
                        if (resp.status.equals("success")) {

                            Utility.showAlertDialog(mContext,resp.message)
                        } else {
                            Utility.showSnackBar(binding.root, ""+resp.message)
                        }
                    } else {
                        showServerErrorSnackbar(binding.root)
                    }
                }

            })



    }


    private fun callReferralValidationApi(strReferalCode: String) {

        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)
        mViewModel.callRefrrealValidation(""+strReferalCode)
            .observe(mContext, object : Observer<CoupanCodeResponse> {
                override fun onChanged(resp: CoupanCodeResponse?) {
                    showLoadingView(false, binding.loadingView.loadingIndicator, binding.loadingView.container)
                    if (resp != null) {
                        if (resp.status.equals("success")) {

                            referralCodeValue=strReferalCode
                            binding.txtReferralValue.setTextColor(resources.getColor(R.color.green_light))
                            binding.txtReferralValue.setText(""+resp.message)

                        } else {
                            referralCodeValue=""
                            binding.txtReferralValue.setTextColor(resources.getColor(R.color.red))
                            binding.txtReferralValue.setText(""+resp.message)
                        }
                    } else {
                        showServerErrorSnackbar(binding.root)
                    }
                }

            })

    }


    private fun callCoupanValidationApi(strCoupanCode: String) {

        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)
        mViewModel.callCoupanValidation(""+strCoupanCode)
            .observe(mContext, object : Observer<CoupanCodeResponse> {
                override fun onChanged(resp: CoupanCodeResponse?) {
                    showLoadingView(false, binding.loadingView.loadingIndicator, binding.loadingView.container)
                    if (resp != null) {
                        if (resp.status.equals("success")) {

                            couponCodeValue=strCoupanCode
                            if (resp.result!!.get(0).coupon_type.contentEquals("flat")){

                                binding.txtCouponValue.setText(""+resp.message+" Discount is: र "+resp.result!!.get(0).coupon_value)
                                val couponPrice=resp.result!!.get(0).coupon_value
                                val calculatePrice=totalPrice-couponPrice
                                finalPrice=calculatePrice
                                binding.txtTotalPrice.setText("र " + calculatePrice)
                            }else{
                                val couponPercentage=resp.result!!.get(0).coupon_value
                                val percentagePrice=totalPrice*couponPercentage/100
                                val calculatePrice=totalPrice-percentagePrice
                                finalPrice=calculatePrice
                                binding.txtTotalPrice.setText("र " + calculatePrice)

                                binding.txtCouponValue.setText(""+resp.message+" Discount is: र "+percentagePrice)
                            }
                            binding.txtCouponValue.setTextColor(resources.getColor(R.color.green_light))


                        } else {
                            couponCodeValue=""
                            finalPrice=totalPrice
                            binding.txtTotalPrice.setText("र " + totalPrice)
                            binding.txtCouponValue.setTextColor(resources.getColor(R.color.red))
                            binding.txtCouponValue.setText(""+resp.message)

                        }
                    } else {
                        showServerErrorSnackbar(binding.root)
                    }
                }

            })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        try {
            if (requestCode === 101) {
                BundleConstant.SelectedDate = data!!.getStringExtra("Date")
                BundleConstant.SeletedPerson = data!!.getStringExtra("members")
                member = BundleConstant.SeletedPerson

                val totalMember = member.toInt()
                totalPrice = pricePerPerson * totalMember
                finalPrice=totalPrice
                binding.txtTotalPrice.setText("र " + totalPrice)


                binding.txtdateOfTravelling.setText("" + "Travellers From " + BundleConstant.SelectedDate)
                binding.txtNoPerPerson.setText("" + BundleConstant.SeletedPerson + " " + "Travellers ")

                binding.txtReferralValue.setText("")
                binding.txtCouponValue.setText("")
            }


        } catch (e: Exception) {
        }

    }

    override fun onAlertCallBack(msg: String) {
        val intent = Intent(this@CheckoutActivity, HomeActivity::class.java)
        Utility.startActivityWithLeftToRightAnimation(this@CheckoutActivity,intent)
        finish()

    }

}
