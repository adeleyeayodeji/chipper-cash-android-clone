package com.biggidroid.opaykotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.model.SlideItem

class SliderAdapter(private val context: Context, private val items: List<SlideItem>) : PagerAdapter() {

    override fun getCount(): Int = items.size

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.payment_slider_cardview, null)

        val iconLargeImageView = view.findViewById<ImageView>(R.id.iconLargeImageView)
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val iconSmallImageView = view.findViewById<ImageView>(R.id.iconSmallImageView)

        val item = items[position]
        iconLargeImageView.setImageResource(item.iconResource)
        titleTextView.text = item.title
        iconSmallImageView.setImageResource(item.iconSmallResource)

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
