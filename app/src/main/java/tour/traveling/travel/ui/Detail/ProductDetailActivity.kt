package tour.traveling.travel.ui.product

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.accountapp.accounts.callback.OnFragmentInteractionListener
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.utils.Utility
import kotlinx.android.synthetic.main.layout_footer_detail_page.view.*
import kotlinx.android.synthetic.main.row_hot_places.view.txtDays
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import tour.traveling.travel.R
import tour.traveling.travel.adpater.ViewPagerAdapter
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.constants.AppConstants
import tour.traveling.travel.databinding.ActivityProductsDetailsBinding
import tour.traveling.travel.event.ItiernaryPriceEvent
import tour.traveling.travel.model.response.ResultItemBanner
import tour.traveling.travel.ui.Detail.CheckoutActivity
import tour.traveling.travel.ui.image.ImageViewerActivity
import java.io.Serializable


class ProductDetailActivity : BaseActivity(),OnFragmentInteractionListener {


    lateinit var binding: ActivityProductsDetailsBinding
    val mViewModel by lazy { ViewModelProviders.of(this).get(ProductDetailViewModel::class.java) }
    val mContext by lazy { this@ProductDetailActivity }

    var mNewPriceValue:Float = 0.0f
    var mUpdatePriceValue:Float = 0.0f
    var mTxtPrice:Float = 0.0f

    var mData: ResultItemBanner?=null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_products_details)

        mData = intent.getSerializableExtra(BundleConstant.DETAIL_DATA) as  ResultItemBanner?
        mTxtPrice=mData!!.package_cost.toFloat()

        EventBus.getDefault().register(this);

        Log.d("DataOfSingle",""+mData)

        setToolbarWithBackIcon(binding.toolbar, ""+mData!!.package_name)
        binding.txtCountry.setText(""+mData!!.country_details!!.get(0).name)

        binding.includedFooter.txtPrice.setText(""+Utility.getIndianRupee(mData!!.package_cost))

        binding.includedFooter.txtDays!!.setText("("+mData!!.package_days+" Days "+mData!!.package_night+" Nights)")

        setupViewPager(binding.viewpager)
        binding.tabLayout.setupWithViewPager(binding.viewpager)

        setItenanryData(mData!!)

        Utility.setImageViaGlide(
            R.drawable.product_placeholder,
            mData!!.package_images!!.get(0).image,
            binding.imageView1!!,
            mContext
        )

        binding.includedFooter.btn_buy_now.setOnClickListener {
//            if (!BundleConstant.SelectedDate.isEmpty()){

            val intent = Intent(mContext, CheckoutActivity::class.java)
                intent.putExtra("date", BundleConstant.SelectedDate)
                intent.putExtra("member", BundleConstant.SeletedPerson)
                intent.putExtra("price", mTxtPrice)

                intent.putExtra(BundleConstant.DETAIL_DATA,mData as Serializable)
                mContext!!.startActivity(intent)
//            }else{
//                Utility.showSnackBar(binding.root,"Please select date")
//            }


        }
        binding.imageView1.setOnClickListener {
            val intent = Intent(mContext, ImageViewerActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("list", mData!!.package_images as Serializable)
            intent.putExtra("image", mData!!.package_images!!.get(0).image)
            intent.putExtra("imageName", mData!!.package_images!!.get(0).image_title)

            mContext!!.startActivity(intent)
        }


    }

    private fun setItenanryData(mData: ResultItemBanner) {

        for (i in  0..mData.itinerary_details!!.size-1){
            if (mData!!.itinerary_details!!.get(i).itinerary_default_status==0){
                AppConstants.iteData.add(mData!!.itinerary_details!!.get(i).id)
            }
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {

        val adapter = ViewPagerAdapter(supportFragmentManager,mData)
        binding.viewpager.offscreenPageLimit=3
        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_page, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            R.id.search -> {

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFragmentInteraction(tag: String) {


    }

    override fun showFragment(
        tag: String,
        args: Bundle?,
        addToBackStack: Boolean,
        replace: Boolean
    ) {
    }

    override fun popFrag(tag: String) {
    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(eventPrice: ItiernaryPriceEvent?) {
        mNewPriceValue=eventPrice!!.price
        if (eventPrice!!.isCheck){
            mTxtPrice=mTxtPrice+mNewPriceValue
        }else{
            mTxtPrice=mTxtPrice-mNewPriceValue
        }


        binding.includedFooter.txtPrice.setText(""+Utility.getIndianRupee(mTxtPrice.toString()))



    }

}