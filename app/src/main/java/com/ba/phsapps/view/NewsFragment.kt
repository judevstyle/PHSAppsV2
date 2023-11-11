package com.ba.phsapps.view

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import com.ba.phsapps.R
import com.ba.phsapps.databinding.FragmentNewsBinding
import com.ssoft.common.BaseFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : BaseFragment() {
    private lateinit var binding: FragmentNewsBinding

    //    private var progress: Progress? = null
    private var isLoaded: Boolean = false
    private var doubleBackToExitPressedOnce = false
    private var webURL =
        "https://multimedia.anamai.moph.go.th/help-knowledge/categories/elderly/" // Change it with your URL


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        super.onViewReady(view, savedInstanceState)


        binding.backAction.setOnClickListener {

            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                findNavController()
                    .popBackStack()
            }


        }


        binding.webView.settings.javaScriptEnabled = true
        if (!isOnline()) {
//            showToast(getString(R.string.no_internet))
//            infoTV.text = getString(R.string.no_internet)
//            showNoNetSnackBar()
            return
        }

    }

    override fun onResume() {
        if (isOnline() && !isLoaded) loadWebView()
        super.onResume()
    }


    private fun loadWebView() {
//        infoTV.text = ""
        binding.webView.loadUrl(webURL)
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url.toString()
                view?.loadUrl(url)
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                setProgressDialogVisibility(true)
                showProgressDialog()
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                isLoaded = true
//                setProgressDialogVisibility(false)
                super.onPageFinished(view, url)
                hideDialog()
            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                isLoaded = false
                val errorMessage = "Got Error! $error"
                showToast(errorMessage)
//                infoTV.text = errorMessage
                hideDialog()
//                setProgressDialogVisibility(false)
                super.onReceivedError(view, request, error)
            }
        }
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
//        if (event.action == KeyEvent.ACTION_DOWN) {
//            if (keyCode == KeyEvent.KEYCODE_BACK) {
//                if (binding.webView.canGoBack()) {
//                    binding.webView.goBack()
//                } else {
//                    showToastToExit()
//                }
//                return true
//            }
//        }
//        return super.onKeyDown(keyCode, event)
//    }

//    private fun showToastToExit() {
//        when {
//            doubleBackToExitPressedOnce -> {
//                onBackPressed()
//            }
//            else -> {
//                doubleBackToExitPressedOnce = true
//                showToast(getString(R.string.back_again_to_exit))
//                Handler(Looper.myLooper()!!).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
//            }
//        }
//    }

//    private fun setProgressDialogVisibility(visible: Boolean) {
//        if (visible) progress = Progress(this, R.string.please_wait, cancelable = true)
//        progress?.apply { if (visible) show() else dismiss() }
//    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }

                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }

                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }


}