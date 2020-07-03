package tour.traveling.travel.ui.home


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.ui.home.HomeViewModel
import com.accountapp.accounts.utils.Prefences
import com.accountapp.accounts.utils.Utility
import tour.traveling.travel.R
import tour.traveling.travel.adpater.*
import tour.traveling.travel.base.BaseFragment
import tour.traveling.travel.constants.AppConstants
import tour.traveling.travel.databinding.FragmentHomeBinding
import tour.traveling.travel.model.response.*
import tour.traveling.travel.ui.listing.SeeAllListingActivity
import tour.traveling.travel.ui.login.LoginActivity
import tour.traveling.travel.ui.product.ProductDetailActivity


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() , HotPlacesAdapter.ProductCallback {
    override fun getFragmentTag(): String {
        return TAG
    }

    val TAG = "FragmentHome"

    companion object {
        val TAG = "FragmentHome"

        fun newInstance(args: Bundle?): HomeFragment {
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
    lateinit var mHotPlaceAdapter: HotPlacesAdapter
    lateinit var mcategoryAdapter: CategoryAdapter
    lateinit var mitinanryAdapter: ItineraryAdapter
    lateinit var mflightAdapter: FlightAdapter
    lateinit var mhotelAdapter: HotelAdapter
    lateinit var minsuranceAdapter: InsuranceAdapter
    lateinit var mvisaAdapter: VisaAdapter

    lateinit var binding: FragmentHomeBinding
    val mContext by lazy { context }
    val mViewModel by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
    private var NUM_PAGES = 0
    internal var position: Int = 0
    private var handler = Handler()
    internal val PERIOD_MS: Long = 3 * 1000 // time in milliseconds between successive task executions.

    var mResultHotePlace: MutableList<ResultItemBanner>? = null
    var mResultItinenary: MutableList<DetailsItem>? = null
    var mResultCategory: MutableList<ResultItemCategory>? = null



    private val runnale = object : Runnable {
        override fun run() {
            binding.viewpager.setCurrentItem(position, true)
            if (position >= NUM_PAGES)
                position = 0
            else
                position++
            // Move to the next page after 3s
            handler.postDelayed(this, PERIOD_MS)
        }
    }
    public override fun onPause() {
        super.onPause()
        handler?.removeCallbacks(runnale)
    }

    override fun onResume() {
        super.onResume()
        AppConstants.iteData.clear()
        handler.postDelayed(runnale, PERIOD_MS)

    }



    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val name=Prefences.getUserName(mContext).toString()

        binding.txtName.setText("Hi "+name.substring(0, 1).toUpperCase()+  name.substring(1)+",")

//        activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS )

        binding.layoutHotPlce.visibility=View.GONE

        callBannerApi()
//        callCategory()

        setHotPlacesAdapter()
        setCategoryAdapter()
        setItinenaryAdapter()
        setFlightAdapter()
        setHotelAdapter()
        setInsuranceAdapter()
        setVisaAdapter()


        mHotPlaceAdapter.setViewCallback(this)


//        mHotPlaceAdapter.setData()
//        mcategoryAdapter.setData()
//        mitinanryAdapter.setData()
//        mflightAdapter.setData()
//        mhotelAdapter.setData()
//        minsuranceAdapter.setData()
//        mvisaAdapter.setData()


        binding.txtHotPlace.setOnClickListener { callListingPage(binding.lblHotPlace.text.toString())}
        binding.txtCatagory.setOnClickListener { callListingPage(binding.lblCat.text.toString()) }
        binding.txtitirarary.setOnClickListener {  callListingPage(binding.lblitir.text.toString())}
        binding.txtFlight.setOnClickListener {  callListingPage(binding.lblFlight.text.toString())}
        binding.txtHotel.setOnClickListener {  callListingPage(binding.lblHotel.text.toString())}
        binding.txtVisa.setOnClickListener {  callListingPage(binding.lblVisa.text.toString())}
        binding.txtInsurance.setOnClickListener {  callListingPage(binding.lblInsurance.text.toString())}

        return binding.root


    }

    private fun callListingPage(type: String) {

        val intent = Intent(activity, SeeAllListingActivity::class.java)
        intent.putExtra(BundleConstant.HOME_SCREEN_TYPE,type)
        Utility.startActivityWithLeftToRightAnimation(activity,intent)
    }


//    private fun setNewArrival(resultFeatureStore: List<NewArrivalsItem>?) {
//        mResultNewArrival = ArrayList(resultFeatureStore)
//    }

    @SuppressLint("WrongConstant")
    fun setHotPlacesAdapter() {
        if (!::mHotPlaceAdapter.isInitialized) {
            val layoutManager = LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false)
            val recyclerView = binding.recyclerViewImages
            recyclerView.layoutManager = layoutManager

            mHotPlaceAdapter = HotPlacesAdapter()
            recyclerView.adapter = mHotPlaceAdapter
        }

    }


    @SuppressLint("WrongConstant")
    fun setCategoryAdapter() {
        if (!::mcategoryAdapter.isInitialized) {
            val layoutManager = LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false)
            val recyclerView = binding.recyclerCategory
            recyclerView.layoutManager = layoutManager

            mcategoryAdapter = CategoryAdapter()
            recyclerView.adapter = mcategoryAdapter
        }

    }


    @SuppressLint("WrongConstant")
    fun setItinenaryAdapter() {
        if (!::mitinanryAdapter.isInitialized) {
            val layoutManager = LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false)
            val recyclerView = binding.recyclerIninary
            recyclerView.layoutManager = layoutManager

            mitinanryAdapter = ItineraryAdapter()
            recyclerView.adapter = mitinanryAdapter
        }

    }


    @SuppressLint("WrongConstant")
    fun setFlightAdapter() {
        if (!::mflightAdapter.isInitialized) {
            val layoutManager = LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false)
            val recyclerView = binding.recyclerFlight
            recyclerView.layoutManager = layoutManager

            mflightAdapter = FlightAdapter()
            recyclerView.adapter = mflightAdapter
        }

    }

    @SuppressLint("WrongConstant")
    fun setHotelAdapter() {
        if (!::mhotelAdapter.isInitialized) {
            val layoutManager = LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false)
            val recyclerView = binding.recyclerHotel
            recyclerView.layoutManager = layoutManager

            mhotelAdapter = HotelAdapter()
            recyclerView.adapter = mhotelAdapter
        }

    }

    @SuppressLint("WrongConstant")
    fun setInsuranceAdapter() {
        if (!::minsuranceAdapter.isInitialized) {
            val layoutManager = LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false)
            val recyclerView = binding.recyclerInsurance
            recyclerView.layoutManager = layoutManager

            minsuranceAdapter = InsuranceAdapter()
            recyclerView.adapter = minsuranceAdapter
        }

    }

    @SuppressLint("WrongConstant")
    fun setVisaAdapter() {
        if (!::mvisaAdapter.isInitialized) {
            val layoutManager = LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false)
            val recyclerView = binding.recyclerVisa
            recyclerView.layoutManager = layoutManager

            mvisaAdapter = VisaAdapter()
            recyclerView.adapter = mvisaAdapter
        }

    }

    // Banner Adapter

    private fun callBannerApi() {
        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)
        mViewModel.callBannerListing()
            .observe(this, object : Observer<BannerListingResponce> {
                override fun onChanged(resp: BannerListingResponce?) {
                    if (resp != null && resp.status.equals("success")) {

                        binding.viewpager.adapter =
                            BannerPagerAdapter(mContext, resp.result)
                        binding.indicator.setViewPager(binding.viewpager)

                        NUM_PAGES = resp.result!!.size

                        callHotPlacesApi()

                    } else {
//                        if (isInternetAvailable(binding.root, this)) {
//                            showServerErrorSnackbar(binding.root)
//                        }
                    }
                }
            })
    }

    private fun callHotPlacesApi() {
        mViewModel.callHotPlaces()
            .observe(this, object : Observer<HotPlaceResponce> {
                override fun onChanged(resp: HotPlaceResponce?) {
                    if (resp != null && resp.status.equals("success")) {
                        if (resp.result!!.size > 0){


                            binding.layoutHotPlce.visibility=View.VISIBLE
                            setHotePlaces(resp.result)

                            callCategory()
                        }else{
                            Utility.showSnackBar(binding.root,"No Data available.")
                        }


                    }
                }
            })

    }

    private fun callCategory() {

        mViewModel.callCategory()
            .observe(this, object : Observer<CategoryResponce> {
                override fun onChanged(resp: CategoryResponce?) {
                    showLoadingView(false, binding.loadingView.loadingIndicator, binding.loadingView.container)
                    if (resp != null && resp.status.equals("success")) {
                        if (resp.result!!.size > 0){

                            binding.layoutCategory.visibility = View.VISIBLE
                            setCategory(resp.result)    // Caregory Listing for circle menu

                            try {
                                if (resp.result!!.get(4).details!!.size > 0) {
                                    binding.lblVisa.setText(""+resp.result!!.get(4).category)
                                    binding.layoutVisa.visibility = View.VISIBLE

                                    setVisa(resp.result.get(4).details)    //4 Index for Visa
                                }else{
                                    binding.layoutVisa.visibility = View.GONE
                                }
                            } catch (e: Exception) {
                                binding.layoutVisa.visibility = View.GONE

                            }


                            try {
                                if (resp.result!!.get(3).details!!.size > 0) {
                                    binding.lblInsurance.setText(""+resp.result!!.get(3).category)
                                    binding.layoutInsurance.visibility = View.VISIBLE

                                    setInsurance(resp.result.get(3).details)    //3 Index for Insurance
                                }else{
                                    binding.layoutInsurance.visibility = View.GONE
                                }
                            } catch (e: Exception) {
                                binding.layoutInsurance.visibility = View.GONE

                            }



                            try {
                                if (resp.result!!.get(2).details!!.size > 0) {
                                    binding.lblitir.setText(""+resp.result!!.get(2).category)
                                    binding.layoutItinary.visibility = View.VISIBLE

                                    setItinenary(resp.result.get(2).details)    //2 Index for Itinenary
                                }else{
                                    binding.layoutItinary.visibility = View.GONE
                                }
                            } catch (e: Exception) {
                                binding.layoutItinary.visibility = View.GONE

                            }


                            try {
                                if (resp.result!!.get(1).details!!.size > 0) {
                                    binding.lblFlight.setText(""+resp.result!!.get(1).category)
                                    binding.layoutFlight.visibility = View.VISIBLE

                                    setFlight(resp.result.get(1).details)    //1 Index for Flight
                                }else{
                                    binding.layoutFlight.visibility = View.GONE
                                }
                            } catch (e: Exception) {
                                binding.layoutFlight.visibility = View.GONE

                            }

                            try {
                                if (resp.result!!.get(0).details!!.size > 0) {
                                    binding.lblHotel.setText(""+resp.result!!.get(0).category)
                                    binding.layoutHotel.visibility = View.VISIBLE

                                    setHotel(resp.result.get(0).details)    //0 Index for Hotel
                                }else{
                                    binding.layoutHotel.visibility = View.GONE
                                }
                            } catch (e: Exception) {
                                binding.layoutHotel.visibility = View.GONE

                            }

                        }else{
                            Utility.showSnackBar(binding.root,"No Data available.")
                        }


                    }
                }
            })

    }

    private fun setHotePlaces(resp: List<ResultItemBanner>?) {
        mResultHotePlace = ArrayList(resp!!)
        mHotPlaceAdapter.setData(mResultHotePlace!!)
    }


    private fun setCategory(resp: List<ResultItemCategory>? ) {
        mResultCategory = ArrayList(resp!!)
        mcategoryAdapter.setData(mResultCategory!!)
    }

    private fun setVisa(resp: List<DetailsItem>? ) {
        mResultItinenary = ArrayList(resp!!)
        mvisaAdapter.setData(mResultItinenary!!)
    }

    private fun setInsurance(resp: List<DetailsItem>? ) {
        mResultItinenary = ArrayList(resp!!)
        minsuranceAdapter.setData(mResultItinenary!!)
    }

    private fun setItinenary(resp: List<DetailsItem>? ) {
        mResultItinenary = ArrayList(resp!!)
        mitinanryAdapter.setData(mResultItinenary!!)
    }

    private fun setFlight(resp: List<DetailsItem>? ) {
        mResultItinenary = ArrayList(resp!!)
        mflightAdapter.setData(mResultItinenary!!)
    }

    private fun setHotel(resp: List<DetailsItem>? ) {
        mResultItinenary = ArrayList(resp!!)
        mhotelAdapter.setData(mResultItinenary!!)
    }

    private fun openLogoutDialog() {

        val dialogBuilder = AlertDialog.Builder(context)

        // set message of alert dialog
        dialogBuilder.setMessage("Do you want to Logout?")
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()

                Prefences.resetUserData(mContext!!)
                val intent = Intent(mContext, LoginActivity::class.java)
                Utility.startActivityWithLeftToRightAnimation(activity,intent)
                activity!!.finishAffinity()

            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        alert.show()



    }


    override fun onProductCallback(p_id: String) {
        Utility.startActivityWithLeftToRightAnimation(activity, Intent(mContext, ProductDetailActivity::class.java))

    }



}
