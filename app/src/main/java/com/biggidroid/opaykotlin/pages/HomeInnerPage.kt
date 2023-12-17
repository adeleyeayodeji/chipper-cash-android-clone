package com.biggidroid.opaykotlin.pages

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val adapter2 = SliderAdapter(appContext, slideItems)
        viewPager.adapter = adapter2
2
        // Sample list of TransactionItem objects (replace with your data)
        val transactionItems = listOf(
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
            TransactionItem(
                R.drawable.account_circle_black,
                "You paid IKEJA Prepaid Electricity",
                "₦5,000.00",
                "Thursday, 12th March 2020",
                "debit",
                "For IKEJA Prepaid Electricity"
            ),
        )

        //init transactionRecyclerView
        val transactionRecyclerView = binding.transactionRecyclerView

        val transactionAdapter = TransactionItemAdapter(appContext, transactionItems)
        transactionRecyclerView.adapter = transactionAdapter
        transactionRecyclerView.layoutManager = LinearLayoutManager(appContext)

        //init on scroll listener
        onScrollListener();
    }

    /**
     * onScrollListener
     *
     * This function is used to listen to scroll events on the home page
     */
    private fun onScrollListener() {
        //set on scroll listener on home_nested_scroll_view
        binding.homeNestedScrollView.setOnScrollChangeListener { v: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->
            //visibility visible
            //set opacity from 0.0f to 0.1f till 1.0f
            val opacity = scrollY.toFloat() / 300 // 0.0f to 1.0f
            //home_app_bar_layout
            val balance_view_header_logic = binding.homeAppHeaderContraintLayout.balanceViewHeaderLogic
            //home_header_contraint_layout
            val home_header_contraint_layout = binding.homeAppHeaderContraintLayout.homeHeaderContraintLayout
            //header_center_balanceview
            val currency_selector_header_top = binding.homeAppHeaderContraintLayout.headerCenterBalanceview.currencySelectorHeaderTop
            //header
            val header = binding.homeAppHeaderContraintLayout.header
//            Log.d("TAG_DATA", "onScrollopacity: $opacity")
            balance_view_header_logic.alpha = opacity
            //move balance_view_header_logic to top
            val params = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            //set padding max 45 from 130
            var paddingDynamic = 130 - scrollY / 2

            if (scrollY >= 176) {
                paddingDynamic = 45
            }

            if (scrollY >= 500) {
                //set home_header_contraint_layout background color to white
                home_header_contraint_layout.setBackgroundColor(Color.parseColor("#ffffff"));
                header.setBackgroundColor(Color.parseColor("#ffffff"));
            } else {
                //restore color to #F3EFEF
                home_header_contraint_layout.setBackgroundColor(Color.parseColor("#F3EFEF"));
                header.setBackgroundColor(Color.parseColor("#F3EFEF"));
            }

//        Log.d("TAG_DATA", "onScrollpaddingDynamic: $paddingDynamic")

            //set top
            params.setMargins(0, 0, 0, 0)
            //padding
            balance_view_header_logic.setPadding(0, paddingDynamic, 0, 0)
            balance_view_header_logic.layoutParams = params

            //currency_selector_header_top
            val params2 = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )

            //width and height for currency_selector_header_top
            var width = scrollY / 2

            //check if width is greater than 24
            if (width >= 60) {
                width = 60
            }

            //set image height and width currency_selector_header_top
            val layoutParams = currency_selector_header_top.layoutParams
            layoutParams.width = width
            layoutParams.height = width
            currency_selector_header_top.layoutParams = layoutParams

//        Log.d("TAG_DATA", "onScrollChange: $scrollY")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}