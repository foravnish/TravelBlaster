package tour.traveling.travel.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.FragmentOpenTabMyBooking
import fragments.FragmentSettings
import tour.traveling.travel.R
import tour.traveling.travel.base.BaseFragment
import tour.traveling.travel.base.BaseFragmentActivity
import tour.traveling.travel.databinding.ActivityHomeBinding
import tour.traveling.travel.ui.notification.FragmentNotification

class HomeActivity : BaseFragmentActivity() {
    override fun getFragmentContainer(): Int {
        return R.id.container
    }

    override fun onFragmentInteraction(tag: String) {
    }

    override fun popFrag(tag: String) {
    }

    lateinit var binding: ActivityHomeBinding
    val mContext by lazy { this@HomeActivity }
    var TAG: String = "Tag"
    var currentlyLoadedFragment = ""

    override fun initUI() {
        binding= DataBindingUtil.setContentView(this, R.layout.activity_home)
//        setSupportActionBar(binding.toolbar)

//        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS ,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS )

        TAG = HomeFragment.TAG
        currentlyLoadedFragment = TAG
        showFragment(TAG, null, true, true)


        val mOnNavigationItemSelectedListener =
            object : BottomNavigationView.OnNavigationItemSelectedListener {

                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    when (item.itemId) {
                        R.id.navigation_home -> {
                            if (currentlyLoadedFragment != HomeFragment.TAG) {
                                TAG = HomeFragment.TAG
                                currentlyLoadedFragment = TAG

                                showFragment(TAG, null, true, true)
                                //supportActionBar!!.setTitle("Home")
                            }
                            return true
                        }
                        R.id.navigation_booking -> {
                            if (currentlyLoadedFragment != FragmentOpenTabMyBooking.TAG) {
                                TAG = FragmentOpenTabMyBooking.TAG
                                currentlyLoadedFragment = TAG

                                showFragment(TAG, null, true, true)
                                // supportActionBar!!.setTitle("Ledger")
                            }
                            return true
                        }
                        R.id.navigation_ecahs -> {
                            if (currentlyLoadedFragment != FragmentNotification.TAG) {
                                TAG = FragmentNotification.TAG
                                currentlyLoadedFragment = TAG

                                showFragment(TAG, null, true, true)
                                //supportActionBar!!.setTitle("Trail Balance")
                            }
                            return true
                        }
                        R.id.navigation_notification -> {
                            if (currentlyLoadedFragment != FragmentNotification.TAG) {
                                TAG = FragmentNotification.TAG
                                currentlyLoadedFragment = TAG

                                showFragment(TAG, null, true, true)
                                //supportActionBar!!.setTitle("Trail Balance")
                            }
                            return true
                        }

                        R.id.navigation_setting -> {
                            if (currentlyLoadedFragment != FragmentSettings.TAG) {
                                TAG = FragmentSettings.TAG
                                currentlyLoadedFragment = TAG

                                showFragment(TAG, null, true, true)
                            }

                            return true
                        }
                    }

                    return false
                }
            }


        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }


    override fun showFragment(tag: String, args: Bundle?, addToBackStack: Boolean, replace: Boolean) {
        if (!isFinishing) {
            getFragmentByTag(tag, args)?.let { loadFragments(it, addToBackStack, replace, tag) }
        }

    }
    protected fun getFragmentByTag(tag: String, args: Bundle?): BaseFragment? {
        var frag: BaseFragment? = null
        when (tag) {
            HomeFragment.TAG -> {
                frag = HomeFragment.newInstance(args)
            }
            FragmentOpenTabMyBooking.TAG -> {
                frag = FragmentOpenTabMyBooking.newInstance(args)
            }
            FragmentNotification.TAG -> {
                frag = FragmentNotification.newInstance(args)
            }
            FragmentNotification.TAG -> {
                frag = FragmentNotification.newInstance(args)
            }
            FragmentSettings.TAG -> {
                frag = FragmentSettings.newInstance(args)
            }
        }

        return frag
    }


}
