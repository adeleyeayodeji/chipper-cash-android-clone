package com.biggidroid.opaykotlin

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.biggidroid.opaykotlin.databinding.ActivityMainBinding
import com.biggidroid.opaykotlin.databinding.FragmentHomePageBinding
import com.biggidroid.opaykotlin.mainactivity.trait.MainActivityTrait
import org.json.JSONObject


class MainActivity : MainActivityTrait() {
    //binding
    private var _binding: ActivityMainBinding? = null

    //binding homepage
    private var _bindingHomePage: FragmentHomePageBinding? = null

    private var doubleBackToExitPressedOnce = false

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //init view binding
        _binding = ActivityMainBinding.inflate(layoutInflater)
        //init view binding homepage
        _bindingHomePage = FragmentHomePageBinding.inflate(layoutInflater)
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
            try {
                //get selectedBottomItemId from shared preference
                val sharedPref = getPreferences(Context.MODE_PRIVATE)
                val jsonString = sharedPref.getString("selectedBottomItemId", "")
                val jsonObject = JSONObject(jsonString!!)
                //get the parent name
                val parentName = jsonObject.getString("savednav")
                //get name from parent
                val name = JSONObject(parentName).getString("name")
                val id = JSONObject(parentName).getInt("selectedBottomItemId")
                //log parent name
                Log.e("MainActivity", "Am working $name $id")
                //check if the parent name is not home
                if (name != "home") {
                    // Trigger the first index on bottomnavigationview
                    val bottomNavView = _bindingHomePage?.fragmentHomePageContainer?.bottomNavigationView
                    if (bottomNavView == null) {
                        Log.e("MainActivity", "BottomNavigationView is null")
                    } else {
                        bottomNavView.selectedItemId = id
                        Log.d("MainActivity", "Set selected item id to $id")
                    }
                    //delete shared preference
                    sharedPref.edit().remove("selectedBottomItemId").apply()
                    //return
                    return
                }
            } catch (e: Exception) {
                e.printStackTrace()
                //toast error
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
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