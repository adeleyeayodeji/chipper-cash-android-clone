package com.biggidroid.opaykotlin.adapter

import android.util.Log
import com.biggidroid.opaykotlin.R
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.biggidroid.opaykotlin.model.PaymentData


public class PaymentSliderAdapter(private val data: List<PaymentData>) :
    RecyclerView.Adapter<PaymentSliderAdapter.PaymentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.payment_slider_cardview, parent, false)
        return PaymentViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class PaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconLarge: ImageView
        private val iconSmall: ImageView
        private val titleTextView: TextView
        private val cardViewPaymentSlider: CardView

        init {
            iconLarge = itemView.findViewById<ImageView>(R.id.iconLargeImageView)
            iconSmall = itemView.findViewById<ImageView>(R.id.iconSmallImageView)
            titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
            cardViewPaymentSlider = itemView.findViewById<CardView>(R.id.cardViewPaymentSlider)
        }

        fun bind(item: PaymentData) {
            iconLarge.setImageResource(item.iconResource)
            iconSmall.setImageResource(item.iconSmallResource)
            titleTextView.text = item.title

            //set margin top to -20dp for all execpt first item
            if (adapterPosition != 0) {
                setMarginTop()
            }

            // Enable card dragging
            cardViewPaymentSlider.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Handle the touch down event
                        // You can capture initial touch coordinates, if needed
                        Log.d("TAG_DATA", "bind: Item dragged")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        // Handle the touch move event
                        // move the view as you drag it
                    }
                    MotionEvent.ACTION_UP -> {
                        // Handle the touch up event
                        // Perform any final actions when the user stops dragging
                        Log.d("TAG_DATA", "bind: Item dropped")
                    }
                }
                true // Indicates that the touch event has been consumed
            }
        }

        private fun setMarginTop() {
            val layoutParams = itemView.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.setMargins(0, -600, 0, 0)
            itemView.layoutParams = layoutParams
        }
    }
}
