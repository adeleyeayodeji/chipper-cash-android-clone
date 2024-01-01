package com.biggidroid.opaykotlin.parts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.databinding.FragmentEarnPageBinding
import com.biggidroid.opaykotlin.databinding.FragmentWebViewBinding

/**
 * A simple [Fragment] subclass.
 * Use the [WebViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WebViewFragment : Fragment() {
    //binding
    private var _binding: FragmentWebViewBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init view binding
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        //set content view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString("url")
        binding.webView.loadUrl(url!!)

        //set on click listener
        setOnclikListener()
    }

    private fun setOnclikListener() {
        //close_icon_webview_page
        binding.closeIconWebviewPage.setOnClickListener {
            // Pop the current fragment off the back stack, returning to the previous one.
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        fun newInstance(url: String): WebViewFragment {
            val fragment = WebViewFragment()
            val args = Bundle()
            args.putString("url", url)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}