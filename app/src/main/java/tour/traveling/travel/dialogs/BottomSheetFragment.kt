package tour.traveling.travel.dialogs

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import tour.traveling.travel.adpater.FlightDetailAdapter
import tour.traveling.travel.databinding.FragmentBottomSheetDialogBinding
import tour.traveling.travel.model.response.FlightDetailsItem
import tour.traveling.travel.model.response.ResultItemBanner


class BottomSheetFragment(ctx: Context?, title: String, mData: List<FlightDetailsItem>?) :
    BottomSheetDialogFragment() {

    var title: String = ""
    var mData: List<FlightDetailsItem>? = null
    var ctx: Context?

    var mResultFlightDetail: MutableList<FlightDetailsItem>? = null
    lateinit var mFlightDataAdapter: FlightDetailAdapter


    init {
        this.title = title
        this.mData = mData
        this.ctx = ctx
    }

    lateinit var binding: FragmentBottomSheetDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater,
            tour.traveling.travel.R.layout.fragment_bottom_sheet_dialog,
            container,
            false
        )

        binding.toolbar.toolbar.setTitle("" + title)

        setFlightDataAdapter()
        setFlightData(mData)


        return binding.root

    }

    @SuppressLint("WrongConstant")
    fun setFlightDataAdapter() {
        if (!::mFlightDataAdapter.isInitialized) {
            val layoutManager = LinearLayoutManager(ctx, LinearLayout.VERTICAL, false)
            val recyclerView = binding.recyclerFlight
            recyclerView.layoutManager = layoutManager

            mFlightDataAdapter = FlightDetailAdapter()
            recyclerView.adapter = mFlightDataAdapter
        }

    }

    private fun setFlightData(resp: List<FlightDetailsItem>?) {
        mResultFlightDetail = ArrayList(resp!!)
        mFlightDataAdapter.setData(mResultFlightDetail!!)
    }


}