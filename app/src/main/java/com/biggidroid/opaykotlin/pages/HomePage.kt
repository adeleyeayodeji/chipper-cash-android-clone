package com.biggidroid.opaykotlin.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.databinding.FragmentHomePageBinding

class HomePage : Fragment() {
    private var _binding: FragmentHomePageBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init home inner page
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_home, HomeInnerPage())
        }

        //bottom_navigation_view
        binding.fragmentHomePageContainer.bottomNavigationView.setOnItemSelectedListener { item ->
            handleNavigationSelection(item)
        }
    }

    private fun handleNavigationSelection(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_home -> {
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(R.id.fragment_container_home, HomeInnerPage())
                }
                true
            }

            R.id.navigation_earn -> {
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(R.id.fragment_container_home, EarnPage())
                }
                true
            }

            R.id.navigation_invest -> {
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(R.id.fragment_container_home, InvestPage())
                }
                true
            }

            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}