package tour.traveling.travel.adpater

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import com.accountapp.accounts.utils.Utility
import com.github.chrisbanes.photoview.PhotoView
import tour.traveling.travel.R
import tour.traveling.travel.model.response.PackageImagesItem

class ImageViewPagerAdapter(context: Activity, val images: List<PackageImagesItem>?) : PagerAdapter() {
//class ImageViewPagerAdapter(context: Activity?) : PagerAdapter() {

    var context: Activity
    lateinit var callback: GalleryCallback
    var imgGallery : PhotoView? = null
    var vari = 0

    init {
        this.context = context!!
    }

    fun setViewCallback(callback: GalleryCallback) {
        this.callback = callback
    }

    override fun getCount(): Int {
        if (images!!.size> 0)
            return images!!.size
        return  0
    }

    override fun isViewFromObject(view: View, p1: Any): Boolean {
        return view === (`p1` as RelativeLayout)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = LayoutInflater.from(container?.context)
            .inflate(R.layout.image_view_main,container,false)

        imgGallery = itemView.findViewById(R.id.imgGallery) as PhotoView

        imgGallery!!.setOnClickListener {

            if(vari == 0)
            {
                context.window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                context.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                vari = 1;
            }else
            {
                context.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                context.window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                vari = 0;
            }

            /*callback.onGalleryCallback("") */}


        Utility.setImageViaGlide(
            R.drawable.product_placeholder,
            images!!.get(position).image!!,
            imgGallery!!,
            context
        )

        container.addView(itemView)

        return itemView
    }


    private fun setImageUsingGlide(imgUrl: String) {
        //set image
        Utility.setImageViaGlide(R.drawable.product_placeholder,
            "" + imgUrl,
            this!!.imgGallery!!,
            context)
    }

    interface GalleryCallback {
        fun onGalleryCallback(p_id: String)
    }



}