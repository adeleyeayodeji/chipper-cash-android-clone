package com.biggidroid.opaykotlin.pages

import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.databinding.FragmentHomePageBinding


class HomePage : Fragment() {
    private var _binding: FragmentHomePageBinding? = null
    private var currentFragment: Fragment? = null

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

        //bottom_navigation_view
        binding.fragmentHomePageContainer.bottomNavigationView.setOnItemSelectedListener { item ->
            handleNavigationSelection(item)
        }

        // Restore selected item ID
        if (savedInstanceState != null) {
            binding.fragmentHomePageContainer.bottomNavigationView.selectedItemId =
                savedInstanceState.getInt("selectedItemId")
        } else {
            // Trigger the first index on bottomnavigationview
            binding.fragmentHomePageContainer.bottomNavigationView.selectedItemId =
                R.id.navigation_home
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(
            "selectedItemId",
            binding.fragmentHomePageContainer.bottomNavigationView.selectedItemId
        )
    }

    private fun findFragmentByTag(s: String): Fragment? {
        return parentFragmentManager.findFragmentByTag(s)
    }

    private fun handleNavigationSelection(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_home -> {
                val homeFragment = findFragmentByTag("home")
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    currentFragment?.let { hide(it) }
                    if (homeFragment == null) {
                        currentFragment = HomeInnerPage()
                        add(R.id.fragment_container_home, currentFragment!!, "home")
                    } else {
                        currentFragment = homeFragment
                        show(homeFragment)
                    }
                }
                true
            }

            R.id.navigation_earn -> {
                val earnFragment = findFragmentByTag("earn")
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    currentFragment?.let { hide(it) }
                    if (earnFragment == null) {
                        currentFragment = EarnPage()
                        add(R.id.fragment_container_home, currentFragment!!, "earn")
                    } else {
                        currentFragment = earnFragment
                        show(earnFragment)
                    }
                }
                true
            }

            R.id.navigation_invest -> {
                val investFragment = findFragmentByTag("invest")
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    currentFragment?.let { hide(it) }
                    if (investFragment == null) {
                        currentFragment = InvestPage()
                        add(R.id.fragment_container_home, currentFragment!!, "invest")
                    } else {
                        currentFragment = investFragment
                        show(investFragment)
                    }
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

    override fun onResume() {
        super.onResume()
        currentFragment?.let {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                show(it)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.e("currentFragment", "Pause")
    }
}