package com.uzlahalya.beosis4.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article (
    var image : Int = 0,
    var title : String? = null,
    var country : String? = null,
    var content : String? = null,
    var url : String? = null,
):Parcelable