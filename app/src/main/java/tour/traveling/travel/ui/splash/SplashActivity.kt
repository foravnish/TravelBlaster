package tour.traveling.travel.ui.splash

import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.accountapp.accounts.utils.Prefences
import com.accountapp.accounts.utils.Utility
import tour.traveling.travel.R
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.databinding.ActivityMainBinding
import tour.traveling.travel.ui.home.HomeActivity
import tour.traveling.travel.ui.login.LoginActivity

class SplashActivity : BaseActivity() ,SplashHandler{

    lateinit var binding: ActivityMainBinding
    internal var mSplashRepo = SplashRepository(this)
    val mContext by lazy { this@SplashActivity }


    override fun initUI() {

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        mSplashRepo.onSplashInitiated()


    }

    override fun onSplashCompleted() {
        val isUserLoggedIn = Prefences.getIsLogin(mContext)

        if (isUserLoggedIn)
            Utility.startActivityWithLeftToRightAnimation(this, Intent(this, HomeActivity::class.java))
        else
            Utility.startActivityWithLeftToRightAnimation(
                this,
                Intent(this, LoginActivity::class.java)
            )
        finish()
    }

}
