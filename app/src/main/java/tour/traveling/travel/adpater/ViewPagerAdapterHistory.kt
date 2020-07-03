package tour.traveling.travel.adpater

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.accountapp.accounts.constants.BundleConstant
import tour.traveling.travel.model.response.ResultItemHistory
import tour.traveling.travel.ui.fragments.ItineraryFragment
import tour.traveling.travel.ui.fragments.OverViewFragmentHistory
import tour.traveling.travel.ui.fragments.GallaryFragmentHistory
import tour.traveling.travel.ui.fragments.ItineraryFragmentHistory

class ViewPagerAdapterHistory(manager: FragmentManager?, str: ResultItemHistory?) :
    FragmentStatePagerAdapter(manager!!) {
    var str:ResultItemHistory?
    init {
        this.str=str
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        val args = Bundle()
        if (position === 0) {
            args.putSerializable(BundleConstant.ITINENARY_DATA,str)
            fragment = OverViewFragmentHistory.newInstance(args)
        } else if (position === 1) {
            args.putSerializable(BundleConstant.ITINENARY_DATA, str)
            fragment = GallaryFragmentHistory.newInstance(args)
        } else if (position === 2) {
            args.putSerializable(BundleConstant.ITINENARY_DATA, str)
            fragment = ItineraryFragmentHistory.newInstance(args)
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
