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
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.logging.Handler

/**
 * A simple [Fragment] subclass.
 * Use the [HomeInnerPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeInnerPage : Fragment() {
    //binding
    private var _binding: FragmentHomeInnerPageBinding? = null

    private var currentMainFragment: Fragment? = null

    //appContext
    private lateinit var appContext: Context

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    public lateinit var appViewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init view binding
        _binding = FragmentHomeInnerPageBinding.inflate(inflater, container, false)

        //init appContext
        appContext = requireContext().applicationContext

        //init view model
        appViewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        //init core adapter
        initCoreAdapter()

        //init on scroll listener
        onScrollListener()

        //init observer
        initObserver()

        //set on click listener
        setOnClickListener()

        //currentMainFragment
        currentMainFragment =
            parentFragmentManager.findFragmentById(R.id.fragment_container)

        //set content view
        return binding.root
    }

    private fun initCoreAdapter() {
        try {
            //init default currency visibility
            appViewModel.initDefaultCurrencyVisibility(appContext)

            //init viewPager
            val viewPager = binding.homeInnerContent.bannerSliderLayout.adsBannerSlider

            //init transactionRecyclerView
            val transactionRecyclerView = binding.homeInnerContent.transactionRecyclerView

            //shimmer_view_container
            val shimmerContainer =
                binding.homeInnerContent.bannerSliderLayout.facebookShimmerLayout.shimmerViewContainer

            //viewSwitcher
            val viewSwitcher = binding.homeInnerContent.bannerSliderLayout.viewSwitcher

            //viewSwitcherForTransactions
            val viewSwitcherForTransactions = binding.homeInnerContent.viewSwitcherForTransactions

            //facebook_shimmer_layout_for_transactions
            val facebookShimmerLayoutForTransactions =
                binding.homeInnerContent.facebookShimmerLayoutForTransactions.shimmerViewContainerForTransactions

            // Start the shimmer animation and show the skeleton layout
            shimmerContainer.startShimmer()
            viewSwitcher.displayedChild = 0

            // Start the shimmer animation and show the skeleton layout
            facebookShimmerLayoutForTransactions.startShimmer()
            viewSwitcherForTransactions.displayedChild = 1

            // Load data in a background thread using coroutines
            CoroutineScope(Dispatchers.IO).launch {
                val slideItems = listOf(
                    SlideItem("Protect Your Account!", R.drawable.nigeria, R.drawable.map),
                    SlideItem(
                        "Get Paid in USD Globally",
                        R.drawable.lab_profile,
                        R.drawable.icons8_globe
                    ),
                    SlideItem(
                        "Invest in global stocks for as low as $1",
                        R.drawable.icons8_globe,
                        R.drawable.wifi_tethering
                    )
                )

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

                withContext(Dispatchers.Main) {
                    // Stop the shimmer animation and show the ViewPager
                    shimmerContainer.stopShimmer()
                    //remove shimmerContainer from viewSwitcher
                    viewSwitcher.displayedChild = 1

                    // Stop the shimmer animation and show the ViewPager
                    facebookShimmerLayoutForTransactions.stopShimmer()
                    //remove facebookShimmerLayoutForTransactions from viewSwitcherForTransactions
                    viewSwitcherForTransactions.displayedChild = 0

                    val adapter2 = SliderAdapter(appContext, slideItems)
                    viewPager.adapter = adapter2

                    val transactionAdapter = TransactionItemAdapter(appContext, transactionItems)
                    transactionRecyclerView.adapter = transactionAdapter
                    transactionRecyclerView.layoutManager = LinearLayoutManager(appContext)
                }
            }
        } catch (e: Exception) {
            Log.d("TAG_DATA", "initCoreAdapter: ${e.message}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //auto scroll view pager
        autoScrollViewPager()
    }

    /**
     * Auto scroll view pager
     *
     */
    private fun autoScrollViewPager() {
        val delayMillis: Long = 3000 // change this to make the updates faster or slower

        val runnable = object : Runnable {
            var currentItem = 0

            override fun run() {
                val viewPager = binding.homeInnerContent.bannerSliderLayout.adsBannerSlider
                if (currentItem >= viewPager.adapter?.count ?: 0) {
                    currentItem = 0
                }
                viewPager.setCurrentItem(currentItem++, true)
                viewPager.postDelayed(this, delayMillis)
            }
        }

        val viewPager = binding.homeInnerContent.bannerSliderLayout.adsBannerSlider
        viewPager.postDelayed(runnable, delayMillis)
        viewPager.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    viewPager.removeCallbacks(runnable)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    viewPager.postDelayed(runnable, delayMillis)
                    v.performClick() // Add this line
                }
            }
            false
        }
    }

    /**
     * onScrollListener
     *
     * This function is used to listen to scroll events on the home page
     */
    private fun onScrollListener() {
        //set on scroll listener on home_nested_scroll_view
        binding.homeInnerContent.homeNestedScrollView.setOnScrollChangeListener { v: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->
            //visibility visible
            //set opacity from 0.0f to 0.1f till 1.0f
            val opacity = scrollY.toFloat() / 300 // 0.0f to 1.0f
            //home_app_bar_layout
            val balance_view_header_logic =
                binding.homeInnerContent.homeAppHeaderContraintLayout.balanceViewHeaderLogic
            //home_header_contraint_layout
            val home_header_contraint_layout =
                binding.homeInnerContent.homeAppHeaderContraintLayout.homeHeaderContraintLayout
            //header_center_balanceview
            val currency_selector_header_top =
                binding.homeInnerContent.homeAppHeaderContraintLayout.headerCenterBalanceview.currencySelectorHeaderTop
            //header
            val header = binding.homeInnerContent.homeAppHeaderContraintLayout.header
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
            var parentView = binding.homeInnerContent.balanceLayout
            var parentViewHeader =
                binding.homeInnerContent.homeAppHeaderContraintLayout.headerCenterBalanceview
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
        var parentView = binding.homeInnerContent.balanceLayout
        var parentViewAddCash = binding.homeInnerContent.balanceButtonLayout
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
        binding.homeInnerContent.homeAppHeaderContraintLayout.info.setOnClickListener {
            val bottomSheetFragment = InfoBottomSheet()
            bottomSheetFragment.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.BottomSheetDialogCustom
            )
            bottomSheetFragment.show(
                requireActivity().supportFragmentManager,
                bottomSheetFragment.tag
            )

        }

        //add_cash_layout
        parentViewAddCash.addCashLayout.setOnClickListener {
            // Get the fragment from the fragment manager
            val addCashFragment =
                requireActivity().supportFragmentManager.findFragmentByTag("AddCash")

            // Begin the transaction
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            // Set the transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

            if (addCashFragment == null) {
                // If the fragment is not already added, add it
                transaction.add(R.id.fragment_container, AddCash(), "AddCash")
            } else {
                // If the fragment is already added, show it
                transaction.show(addCashFragment)
            }

            // Add the transaction to the back stack and commit
            transaction.addToBackStack("AddCash")
            transaction.commit()
        }

        //set onclick user_home_icon
        binding.homeInnerContent.homeAppHeaderContraintLayout.userHomeIcon.setOnClickListener {
            //goto user page fragment
            // Get the fragment from the fragment manager
            val userPageFragment =
                requireActivity().supportFragmentManager.findFragmentByTag("UserPage")

            // Begin the transaction
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            // Set the transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

            if (userPageFragment == null) {
                // If the fragment is not already added, add it
                transaction.add(R.id.fragment_container, UserPage(), "UserPage")
            } else {
                // If the fragment is already added, show it
                transaction.show(userPageFragment)
            }

            // Add the transaction to the back stack and commit
            transaction.addToBackStack("UserPage")
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
        binding.homeInnerContent.bannerSliderLayout.adsBannerSlider.removeCallbacks(null)
        _binding = null
    }
}