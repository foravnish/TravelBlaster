package tour.traveling.travel.adpater

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.utils.Utility
import tour.traveling.travel.R
import tour.traveling.travel.model.response.PackageImagesItem
import tour.traveling.travel.model.response.ResultItemBanner
import tour.traveling.travel.ui.login.LoginActivity
import tour.traveling.travel.ui.product.ProductDetailActivity
import java.io.Serializable

class BannerPagerAdapter(context: Context?, val images: List<ResultItemBanner>?) : PagerAdapter() {

    var context: Context
    var imageView : ImageView? = null
    var txt1:TextView?=null
    var txtCountry:TextView?=null
    var txtDays:TextView?=null

    init {
        this.context = context!!
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
            .inflate(R.layout.banner_item,container,false)


        imageView = itemView.findViewById(R.id.imageView) as ImageView
        txt1=itemView.findViewById(R.id.txt1) as TextView;
        txtCountry=itemView.findViewById(R.id.txtCountry) as TextView;
        txtDays=itemView.findViewById(R.id.txtDays) as TextView;


        txt1!!.setText(""+images!!.get(position).package_name)
        txtCountry!!.setText(""+images!!.get(position).country_details!!.get(0).name)
        txtDays!!.setText(""+images!!.get(position).package_days+" Days "+images!!.get(position).package_night+" Nights")

        imageView!!.setOnClickListener {

            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(BundleConstant.DETAIL_DATA,images!!.get(position) as Serializable)

            context.startActivity(intent)

        }
        Utility.setImageViaGlide(
            R.drawable.product_placeholder,
            images!!.get(position).package_images!!.get(0).image,
            imageView!!,
            context
        )

        container.addView(itemView)

        return itemView
    }

}