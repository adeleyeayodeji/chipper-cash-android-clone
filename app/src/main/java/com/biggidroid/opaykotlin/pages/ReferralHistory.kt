package com.biggidroid.opaykotlin.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.adapter.ViewPagerAdapter
import com.biggidroid.opaykotlin.databinding.FragmentReferralHistoryBinding
import com.biggidroid.opaykotlin.databinding.ReferralHistoryTabItemBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass.
 * Use the [ReferralHistory.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReferralHistory : Fragment() {

    //binding
    private var _binding: FragmentReferralHistoryBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init view binding
        _binding = FragmentReferralHistoryBinding.inflate(inflater, container, false)
        //set content view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set on click listener
        setOnclickListener()

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.adapter = ViewPagerAdapter(requireActivity())

        //pass this as the value pending and completed
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Pending"
                else -> "Completed"
            }
        }.attach()

//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            // Inflate the custom tab layout view
//            val binding = ReferralHistoryTabItemBinding.inflate(layoutInflater, tabLayout, false)
//
//            tab.customView = binding.root
//            binding.tab.text = when (position) {
//                0 -> "Pending"
//                else -> "Completed"
//            }
//        }.attach()

    }

    private fun setOnclickListener() {
        binding.closeIconUserPage.setOnClickListener {
            //fragment pop back stack
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}