package com.biggidroid.opaykotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.model.TransactionItem

class TransactionItemAdapter(private val context: Context, private val transactionItems: List<TransactionItem>) :
    RecyclerView.Adapter<TransactionItemAdapter.TransactionItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.transaction_item_layout, parent, false)
        return TransactionItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionItemViewHolder, position: Int) {
        val transactionItem = transactionItems[position]
        holder.bind(transactionItem)
    }

    override fun getItemCount(): Int {
        return transactionItems.size
    }

    inner class TransactionItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val amountTextView: TextView = itemView.findViewById(R.id.amountTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val shortDescTextView: TextView = itemView.findViewById(R.id.shortDescTextView)
        private val transactionItemLayoutParent: LinearLayoutCompat = itemView.findViewById(R.id.transactionItemLayoutParent)

        fun bind(transactionItem: TransactionItem) {
            iconImageView.setImageResource(transactionItem.icon)
            titleTextView.text = transactionItem.title
            amountTextView.text = transactionItem.amount
            dateTextView.text = transactionItem.date
            shortDescTextView.text = transactionItem.shortDesc

            //set border bottom for all except last item
            if (adapterPosition != transactionItems.size - 1) {
               //append an horizontal line with 1dp height
                val horizontalLine = View(context)
                horizontalLine.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    2
                )
                horizontalLine.setBackgroundColor(context.resources.getColor(R.color.lightGray))
                transactionItemLayoutParent.addView(horizontalLine)
            }
        }
    }
}
