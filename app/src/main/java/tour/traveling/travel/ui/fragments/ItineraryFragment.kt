package tour.traveling.travel.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.accountapp.accounts.constants.BundleConstant
import kotlinx.android.synthetic.main.layout_footer_detail_page.*
import org.json.JSONArray
import org.json.JSONObject
import tour.traveling.travel.R
import tour.traveling.travel.adpater.GallaryAdapter
import tour.traveling.travel.adpater.ItinenaryDetailAdapter
import tour.traveling.travel.databinding.FragmentItineraryBinding
import tour.traveling.travel.model.response.ItineraryDetailsItem
import tour.traveling.travel.model.response.PackageImagesItem
import tour.traveling.travel.model.response.ResultItemBanner


/**
 * A simple [Fragment] subclass.
 */
class ItineraryFragment() : Fragment() {

    lateinit var binding: FragmentItineraryBinding
    val mContext by lazy { context }
    var str: ResultItemBanner? = null
    lateinit var mitiAdapter: ItinenaryDetailAdapter
    var mResultIti: MutableList<ItineraryDetailsItem>? = null

    companion object {
        val TAG = "ItineraryFragment"
        fun newInstance(args: Bundle?): ItineraryFragment {
            val fragment = ItineraryFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_itinerary, container, false)

        str = arguments!!.getSerializable(BundleConstant.ITINENARY_DATA) as ResultItemBanner?
        ItinenaryAdapter()

        setItinenary(str!!.itinerary_details)


//        binding.txtDesc.setText("" + Html.fromHtml(str!!.itinerary_details!!.get(0).item_details!!.get(0).Itinerary_description))


        return binding.root

    }

    private fun setItinenary(resp: List<ItineraryDetailsItem>?) {
        mResultIti = ArrayList(resp!!)
        mitiAdapter.setData(mResultIti!!)
    }



    @SuppressLint("WrongConstant")
    fun ItinenaryAdapter() {
        if (!::mitiAdapter.isInitialized) {
            val layoutManager = LinearLayoutManager(mContext, LinearLayout.VERTICAL, false)
            val recyclerView = binding.recyclerItinary
            recyclerView.layoutManager = layoutManager

            mitiAdapter = ItinenaryDetailAdapter()
            recyclerView.adapter = mitiAdapter
        }

    }


}
