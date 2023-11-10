package com.biggidroid.opaykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class UserPage : AppCompatActivity() {
    private lateinit var close_icon_user_page: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page)

        //find view by id
        findview()

        //set on click listener
        setOnClickListener()
    }

    //find view by id
    private fun findview() {
        close_icon_user_page = findViewById(R.id.close_icon_user_page)
    }

    //set on click listener
    private fun setOnClickListener() {
        close_icon_user_page.setOnClickListener {
            finishAfterTransition()
        }
    }
}