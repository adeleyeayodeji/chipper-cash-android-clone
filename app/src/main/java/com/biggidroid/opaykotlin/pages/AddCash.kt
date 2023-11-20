package com.biggidroid.opaykotlin.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.biggidroid.opaykotlin.R

class AddCash : AppCompatActivity() {
    private lateinit var close_icon_addcash_page: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cash)
        //find view by id
        findview()

        //set on click listener
        setOnClickListener()
    }

    //find view by id
    private fun findview() {
        close_icon_addcash_page = findViewById(R.id.close_icon_addcash_page)
    }

    //set on click listener
    private fun setOnClickListener() {
        close_icon_addcash_page.setOnClickListener {
            finishAfterTransition()
        }
    }
}