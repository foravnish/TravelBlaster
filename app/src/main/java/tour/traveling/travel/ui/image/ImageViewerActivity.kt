package tour.traveling.travel.ui.image

import android.util.Log
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.accountapp.accounts.constants.BundleConstant
import tour.traveling.travel.R
import tour.traveling.travel.adpater.BannerPagerAdapter
import tour.traveling.travel.adpater.GallaryAdapter
import tour.traveling.travel.adpater.ImageViewPagerAdapter
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.databinding.ActivityImageViewerBinding
import tour.traveling.travel.model.response.PackageImagesItem
import tour.traveling.travel.model.response.ResultItemBanner


class ImageViewerActivity : BaseActivity(){
    lateinit var binding: ActivityImageViewerBinding
    val mContext by lazy { this@ImageViewerActivity }
    var str:  List<PackageImagesItem>? = null
    var pos:Int = 0

    override fun initUI() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_viewer)

        try {
            str = intent.getSerializableExtra("list") as  List<PackageImagesItem>??
            pos=intent.getIntExtra("pos",0)
        } catch (e: Exception) {
        }

        binding.ivback.setText(""+intent.getStringExtra("imageName"))
        binding.ivback.setOnClickListener { finish() }
//        binding.viewPager.adapter = ImageViewPagerAdapter(mContext)


        binding.viewPager.adapter =
            ImageViewPagerAdapter(mContext, str)
        binding.viewPager.setCurrentItem(pos);
//        binding.indicator.setViewPager(binding.viewpager)



    }

}
