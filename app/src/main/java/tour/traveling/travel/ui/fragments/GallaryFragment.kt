package tour.traveling.travel.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.accountapp.accounts.constants.BundleConstant
import tour.traveling.travel.R
import tour.traveling.travel.adpater.GallaryAdapter
import tour.traveling.travel.databinding.FragmentGallaryBinding
import tour.traveling.travel.model.response.PackageImagesItem
import tour.traveling.travel.model.response.ResultItemBanner
import tour.traveling.travel.ui.image.ImageViewerActivity
import java.io.Serializable

/**
 * A simple [Fragment] subclass.
 */
class GallaryFragment : Fragment(), GallaryAdapter.GalleryCallback {

    lateinit var binding: FragmentGallaryBinding
    lateinit var mGallaryAdapter: GallaryAdapter
    val mContext by lazy { context }
    var str: ResultItemBanner? = null
    var mResultGallery: MutableList<PackageImagesItem>? = null


    companion object {
        val TAG = "GallaryFragment"
        fun newInstance(args: Bundle?): GallaryFragment {
            val fragment = GallaryFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallary, container, false)

        str = arguments!!.getSerializable(BundleConstant.ITINENARY_DATA) as ResultItemBanner?

        gallaryAdapter()

        mGallaryAdapter.setViewCallback(this)

        setGalleryImages(str!!.package_images)

        return binding.root

    }

    private fun setGalleryImages(resp: List<PackageImagesItem>?) {
        mResultGallery = ArrayList(resp!!)
        mGallaryAdapter.setData(mResultGallery!!)
    }



    @SuppressLint("WrongConstant")
    fun gallaryAdapter() {
        if (!::mGallaryAdapter.isInitialized) {
            val layoutManager = GridLayoutManager(mContext, 4)
            val recyclerView = binding.recyclerGallery
            recyclerView.layoutManager = layoutManager

            mGallaryAdapter = GallaryAdapter()
            recyclerView.adapter = mGallaryAdapter
        }

    }

    override fun onGalleryCallback(pos: Int,imgName: String) {
        val intent = Intent(mContext, ImageViewerActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("pos", pos)
        intent.putExtra("list", str!!.package_images as Serializable?)
        intent.putExtra("image", str!!.package_images!!.get(pos))
        intent.putExtra("imageName", imgName)

        mContext!!.startActivity(intent)
    }


}
