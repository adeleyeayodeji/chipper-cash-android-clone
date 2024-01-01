package com.biggidroid.opaykotlin.pages

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.databinding.FragmentEarnPageBinding
import com.biggidroid.opaykotlin.parts.WebViewFragment

/**
 * A simple [Fragment] subclass.
 * Use the [EarnPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class EarnPage : Fragment() {
    //binding
    private var _binding: FragmentEarnPageBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init view binding
        _binding = FragmentEarnPageBinding.inflate(inflater, container, false)
        //set content view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set on click listener
        setOnclikListener()
    }

    private fun setOnclikListener() {
        binding.earnButtonInviteFriends.setOnClickListener {
             //share invite link
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Hey, I'm using BiggiPay, you should too! Use my code 3JXJZD to sign up and earn 50 NGN when you do. Download the app from https://operapay.page.link/invite")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        //earn_button_enter_referral_code
        binding.earnButtonEnterReferralCode.setOnClickListener {
            //navigate to enter referral code page

            // Get the fragment from the fragment manager
            val enterReferralCode =
                requireActivity().supportFragmentManager.findFragmentByTag("enterReferralCode")

            // Begin the transaction
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            // Set the transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

            //hide any fragment that is visible
            val earnPage =
                requireActivity().supportFragmentManager.findFragmentByTag("earnPage")
            if (earnPage != null) {
                transaction.hide(earnPage)
            }

            if (enterReferralCode == null) {
                // If the fragment is not already added, add it
                transaction.add(R.id.fragment_container_home, EnterReferralCode(), "enterReferralCode")
            } else {
                // If the fragment is already added, show it
                transaction.show(enterReferralCode)
            }

            // Add the transaction to the back stack and commit
            transaction.addToBackStack("enterReferralCode")
            transaction.commit()
        }

        //earn_text_view_terms_and_conditions
        binding.earnTextViewTermsAndConditions.setOnClickListener {
            //open terms and conditions page as a webview
            val link = "https://www.biggidroid.com/about/"
            val webViewFragmentInstance = WebViewFragment.newInstance(link)

            // Get the fragment from the fragment manager
            val webViewFragment =
                requireActivity().supportFragmentManager.findFragmentByTag("webViewFragment")

            // Begin the transaction
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            // Set the transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

            if (webViewFragment == null) {
                // If the fragment is not already added, add it
                transaction.add(R.id.fragment_container, webViewFragmentInstance, "webViewFragment")
            } else {
                // If the fragment is already added, show it
                transaction.show(webViewFragment)
            }

            // Add the transaction to the back stack and commit
            transaction.addToBackStack("webViewFragment")
            transaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}