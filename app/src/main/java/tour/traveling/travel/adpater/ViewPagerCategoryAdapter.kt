package tour.traveling.travel.adpater

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.accountapp.accounts.constants.BundleConstant
import tour.traveling.travel.model.response.DetailsItem
import tour.traveling.travel.ui.fragments.GallaryCategoryFragment
import tour.traveling.travel.ui.fragments.ItineraryCategoryFragment
import tour.traveling.travel.ui.fragments.OverViewCategoryFragment

class ViewPagerCategoryAdapter(manager: FragmentManager?, str: DetailsItem?, txtPrice:TextView) :
    FragmentStatePagerAdapter(manager!!) {
    var str:DetailsItem?
    lateinit var txtPrice:TextView
    init {
        this.str=str
        this.txtPrice=txtPrice
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        val args = Bundle()
        if (position === 0) {
            args.putSerializable(BundleConstant.ITINENARY_DATA,str)
            fragment = OverViewCategoryFragment.newInstance(args)
        } else if (position === 1) {
            args.putSerializable(BundleConstant.ITINENARY_DATA, str)
            fragment = GallaryCategoryFragment.newInstance(args)
        } else if (position === 2) {
            args.putSerializable(BundleConstant.ITINENARY_DATA, str)
            fragment = ItineraryCategoryFragment.newInstance(args)
        }

        return fragment!!
    }

    override fun getCount(): Int {
        return pageCount
    }

    val pageCount = 3

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "Overview"
        } else if (position == 1) {
            title = "Gallery"
        } else if (position == 2) {
            title = "Itinerary"
        }
        return title
    }
}
