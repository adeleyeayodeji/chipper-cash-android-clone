package com.biggidroid.opaykotlin.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.databinding.FragmentWelcomePageBinding

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomePageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomePageFragment : Fragment() {

    private var _binding: FragmentWelcomePageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate layout
        _binding = FragmentWelcomePageBinding.inflate(inflater, container, false)
        //return root
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}