package com.biggidroid.opaykotlin
import android.os.Bundle
import com.biggidroid.opaykotlin.databinding.ActivityMainBinding
import com.biggidroid.opaykotlin.mainactivity.trait.MainActivityTrait


class MainActivity : MainActivityTrait() {
    //binding
    private var _binding: ActivityMainBinding? = null

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

}