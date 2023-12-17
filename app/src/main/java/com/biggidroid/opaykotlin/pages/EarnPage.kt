package com.biggidroid.opaykotlin.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.databinding.FragmentEarnPageBinding

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
        //do other stuff here
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}