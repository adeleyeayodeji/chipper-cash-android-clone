package com.biggidroid.opaykotlin.adapter

import com.biggidroid.opaykotlin.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.biggidroid.opaykotlin.model.PaymentData


public class PaymentSliderAdapter(private val data: List<PaymentData>) :
    RecyclerView.Adapter<PaymentSliderAdapter.PaymentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.payment_slider_cardview_test, parent, false)
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
        private val iconImageView: ImageView
        private val titleTextView: TextView

        init {
            iconImageView = itemView.findViewById<ImageView>(R.id.iconImageView)
            titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        }

        fun bind(item: PaymentData) {
            iconImageView.setImageResource(item.iconResource)
            titleTextView.text = item.title
        }
    }
}
