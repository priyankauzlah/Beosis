package com.uzlahalya.beosis4.datascholar

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Scholarship (
    var logo : Int = 0,
    var name : String? = null,
    var university : String? = null,
    var country : String? = null,
    var majors : String? = null,
    var degree : String? = null,
    var closeregistration : String? = null,
    var openregistration : String? = null,
) : Parcelable