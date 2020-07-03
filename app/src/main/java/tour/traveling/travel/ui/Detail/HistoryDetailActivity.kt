package tour.traveling.travel.ui.Detail

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.utils.Prefences
import com.accountapp.accounts.utils.Utility
import kotlinx.android.synthetic.main.layout_footer_detail_page.view.*
import kotlinx.android.synthetic.main.layout_footer_detail_page.view.txtDays
import kotlinx.android.synthetic.main.layout_footer_detail_page.view.txtPrice
import kotlinx.android.synthetic.main.layout_footer_detail_page_history.view.*
import kotlinx.android.synthetic.main.row_hot_places.view.*
import kotlinx.android.synthetic.main.row_open_jobs.view.*
import tour.traveling.travel.R
import tour.traveling.travel.adpater.ViewPagerAdapter
import tour.traveling.travel.adpater.ViewPagerAdapterHistory
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.callback.AlertCallBack
import tour.traveling.travel.databinding.ActivityHistoryDetailBinding
import tour.traveling.travel.databinding.ActivityProductsDetailsBinding
import tour.traveling.travel.model.response.LoginResponce
import tour.traveling.travel.model.response.ResultItemBanner
import tour.traveling.travel.model.response.ResultItemHistory
import tour.traveling.travel.ui.home.HomeActivity
import tour.traveling.travel.ui.image.ImageViewerActivity
import tour.traveling.travel.ui.login.LoginActivity
import tour.traveling.travel.ui.product.ProductDetailViewModel
import java.io.Serializable

class HistoryDetailActivity : BaseActivity() , AlertCallBack {
    lateinit var binding: ActivityHistoryDetailBinding
    val mViewModel by lazy { ViewModelProviders.of(this).get(ProductDetailViewModel::class.java) }
    val mContext by lazy { this@HistoryDetailActivity}
    var mData: ResultItemHistory?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history_detail)

        mData = intent.getSerializableExtra(BundleConstant.DETAIL_DATA) as  ResultItemHistory?


        setToolbarWithBackIcon(binding.toolbar, ""+mData!!.package_id!!.get(0).package_name)
        binding.txtCountry.setText(""+mData!!.package_id!!.get(0).country_details!!.get(0).name)
        Utility.setViewCallback(this)


        binding.includedFooter.txtdateOfJunery.setText("Jounery Date: "+mData!!.journey_date)
//        binding.jobAddressTxtVwPicUp.setText(""+mData!!.package_id!!.get(0).package_days + " Days " + mData!!.package_id!!.get(0).package_night + " Nights")
        binding.includedFooter.txtPerson.setText(""+mData!!.adult +" Person(s)")
        binding.includedFooter.txtFinalPrice.setText("Final Amount: "+Utility.getIndianRupee(mData!!.final_package_cost.toString()))

        setupViewPager(binding.viewpager)
        binding.tabLayout.setupWithViewPager(binding.viewpager)

        binding.includedFooter.txtPrice.setText(""+Utility.getIndianRupee(mData!!.package_id!!.get(0).package_cost.toString()))
        binding.includedFooter.txtDays!!.setText("("+mData!!.package_id!!.get(0).package_days+" Days "+mData!!.package_id!!.get(0).package_night+" Nights)")

        if (mData!!.package_status==0){
            binding.includedFooter.btn_cancel_now.setText("Canceled")
        }else if(mData!!.package_status==1){
            binding.includedFooter.btn_cancel_now.setText("Cancel Now")
        }else if(mData!!.package_status==2){
            binding.includedFooter.btn_cancel_now.setText("Completed")
        }


        Utility.setImageViaGlide(
            R.drawable.product_placeholder,
            mData!!.package_id!!.get(0).package_images!!.get(0).image,
            binding.imageView1!!,
            mContext
        )

        binding.includedFooter.btn_cancel_now.setOnClickListener {
            if(mData!!.package_status==1) {
                openCancelDialog()
            }
        }

        binding.imageView1.setOnClickListener {
            val intent = Intent(mContext, ImageViewerActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("list", mData!!.package_id!!.get(0).package_images as Serializable)
            intent.putExtra("image", mData!!.package_id!!.get(0).package_images!!.get(0).image)
            intent.putExtra("imageName", mData!!.package_id!!.get(0).package_images!!.get(0).image_title)

            mContext!!.startActivity(intent)
        }

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapterHistory(supportFragmentManager,mData)
        binding.viewpager.offscreenPageLimit=3
        viewPager.adapter = adapter
    }

    override fun onAlertCallBack(msg: String) {
        val intent = Intent(this@HistoryDetailActivity, HomeActivity::class.java)
        Utility.startActivityWithLeftToRightAnimation(this@HistoryDetailActivity,intent)
        finish()


    }


    private fun openCancelDialog() {

        val dialogBuilder = AlertDialog.Builder(this@HistoryDetailActivity,R.style.AlertDialogTheme)
        // set message of alert dialog
        dialogBuilder.setMessage("Do you want to cancel this package?")
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()

                callCancelApi()

            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        alert.show()

    }

    private fun callCancelApi() {

        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)

        mViewModel.callCancelPacakge(
            ""+Prefences.getUserId(mContext),
            ""+mData!!.package_id!!.get(0).id,
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

}
