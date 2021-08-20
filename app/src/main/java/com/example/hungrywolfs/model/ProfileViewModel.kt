package com.example.hungrywolfs.model
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hungrywolfs.SingleLiveEvent

class ProfileViewModel: ViewModel() {
    private val _navigateBack = SingleLiveEvent<Any>()
    val navigateBack: LiveData<Any> = _navigateBack

    private val _navigateFavourites = SingleLiveEvent<Any>()
    val navigateFavourites: LiveData<Any> = _navigateFavourites

    private val _navigateTermsAndConditions = SingleLiveEvent<Any>()
    val navigateTermsAndConditions: LiveData<Any> = _navigateTermsAndConditions

    fun goBack(){
        _navigateBack.call()
    }

    fun goFavourites(){
        _navigateFavourites.call()
    }

    fun goTermsAndConditions(){
        _navigateTermsAndConditions.call()
    }
}