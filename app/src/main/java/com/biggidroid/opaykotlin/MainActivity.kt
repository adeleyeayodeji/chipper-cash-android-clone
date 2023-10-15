package com.biggidroid.opaykotlin

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.biggidroid.opaykotlin.adapter.PaymentSliderAdapter
import com.biggidroid.opaykotlin.bottomsheet.InfoBottomSheet
import com.biggidroid.opaykotlin.model.PaymentData


class MainActivity : AppCompatActivity() {
    private lateinit var visibility_on_icon: ImageView
    private lateinit var balance: TextView
    private lateinit var info: ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PaymentSliderAdapter
    private lateinit var paymentDataList: List<PaymentData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //find view
        findView()

        //set on click listener
        setOnClickListener()

        recyclerView = findViewById(R.id.payment_slider_body_main_layout_test)
        recyclerView.setLayoutManager(LinearLayoutManager(this))

        // Initialize your paymentDataList with sample data
        paymentDataList = ArrayList()
        (paymentDataList as ArrayList<PaymentData>).add(PaymentData("Adeleye Ayodeji", R.drawable.map))
        (paymentDataList as ArrayList<PaymentData>).add(PaymentData("PayPal", R.drawable.lab_profile))
        (paymentDataList as ArrayList<PaymentData>).add(PaymentData("Bitcoin", R.drawable.wifi_tethering))
        (paymentDataList as ArrayList<PaymentData>).add(PaymentData("Bitcoin", R.drawable.wifi_tethering))
        (paymentDataList as ArrayList<PaymentData>).add(PaymentData("Bitcoin", R.drawable.wifi_tethering))
        (paymentDataList as ArrayList<PaymentData>).add(PaymentData("Bitcoin", R.drawable.wifi_tethering))
        (paymentDataList as ArrayList<PaymentData>).add(PaymentData("Bitcoin", R.drawable.wifi_tethering))


        adapter = PaymentSliderAdapter(paymentDataList)
        recyclerView.setAdapter(adapter)
    }

    //find view
    private fun findView() {
        visibility_on_icon = findViewById(R.id.visibility_on_icon)
        balance = findViewById(R.id.balance)
        info = findViewById(R.id.info)
    }

    //set on click listener
    private fun setOnClickListener() {
        //set on click listener on visibility_on_icon
        visibility_on_icon.setOnClickListener {
            Log.d("TAG_DATA", "setOnClickListener: ")

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

    override fun onBackPressed() {
        // Handle back navigation within your app
        finish()
    }
}