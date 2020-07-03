package tour.traveling.travel.ui.webview

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.accountapp.accounts.constants.BundleConstant
import tour.traveling.travel.R
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.databinding.ActivityWebViewBinding

class WebViewActivity : BaseActivity() {
    var title = ""
    var webUrl = ""
    lateinit var binding: ActivityWebViewBinding
    override fun initUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        title = intent.getStringExtra(BundleConstant.TITLE)
        webUrl = intent.getStringExtra(BundleConstant.WEB_URL)
        setToolbarWithBackIcon(binding.toolbar, title)
        showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container)
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl(webUrl)
        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                showLoadingView(false, binding.loadingView.loadingIndicator, binding.loadingView.container)
            }
        }
    }
}
