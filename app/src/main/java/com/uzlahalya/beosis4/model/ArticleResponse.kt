package com.uzlahalya.beosis4.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
	@field:SerializedName("article")
	val article: List<ArticleItem>? = null
)


