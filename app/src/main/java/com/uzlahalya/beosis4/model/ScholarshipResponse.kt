package com.uzlahalya.beosis4.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ScholarshipResponse(

    @field:SerializedName("scholarship")
    var scholarship: List<ScholarshipItem>,

    @field:SerializedName("status")
    val status: String? = null
)