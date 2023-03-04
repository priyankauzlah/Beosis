package com.uzlahalya.beosis4.model

import ScholarshipItem
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ScholarshipResponse(
	@field:SerializedName("scholarship")
	val scholarship: List<ScholarshipItem>? = null
)

