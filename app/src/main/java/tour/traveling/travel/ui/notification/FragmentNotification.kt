package tour.traveling.travel.ui.notification

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.layout_toolbar.view.*

import tour.traveling.travel.R
import tour.traveling.travel.adpater.NotificationAdapter
import tour.traveling.travel.base.BaseFragment
import tour.traveling.travel.databinding.FragmentNofificationBinding

/**
 * A simple [Fragment] subclass.
 */
class FragmentNotification : BaseFragment() {

    lateinit var binding: FragmentNofificationBinding
    lateinit var mNotificationAdapter: NotificationAdapter
    val mContext by lazy { context }


    companion object {
        val TAG = "FragmentNotification"
        fun newInstance(args: Bundle?): FragmentNotification {
            val fragment = FragmentNotification()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getFragmentTag(): String {
        return FragmentNotification.TAG
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nofification, container, false)
//        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN )
        binding.includedToolbar.toolbar.setTitle("Notifications")


        notificationAdapter()

        mNotificationAdapter.setData()

        return binding.root



    }
    @SuppressLint("WrongConstant")
    fun notificationAdapter() {
        if (!::mNotificationAdapter.isInitialized) {
            val layoutManager = GridLayoutManager(mContext, 1)
            val recyclerView = binding.recyclerGallery
            recyclerView.layoutManager = layoutManager

            mNotificationAdapter = NotificationAdapter()
            recyclerView.adapter = mNotificationAdapter
        }

    }


}
