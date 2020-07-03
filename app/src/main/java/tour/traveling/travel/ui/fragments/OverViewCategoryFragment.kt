package tour.traveling.travel.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.utils.Utility
import tour.traveling.travel.R
import tour.traveling.travel.adpater.InclusionAdapter
import tour.traveling.travel.databinding.FragmentOverviewBinding
import tour.traveling.travel.dialogs.BottomSheetFragment
import tour.traveling.travel.model.response.AmenitiesDetailsItem
import tour.traveling.travel.model.response.DetailsItem
import tour.traveling.travel.model.response.ResultItemBanner
import tour.traveling.travel.ui.forms.EnquiryFormActivity

/**
 * A simple [Fragment] subclass.
 */
class OverViewCategoryFragment : Fragment() {

    lateinit var binding: FragmentOverviewBinding
    val mContext by lazy { context }
    var str: DetailsItem? = null

    lateinit var mInclusionAdapter: InclusionAdapter
    var mResultinclusion: MutableList<AmenitiesDetailsItem>? = null


    companion object {
        val TAG = "OverViewCategoryFragment"
        fun newInstance(args: Bundle?): OverViewCategoryFragment {
            val fragment = OverViewCategoryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)

        str = arguments!!.getSerializable(BundleConstant.ITINENARY_DATA) as DetailsItem?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.txtDesc.setText("" + Html.fromHtml(str!!.package_description, Html.FROM_HTML_MODE_COMPACT))
        } else {
            binding.txtDesc.setText("" + Html.fromHtml(str!!.package_description))
        }


        binding.fabEnquiry.setOnClickListener {
            val intent = Intent(activity, EnquiryFormActivity::class.java)
            Utility.startActivityWithLeftToRightAnimation(activity,intent)
        }

        setHotPlacesAdapter()
        setInclusion(str!!.amenities_details)

        binding.txtFlight.setOnClickListener {

            if (str!!.flight_details!!.size > 0) {
                val bottomSheetFragment = BottomSheetFragment(
                    mContext,
                    binding.txtFlight.text.toString(),
                    str!!.flight_details
                )
                bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.getTag())
            }else{
                Utility.showSnackBar(binding.root,"There are no Flights in this packages.")
            }
        }
        return binding.root



    }

    private fun setInclusion(resp: List<AmenitiesDetailsItem>?) {
        mResultinclusion = ArrayList(resp!!)
        mInclusionAdapter.setData(mResultinclusion!!)
    }


    @SuppressLint("WrongConstant")
    fun setHotPlacesAdapter() {
        if (!::mInclusionAdapter.isInitialized) {
            val layoutManager = LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false)
            val recyclerView = binding.recyclerInclusion
            recyclerView.layoutManager = layoutManager

            mInclusionAdapter = InclusionAdapter()
            recyclerView.adapter = mInclusionAdapter
        }

    }

}
