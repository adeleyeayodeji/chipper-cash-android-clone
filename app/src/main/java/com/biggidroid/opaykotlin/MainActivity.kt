package com.biggidroid.opaykotlin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.biggidroid.opaykotlin.databinding.ActivityMainBinding
import com.biggidroid.opaykotlin.mainactivity.trait.MainActivityTrait


class MainActivity : MainActivityTrait() {
    //binding
    private var _binding: ActivityMainBinding? = null

    private var doubleBackToExitPressedOnce = false

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //init view binding
        _binding = ActivityMainBinding.inflate(layoutInflater)
        //set content view
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            //allow back press twice to exit
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                doubleBackToExitPressedOnce = false
            }, 2000)
        }
    }

}