package com.biggidroid.opaykotlin
import android.os.Bundle
import com.biggidroid.opaykotlin.mainactivity.trait.MainActivityTrait


class MainActivity : MainActivityTrait() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init action
        initAction()
    }

}