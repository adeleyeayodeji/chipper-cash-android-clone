package com.biggidroid.opaykotlin.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.adapter.SliderAdapter
import com.biggidroid.opaykotlin.adapter.TransactionItemAdapter
import com.biggidroid.opaykotlin.databinding.FragmentHomeInnerPageBinding
import com.biggidroid.opaykotlin.model.SlideItem
import com.biggidroid.opaykotlin.model.TransactionItem
import com.biggidroid.opaykotlin.viewmodel.AppViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeInnerPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeInnerPage : Fragment() {
    //binding
    private var _binding: FragmentHomeInnerPageBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    public lateinit var appViewModel: AppViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init view binding
        _binding = FragmentHomeInnerPageBinding.inflate(inflater, container, false)
        //set content view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init view model
        appViewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        //get app context
        val appContext = requireContext().applicationContext

        //init default currency visibility
        appViewModel.initDefaultCurrencyVisibility(appContext)

        val viewPager = binding.bannerSliderLayout.adsBannerSlider;

        val slideItems = listOf(
            SlideItem("Protect Your Account!", R.drawable.nigeria, R.drawable.map),
            SlideItem("Get Paid in USD Globally", R.drawable.lab_profile, R.drawable.icons8_globe),
            SlideItem(
                "Invest in global stocks for as low as $1",
                R.drawable.icons8_globe,
                R.drawable.wifi_tethering
            )
        )

        val adapter2 = SliderAdapter(this, slideItems)
        viewPager.adapter = adapter2

        // Sample list of TransactionItem objects (replace with your data)
        val transactionItems = listOf(
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
        )

        val transactionAdapter = TransactionItemAdapter(this, transactionItems)
        transactionRecyclerView.adapter = transactionAdapter
        transactionRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}