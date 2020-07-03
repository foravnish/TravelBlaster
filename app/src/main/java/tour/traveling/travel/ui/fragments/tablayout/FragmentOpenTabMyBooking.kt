package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.utils.Prefences
import com.accountapp.accounts.utils.Utility
import com.mooveall.ui.fragment.FindJobsViewModel
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import tour.traveling.travel.R
import tour.traveling.travel.adpater.FindCompleteAdapter
import tour.traveling.travel.adpater.FindJCancelAdapter
import tour.traveling.travel.adpater.FindJOpenAdapter
import tour.traveling.travel.base.BaseFragment
import tour.traveling.travel.databinding.FragmentChatBinding
import tour.traveling.travel.model.response.*
import tour.traveling.travel.utils.EndlessRecyclerViewScrollListener


/**
 */
class FragmentOpenTabMyBooking : BaseFragment(),
    FindJOpenAdapter.ItemClick, FindJCancelAdapter.ItemClick,
    FindCompleteAdapter.ItemClick/*, SwipeRefreshLayout.OnRefreshListener*/ {

    lateinit var binding: FragmentChatBinding
    val mContext by lazy { context }
    lateinit var mOpenAdapter: FindJOpenAdapter
    lateinit var mCencelAdapter: FindJCancelAdapter
    lateinit var mCompleteAdapter: FindCompleteAdapter
    var mResultResultHistory: List<ResultItemHistory>? = null
    var mResultOpen: List<ResultItemHistory>? = null
    var mResultCancel: List<ResultItemHistory>? = null
    var mResultComplete: List<ResultItemHistory>? = null
    var pageNo = 1
    val mViewModel by lazy { ViewModelProviders.of(this).get(FindJobsViewModel::class.java) }
    //    private var scrollListener: EndlessRecyclerViewScrollListener? = null
    var jobType = ""
    private var scrollListener: EndlessRecyclerViewScrollListener? = null


    companion object {
        val TAG = "FragmentOpenTabMyBooking"
        fun newInstance(args: Bundle?): FragmentOpenTabMyBooking {
            val fragment = FragmentOpenTabMyBooking()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getFragmentTag(): String {
        return TAG
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)
        binding.includedToolbar.toolbar.setTitle("My Booking")

//        binding.swiperefresh.setColorSchemeResources(R.color.colorPrimary)
//        binding.swiperefresh.setOnRefreshListener(this)

//        jobType = arguments!!.getString(BundleConstant.JOB_TYPE)!!

        setOpenAdapter()
//        setCancelAdapter()
//        setCompleteAdapter()

//        if (getUserVisibleHint()) { // fragment is visible
        getMyBookingHistory()
//        }

        return binding.root
    }


    private fun setOpenHistory(resp: List<ResultItemHistory>?) {
        mResultResultHistory = ArrayList(resp!!)
        mOpenAdapter.setData(mResultResultHistory!!)
    }

    private fun setCencelHistory(resp: List<ResultItemHistory>?) {
        mResultResultHistory = ArrayList(resp!!)
        mCencelAdapter.setData(mResultResultHistory!!)
    }

    private fun setCompleteHistory(resp: List<ResultItemHistory>?) {
        mResultResultHistory = ArrayList(resp!!)
        mCompleteAdapter.setData(mResultResultHistory!!)
    }

//    override fun onRefresh() {
//        pageNo = 1
//        getTaskList()
//        if (scrollListener != null)
//            scrollListener!!.resetState()
//    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isResumed()/* && mResult == null*/) {
            // fragment is visible and have created
//            getTaskList()
        }
    }

//    override fun onRefresh() {
//    }


    private fun setOpenAdapter() {
        val layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        val recyclerView = binding.chatRecyclerView
        recyclerView.layoutManager = layoutManager

        mOpenAdapter = FindJOpenAdapter()
        mOpenAdapter.setViewCallback(this)
        //set job type
//        mOpenAdapter.mJobType = jobType

        recyclerView.adapter = mOpenAdapter
        recyclerView.setHasFixedSize(true)

        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                pageNo = page
            }
        }
        recyclerView.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)

    }

    private fun setCancelAdapter() {
        val layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        val recyclerView = binding.chatRecyclerView
        recyclerView.layoutManager = layoutManager

        mCencelAdapter = FindJCancelAdapter()
        mCencelAdapter.setViewCallback(this)
        //set job type
//        mCencelAdapter.mJobType = jobType

        recyclerView.adapter = mCencelAdapter
        recyclerView.setHasFixedSize(true)

        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                pageNo = page
            }
        }
        recyclerView.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)

    }


    private fun setCompleteAdapter() {
        val layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        val recyclerView = binding.chatRecyclerView
        recyclerView.layoutManager = layoutManager

        mCompleteAdapter = FindCompleteAdapter()
        mCompleteAdapter.setViewCallback(this)
//        mCompleteAdapter.mJobType = jobType

        recyclerView.adapter = mCompleteAdapter
        recyclerView.setHasFixedSize(true)

        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                pageNo = page
            }
        }
        recyclerView.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)

    }

    override fun onItemClick(position: Int) {

    }


    private fun getMyBookingHistory() {

        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)
        mViewModel.callHistory("" + Prefences.getUserId(mContext))
            .observe(this, object : Observer<BookingHistoryResponse> {
                override fun onChanged(resp: BookingHistoryResponse?) {
                    showLoadingView(
                        false,
                        binding.loadingView.loadingIndicator,
                        binding.loadingView.container
                    )
                    if (resp != null) {
                        if (resp.status.equals("success")) {
                            binding.emptyViewContainer.visibility=View.GONE
                            setOpenHistory(resp.result)

                        } else {
                            binding.emptyViewContainer.visibility = View.VISIBLE
//                            Utility.showSnackBar(binding.root, "" + resp.message)
                        }
                    } else {
//                        showServerErrorSnackbar(binding.root)
                    }
                }

            })

    }

}
