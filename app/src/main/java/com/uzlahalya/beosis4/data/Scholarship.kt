package com.uzlahalya.beosis4.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Scholarship(
    var logo: Int = 0,
    var name: String? = null,
    var university: String? = null,
    var country: String? = null,
    var majors: String? = null,
    var degree: String? = null,
    var closeregistration: String? = null,
    var link: String? = null,
    var openregistration: String? = null,
    var benefit : String? = null,
    var requirement : String? = null
) : Parcelable