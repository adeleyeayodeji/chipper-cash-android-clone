package com.biggidroid.opaykotlin.pages

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.biggidroid.opaykotlin.MainActivity
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.databinding.FragmentHomePageBinding
import org.json.JSONObject


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

        //set investment page as default
//        setSelectedItemId(R.id.navigation_earn)
    }

    /**
     * Save selected bottom navigation item
     * @param outState
     *
     * @return void
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(
            "selectedItemId",
            binding.fragmentHomePageContainer.bottomNavigationView.selectedItemId
        )
    }

    /**
     * Save selected bottom navigation item to shared preference
     * @param selectedBottomItemId
     * @param name
     *
     * @return void
     */
    private fun saveSelectedBottomItemId(selectedBottomItemId: Int, name: String = "home") {
        try {
            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
            //create a json object for name and selectedBottomItemId
            val jsonObject = JSONObject()
            val innerObject = JSONObject()
            innerObject.put("name", name)
            innerObject.put("selectedBottomItemId", selectedBottomItemId)
            jsonObject.put("savednav", innerObject)
            //save the json object to shared preference
            with(sharedPref.edit()) {
                putString("selectedBottomItemId", jsonObject.toString())
                apply()
            }
            //get the saved json object from shared preference
            val savedJson = sharedPref.getString("selectedBottomItemId", "")
            //log the saved json object
            Log.e("savedJson", savedJson.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun findFragmentByTag(s: String): Fragment? {
        return parentFragmentManager.findFragmentByTag(s)
    }

    private fun handleNavigationSelection(item: MenuItem): Boolean {
        // Handle navigation selection
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
                // Save selected bottom item ID
                saveSelectedBottomItemId(item.itemId, "home")
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
                // Save selected bottom item ID
                saveSelectedBottomItemId(item.itemId, "earn")
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
                // Save selected bottom item ID
                saveSelectedBottomItemId(item.itemId, "invest")
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
        //Register the broadcast receiver
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver, IntentFilter(MainActivity.ACTION_MY_BROADCAST))
        //Load the current fragment
        currentFragment?.let {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                show(it)
            }
        }
    }

    //Set bottom navigation view selected item id
    fun setSelectedItemId(id: Int) {
        binding.fragmentHomePageContainer.bottomNavigationView.selectedItemId = id
    }

    override fun onPause() {
        super.onPause()
        //Unregister the broadcast receiver
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
//        Log.e("currentFragment", "Pause")
    }

    /**
     * Broadcast receiver
     *  - Handle the broadcast here
     *
     * @return void
     *
     * @see BroadcastReceiver
     */
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == MainActivity.ACTION_MY_BROADCAST) {
                // Handle the broadcast here
                //get selectedItemId
                val selectedItemId = intent.getIntExtra("selectedItemId", 0)
                //set bottom navigation view selected item id
                setSelectedItemId(selectedItemId)
                //log
                Log.d("MainActivity", "Broadcast received $selectedItemId")
            }
        }
    }
}