package com.biggidroid.opaykotlin.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.databinding.FragmentInvestPageBinding
import java.util.logging.Handler

/**
 * A simple [Fragment] subclass.
 * Use the [InvestPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class InvestPage : Fragment() {
    //binding
    private var _binding: FragmentInvestPageBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init view binding
        _binding = FragmentInvestPageBinding.inflate(inflater, container, false)
        //set content view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //auto scroll horizontal scroll view
        autoScrollHorizontalScrollView()
    }

    /**
     * Auto scroll horizontal scroll view
     *
     */
    private fun autoScrollHorizontalScrollView() {
        //set auto scroll
        val scrollAmount = 1 // change this to scroll faster or slower
        val delayMillis: Long = 20 // change this to make the updates faster or slower

        val runnable = object : Runnable {
            var direction = 1

            override fun run() {
                val scrollView = binding.investInclude.llInvestPage1
                if (direction > 0 && !scrollView.canScrollHorizontally(1)) {
                    direction = -1
                } else if (direction < 0 && !scrollView.canScrollHorizontally(-1)) {
                    direction = 1
                }
                scrollView.smoothScrollBy(scrollAmount * direction, 0)
                scrollView.postDelayed(this, delayMillis)
            }
        }

        binding.investInclude.llInvestPage1.postDelayed(runnable, delayMillis)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //destroy invest page auto scroll
        binding.investInclude.llInvestPage1.removeCallbacks(null)
        //destroy view binding
        _binding = null
    }
}