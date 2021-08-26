package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.network.*
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _foodSearch = MutableLiveData<FoodSearch>()
    val foodSearch: LiveData<FoodSearch> = _foodSearch

    private val _searchFoundResults = MutableLiveData(0)
    val searchFoundResults: LiveData<Int> = _searchFoundResults

    private val _navigateHome = SingleLiveEvent<Any>()
    val navigateHome: LiveData<Any> = _navigateHome

    var successfullyApiCall: Boolean = true

    fun callGoHome(){
        _navigateHome.call()
    }

    fun getSearchFood(newSearch: String?) {
        viewModelScope.launch {
            try {
                _foodSearch.value = FoodApi.retrofitService.getSearchFood(newSearch)
                _searchFoundResults.value = _foodSearch.value?.meals?.size
                successfullyApiCall = true
                Log.d(
                    "DEB_search", "Successfully retrieved search meals for API")
            } catch (e: Exception) {
                Log.e("DEB_search", "Error at getting searched meals for API")
                successfullyApiCall = false
            }
        }
    }
}