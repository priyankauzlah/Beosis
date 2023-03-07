package com.uzlahalya.beosis4.util

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.uzlahalya.beosis4.mvvm.network.handler.NetworkResult

fun <U> LiveData<NetworkResult<U>>.observeLiveData(
    owner: LifecycleOwner,
    context: Context,
    onLoading: (() -> (Unit))? = null,
    onSuccess: (U) -> Unit,
    onFailure: ((String) -> Unit)? = null
) {
//    var apiError: ApiError

    this.observe(owner) {
        when (it) {
            is NetworkResult.Loading -> {
                onLoading?.invoke()
            }

            is NetworkResult.Success -> {
                it.data?.let { it1 -> onSuccess.invoke(it1) }
            }

            is NetworkResult.Error -> {
                it.errorMessage?.let { it1 -> onFailure?.invoke(it1) }
            }
        }
    }
}