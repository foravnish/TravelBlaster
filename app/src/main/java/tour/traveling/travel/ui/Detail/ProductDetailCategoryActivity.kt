package tour.traveling.travel.ui.Detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.accountapp.accounts.callback.OnFragmentInteractionListener
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.utils.Utility
import kotlinx.android.synthetic.main.layout_footer_detail_page.view.*
import kotlinx.android.synthetic.main.row_hot_places.view.*
import tour.traveling.travel.R
import tour.traveling.travel.adpater.ViewPagerAdapter
import tour.traveling.travel.adpater.ViewPagerCategoryAdapter
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.databinding.ActivityProductDetailCategoryBinding
import tour.traveling.travel.databinding.ActivityProductsDetailsBinding
import tour.traveling.travel.model.response.DetailsItem
import tour.traveling.travel.model.response.ResultItemBanner
import tour.traveling.travel.model.response.ResultItemCategory
import tour.traveling.travel.ui.image.ImageViewerActivity
import tour.traveling.travel.ui.product.ProductDetailViewModel
import java.io.Serializable
import kotlinx.android.synthetic.main.row_hot_places.view.txtDays as txtDays1

class ProductDetailCategoryActivity : BaseActivity(), OnFragmentInteractionListener {


    lateinit var binding: ActivityProductDetailCategoryBinding
    val mViewModel by lazy { ViewModelProviders.of(this).get(ProductDetailViewModel::class.java) }
    val mContext by lazy { this@ProductDetailCategoryActivity }

    var mData: DetailsItem?=null

    override fun initUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail_category)

        val hasExtra = intent.hasExtra(BundleConstant.DETAIL_DATA)

        mData = intent.getSerializableExtra(BundleConstant.DETAIL_DATA) as  DetailsItem?


        setToolbarWithBackIcon(binding.toolbar, ""+mData!!.package_images!!.get(0).image_title)
        binding.txtCountry.setText(""+mData!!.country_details!!.get(0).name)

        binding.includedFooter.txtPrice.setText(""+ Utility.getIndianRupee(""+mData!!.package_cost))

        binding.includedFooter.txtDays!!.setText("("+mData!!.package_days+" Days "+mData!!.package_night+" Nights)")

        setupViewPager(binding.viewpager)
        binding.tabLayout.setupWithViewPager(binding.viewpager)

        Utility.setImageViaGlide(
            R.drawable.product_placeholder,
            mData!!.package_images!!.get(0).image,
            binding.imageView1!!,
            mContext
        )

        binding.imageView1.setOnClickListener {
            val intent = Intent(mContext, ImageViewerActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("pos", "0")
            intent.putExtra("list", mData!!.package_images as Serializable)
            intent.putExtra("image", mData!!.package_images!!.get(0).image)
            intent.putExtra("imageName", mData!!.package_images!!.get(0).image_title)

            mContext!!.startActivity(intent)
        }

    }

    private fun setupViewPager(viewPager: ViewPager) {

        val adapter = ViewPagerCategoryAdapter(supportFragmentManager,mData,binding.includedFooter.txtPrice)
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


}
