package com.uzlahalya.beosis4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uzlahalya.beosis4.model.ScholarshipResponse
import com.uzlahalya.beosis4.mvvm.ApiService
import com.uzlahalya.beosis4.mvvm.RemoteDataSource
import com.uzlahalya.beosis4.mvvm.Repository
import com.uzlahalya.beosis4.mvvm.network.handler.NetworkResult
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel() {

//    private val scholarshipService = ApiService.ScholarshipService
//    private val remote = RemoteDataSource(scholarshipService)
//    private val repository = Repository(remote)
//
//    private val _scholarshipList:MutableLiveData<NetworkResult<ScholarshipResponse>> = MutableLiveData()
//    val scholarshipList:LiveData<NetworkResult<ScholarshipResponse>> = _scholarshipList
//
//    init {
//        fetchScholarshipList()
//    }
//
//    private fun fetchScholarshipList(){
//        viewModelScope.launch {
//            repository.remote.getScholarship().collect{ result ->
//                _scholarshipList.value = result
//            }
//        }
//    }
}