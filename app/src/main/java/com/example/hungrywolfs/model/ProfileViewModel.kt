package com.example.hungrywolfs.model
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hungrywolfs.SingleLiveEvent

class ProfileViewModel: ViewModel() {
    private val _navigateBack = SingleLiveEvent<Any>()
    val navigateBack: LiveData<Any> = _navigateBack

    fun callGoBack(){
        Log.d("testX","here i am")
        _navigateBack.call()
    }

}