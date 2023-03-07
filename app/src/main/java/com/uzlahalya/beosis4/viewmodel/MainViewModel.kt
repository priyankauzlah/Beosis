package com.uzlahalya.beosis4.viewmodel

import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.data.Article
import com.uzlahalya.beosis4.data.Scholarship
import com.uzlahalya.beosis4.mvvm.MainRepository
import com.uzlahalya.beosis4.mvvm.database.ScholarshipEntity
import com.uzlahalya.beosis4.mvvm.map
import com.uzlahalya.beosis4.mvvm.network.handler.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository: MainRepository by lazy {
        return@lazy MainRepository()
    }

    private val _scholarshipList: MutableLiveData<NetworkResult<List<Scholarship>>> = MutableLiveData()
    val scholarshipList: LiveData<NetworkResult<List<Scholarship>>> = _scholarshipList

    private val _savedScholarshipList: MutableLiveData<NetworkResult<List<Scholarship>>> = MutableLiveData()
    val savedScholarshipList: LiveData<NetworkResult<List<Scholarship>>> = _savedScholarshipList

    private val _articleList: MutableLiveData<NetworkResult<List<Article>>> = MutableLiveData()
    val articleList: LiveData<NetworkResult<List<Article>>> = _articleList

    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    init {
        fetchScholarships()
        fetchArticles()
    }

    fun fetchScholarships() = viewModelScope.launch {
        repository.getScholarships()
            .catch {
                _scholarshipList.value = NetworkResult.Error(it.message)
            }
            .collect { result ->
                _scholarshipList.value = NetworkResult.Success(result)
            }
    }

    fun fetchArticles() = viewModelScope.launch {
        repository.getArticles()
            .catch {
                _articleList.value = NetworkResult.Error(it.message)
            }
            .collect { result ->
                _articleList.value = NetworkResult.Success(result)
            }
    }

    fun checkScholarshipIsFavorited(id: String) {
        viewModelScope.launch {
            repository.getSavedScholarships()
                .collect { result ->
                    _isFavorite.value = result.map { it.id }.contains(id)
                    Log.d("ISFAVORITE", "$id IS ${_isFavorite.value} IN ${result.map { it.id }} 🙏", )
                }
        }
    }

    fun fetchSavedScholarships() = viewModelScope.launch {
        repository.getSavedScholarships()
            .catch {
                _savedScholarshipList.value = NetworkResult.Error(it.message)
            }
            .collect { result ->
                _savedScholarshipList.value = NetworkResult.Success(result)
            }
    }
    fun insertScholarship(scholarship: Scholarship) = viewModelScope.launch(Dispatchers.IO) {
        _isFavorite.postValue(true)
        repository.insertScholarship(scholarship)
    }
    fun deleteScholarship(scholarship: Scholarship) = viewModelScope.launch(Dispatchers.IO) {
        _isFavorite.postValue(false)
        repository.deleteScholarship(scholarship)
    }
}