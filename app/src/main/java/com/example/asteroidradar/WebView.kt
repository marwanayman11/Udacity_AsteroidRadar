package com.example.asteroidradar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.asteroidradar.databinding.FragmentWebViewBinding

class WebView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentWebViewBinding.inflate(layoutInflater)
        val myWebView: WebView = binding.webview
        val url = WebViewArgs.fromBundle(requireArguments()).url
        myWebView.loadUrl(url)
        return binding.root
    }

}