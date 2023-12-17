package com.biggidroid.opaykotlin.pages

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.adapter.SliderAdapter
import com.biggidroid.opaykotlin.adapter.TransactionItemAdapter
import com.biggidroid.opaykotlin.bottomsheet.InfoBottomSheet
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

    //appContext
    private lateinit var appContext: Context

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

        //init appContext
        appContext = requireContext().applicationContext

        //init view model
        appViewModel = ViewModelProvider(this).get(AppViewModel::class.java)

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

        //init observer
        initObserver()

        //set on click listener
        setOnClickListener()
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
            val balance_view_header_logic =
                binding.homeAppHeaderContraintLayout.balanceViewHeaderLogic
            //home_header_contraint_layout
            val home_header_contraint_layout =
                binding.homeAppHeaderContraintLayout.homeHeaderContraintLayout
            //header_center_balanceview
            val currency_selector_header_top =
                binding.homeAppHeaderContraintLayout.headerCenterBalanceview.currencySelectorHeaderTop
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

  private fun initObserver() {
    //observe currencyVisibility
    appViewModel.currencyVisibility.observe(viewLifecycleOwner) {
        var parentView = binding.balanceLayout
        var parentViewHeader = binding.homeAppHeaderContraintLayout.headerCenterBalanceview
        if (it) {
            //if balance is ***** then set balance text to 0.00
            parentView.balance.text = "488.14"
            //balanceHeaderTop
            parentViewHeader.balanceHeaderTop.text = "488.14"
            //set visibility icon to visibility_off_icon
            parentView.visibilityOnIcon.setImageResource(R.drawable.visibility_on)
        } else {
            //if balance is not ***** then set balance text to *****
            parentView.balance.text = "* * * * *"
            //balanceHeaderTop
            parentViewHeader.balanceHeaderTop.text = "* * * * *"
            //set visibility icon to visibility_on_icon
            parentView.visibilityOnIcon.setImageResource(R.drawable.visibility_off)
        }
    }
}

    //set on click listener
    private fun setOnClickListener() {
        var parentView = binding.balanceLayout
        var parentViewAddCash = binding.balanceButtonLayout
        //set on click listener on visibility_on_icon
        parentView.visibilityOnIcon.setOnClickListener {
            Log.d("TAG_DATA", "setOnClickListener: ")
            hideOrShowBalance()
        }

        //balance
        parentView.balance.setOnClickListener {
            Log.d("TAG_DATA", "setOnClickListener: ")
            hideOrShowBalance()
        }

        //set on click listener on info
        binding.homeAppHeaderContraintLayout.info.setOnClickListener {
            Log.d("TAG_DATA", "setOnClickListener: ")
            // Get a reference to the Vibrator service
            val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            // Vibrate for 50 milliseconds (adjust duration as needed)
            vibrator.vibrate(50)

            val bottomSheetFragment = InfoBottomSheet()
            bottomSheetFragment.show(
                requireActivity().supportFragmentManager,
                bottomSheetFragment.tag
            )
        }

        //add_cash_layout
        parentViewAddCash.addCashLayout.setOnClickListener {
            //goto add cash page fragment
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            //bounce up animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.replace(R.id.fragment_container, AddCash())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        //set onclick user_home_icon
        binding.homeAppHeaderContraintLayout.userHomeIcon.setOnClickListener {
            //goto user page fragment
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.replace(R.id.fragment_container, UserPage())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    //hide or show balance
    private fun hideOrShowBalance() {
        //get currencyVisibility
        val currencyVisibility = appViewModel.currencyVisibility.value

        //check if currencyVisibility is true
        appViewModel._currencyVisibility.value = currencyVisibility != true

        //save data to shared preference
        val sharedPref =
            requireActivity().getSharedPreferences("currencyVisibility", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("currency_visibility_key", currencyVisibility != true)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}