package com.uzlahalya.beosis4.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article (
    var image: String,
    var title: String,
    var country: String,
    var content: String,
    var url: String,
): Parcelable