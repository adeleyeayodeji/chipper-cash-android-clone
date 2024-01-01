package com.biggidroid.opaykotlin.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.biggidroid.opaykotlin.pages.ReferralHistory
import com.biggidroid.opaykotlin.pages.referraldata.CompletedReferralFragment
import com.biggidroid.opaykotlin.pages.referraldata.PendingReferralFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2 // number of tabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PendingReferralFragment()
            else -> CompletedReferralFragment()
        }
    }
}