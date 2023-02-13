package com.uzlahalya.beosis4.datascholar

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article (
    var image : Int = 0,
    var title : String? = null,
    var country : String? = null,
    var content : String? = null,
):Parcelable