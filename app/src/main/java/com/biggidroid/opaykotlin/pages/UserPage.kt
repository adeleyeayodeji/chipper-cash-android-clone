package com.biggidroid.opaykotlin.pages

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.databinding.ActivityAddCashBinding
import com.biggidroid.opaykotlin.databinding.ActivityUserPageBinding

class UserPage : Fragment() {
    //binding
    private var _binding: ActivityUserPageBinding? = null

    //appContext
    private lateinit var appContext: Context

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    /**
     * Oncreate view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate layout
        _binding = ActivityUserPageBinding.inflate(inflater, container, false)
        //return root
        return binding.root
    }

    /**
     * Onview created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init app context
        appContext = requireContext().applicationContext

        //set on click listener
        setOnClickListener()
    }

    //set on click listener
    private fun setOnClickListener() {
        binding.closeIconUserPage.setOnClickListener {
            // Pop the current fragment off the back stack, returning to the previous one.
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}