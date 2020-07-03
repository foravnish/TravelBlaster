package tour.traveling.travel.ui.listing

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.ui.login.LoginViewModel
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import tour.traveling.travel.R
import tour.traveling.travel.adpater.InsuranceAdapter
import tour.traveling.travel.adpater.SeeAllListingAdapter
import tour.traveling.travel.adpater.VisaAdapter
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.databinding.ActivityLoginNewBinding
import tour.traveling.travel.databinding.ActivitySeeAllListingBinding

class SeeAllListingActivity : BaseActivity() {

    lateinit var binding: ActivitySeeAllListingBinding

    val mViewModel by lazy { ViewModelProviders.of(mContext).get(LoginViewModel::class.java) }
    val mContext by lazy { this@SeeAllListingActivity }

    lateinit var mvseeAllAdapter: SeeAllListingAdapter


    override fun initUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_see_all_listing)
        val catname=intent.getStringExtra(BundleConstant.HOME_SCREEN_TYPE)
//        setToolbarWithBackIconWithSubTitle(binding.includedToolbar.toolbar,catname ,"")
        setToolbarWithBackIcon(binding.includedToolbar.toolbar,catname)

        setSeeAllAdapter()

        mvseeAllAdapter.setData()

    }

    @SuppressLint("WrongConstant")
    fun setSeeAllAdapter() {
        if (!::mvseeAllAdapter.isInitialized) {
            val layoutManager = GridLayoutManager(mContext, 1)
            val recyclerView = binding.recyclerSeeAll
            recyclerView.layoutManager = layoutManager

            mvseeAllAdapter = SeeAllListingAdapter()
            recyclerView.adapter = mvseeAllAdapter
        }

    }

}
