package com.biggidroid.opaykotlin.pages.skeleton

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.databinding.ActivityAddCashBinding
import com.biggidroid.opaykotlin.databinding.FragmentSkeletonLoadingBinding
import com.biggidroid.opaykotlin.pages.HomeInnerPage

/**
 * A simple [Fragment] subclass.
 * Use the [SkeletonLoading.newInstance] factory method to
 * create an instance of this fragment.
 */
class SkeletonLoading : Fragment() {
    //binding
    private var _binding: FragmentSkeletonLoadingBinding? = null

    //appContext
    private lateinit var appContext: Context

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate layout
        _binding = FragmentSkeletonLoadingBinding.inflate(inflater, container, false)
        //return root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init app context
        appContext = requireContext().applicationContext

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}