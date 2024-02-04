package com.biggidroid.opaykotlin.parts

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.HorizontalScrollView

class NonScrollableHorizontalScrollView : HorizontalScrollView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        // This prevents the view from intercepting touch events, disabling scrolling
        return false
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        // This prevents the view from handling touch events, disabling scrolling
        return false
    }
}