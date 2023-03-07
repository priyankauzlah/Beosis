package com.uzlahalya.beosis4.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Scholarship(
    var id: String,
    var logo: String,
    var name: String,
    var university: String,
    var country: String,
    var majors: String,
    var degree: String,
    var closeregistration: String,
    var link: String,
    var openregistration: String,
    var benefit : String,
    var requirement : String
) : Parcelable