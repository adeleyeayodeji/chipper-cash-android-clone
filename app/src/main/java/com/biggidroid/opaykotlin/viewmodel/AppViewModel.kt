package com.biggidroid.opaykotlin.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AppViewModel() : ViewModel() {
    val _currencyVisibility = MutableLiveData<Boolean>()
    val currencyVisibility: LiveData<Boolean> = _currencyVisibility

    fun initDefaultCurrencyVisibility(context: Context) {
        //get from shared preferences
        val sharedPreferences = context.getSharedPreferences("currencyVisibility", Context.MODE_PRIVATE)
        // Initialize the cart count
        _currencyVisibility.value = sharedPreferences.getBoolean("currency_visibility_key", false)
    }
}