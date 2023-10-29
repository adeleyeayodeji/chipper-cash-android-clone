package com.biggidroid.opaykotlin

import android.R.attr.bottom
import android.R.attr.left
import android.R.attr.right
import android.R.attr.top
import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.biggidroid.opaykotlin.adapter.SliderAdapter
import com.biggidroid.opaykotlin.adapter.TransactionItemAdapter
import com.biggidroid.opaykotlin.bottomsheet.InfoBottomSheet
import com.biggidroid.opaykotlin.model.SlideItem
import com.biggidroid.opaykotlin.model.TransactionItem
import com.google.android.material.appbar.AppBarLayout


class MainActivity : AppCompatActivity(), NestedScrollView.OnScrollChangeListener {
    private lateinit var visibility_on_icon: ImageView
    private lateinit var balance: TextView
    private lateinit var info: ImageView
    private lateinit var home_app_bar_layout: AppBarLayout
    private lateinit var home_coordinator_layout: CoordinatorLayout
    private lateinit var transactionRecyclerView: RecyclerView
    private lateinit var home_nested_scroll_view: NestedScrollView
    private lateinit var balance_view_header_logic: RelativeLayout
    private lateinit var currency_selector_header_top: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //find view
        findView()

        //set on click listener
        setOnClickListener()

        //on scroll listener
        onScrollListener()


        val viewPager = findViewById<ViewPager>(R.id.viewPager)

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

    //find view
    private fun findView() {
        visibility_on_icon = findViewById(R.id.visibility_on_icon)
        balance = findViewById(R.id.balance)
        info = findViewById(R.id.info)
        home_app_bar_layout = findViewById(R.id.home_app_bar_layout)
        home_coordinator_layout = findViewById(R.id.home_coordinator_layout)
        transactionRecyclerView = findViewById(R.id.transactionRecyclerView)
        home_nested_scroll_view = findViewById(R.id.home_nested_scroll_view)
        balance_view_header_logic = findViewById(R.id.balance_view_header_logic)
        currency_selector_header_top = findViewById(R.id.currency_selector_header_top)

        //move balance_view_header_logic to top
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )

        //set top
        params.setMargins(0, 0, 0, 0)
        //padding
        balance_view_header_logic.setPadding(0, 130, 0, 0)
        balance_view_header_logic.layoutParams = params

        //set image height and width currency_selector_header_top
        val layoutParams = currency_selector_header_top.layoutParams
        layoutParams.width = 0
        layoutParams.height = 0
        currency_selector_header_top.layoutParams = layoutParams
    }

    //set on click listener
    private fun setOnClickListener() {
        //set on click listener on visibility_on_icon
        visibility_on_icon.setOnClickListener {
            Log.d("TAG_DATA", "setOnClickListener: ")
            hideOrShowBalance()
        }

        //balance
        balance.setOnClickListener {
            Log.d("TAG_DATA", "setOnClickListener: ")
            hideOrShowBalance()
        }

        //set on click listener on info
        info.setOnClickListener {
            Log.d("TAG_DATA", "setOnClickListener: ")
            // Get a reference to the Vibrator service
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            // Vibrate for 50 milliseconds (adjust duration as needed)
            vibrator.vibrate(50)

            val bottomSheetFragment = InfoBottomSheet()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }

    //on scroll listener
    private fun onScrollListener() {
        //set on scroll listener on home_nested_scroll_view
        home_nested_scroll_view.setOnScrollChangeListener(this)
    }

    //hide or show balance
    private fun hideOrShowBalance() {
        // Get a reference to the Vibrator service
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        // Vibrate for 50 milliseconds (adjust duration as needed)
        vibrator.vibrate(50)

        //onclick check if balance is text is ***** or not
        if (balance.text == "* * * * *") {
            //if balance is ***** then set balance text to 0.00
            balance.text = "488.14"
            //set visibility icon to visibility_off_icon
            visibility_on_icon.setImageResource(R.drawable.visibility_on)
        } else {
            //if balance is not ***** then set balance text to *****
            balance.text = "* * * * *"
            //set visibility icon to visibility_on_icon
            visibility_on_icon.setImageResource(R.drawable.visibility_off)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Handle back navigation within your app
        finish()
    }

    override fun onScrollChange(
        v: NestedScrollView,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        //visibility visible
        //set opacity from 0.0f to 0.1f till 1.0f
        val opacity = scrollY.toFloat() / 300 // 0.0f to 1.0f
//            Log.d("TAG_DATA", "onScrollopacity: $opacity")
        balance_view_header_logic.alpha = opacity
        //move balance_view_header_logic to top
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        //set padding max 50 from 130
        var paddingDynamic = 130 - scrollY / 2

        if (scrollY >= 176) {
            paddingDynamic = 40
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