package com.uzlahalya.beosis4.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleResponse(

	@field:SerializedName("article")
	val article: List<ArticleItem?>? = null
) : Parcelable


