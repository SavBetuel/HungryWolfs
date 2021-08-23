package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.network.UserData
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    private val _navigateBack = SingleLiveEvent<Any>()
    val navigateBack: LiveData<Any> = _navigateBack

    private val _navigateFavourites = SingleLiveEvent<Any>()
    val navigateFavourites: LiveData<Any> = _navigateFavourites

    private val _navigateTermsAndConditions = SingleLiveEvent<Any>()
    val navigateTermsAndConditions: LiveData<Any> = _navigateTermsAndConditions

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> = _userData

    init{
        getUserData()
    }

    fun goBack(){
        _navigateBack.call()
    }

    fun goFavourites(){
        _navigateFavourites.call()
    }

    fun goTermsAndConditions(){
        _navigateTermsAndConditions.call()
    }

    private fun getUserData(){
        viewModelScope.launch {
            try{
                _userData.value = UserData(name = "Sav Betuel", email = "sav.betuel1999@gmail.com", phone = "+40 752 376 626")
                Log.d("ProfileViewModel", "Add user data")
            } catch (e: Exception){
                Log.e("ProfileViewModel", "Error at adding user data")
            }
        }
    }
}