package com.biggidroid.opaykotlin.pages.referraldata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.databinding.FragmentCompletedReferralBinding
import com.biggidroid.opaykotlin.databinding.FragmentEarnPageBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CompletedReferralFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CompletedReferralFragment : Fragment() {
    //binding
    private var _binding: FragmentCompletedReferralBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init view binding
        _binding = FragmentCompletedReferralBinding.inflate(inflater, container, false)
        //set content view
        return binding.root
    }

    //on destroy view
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}