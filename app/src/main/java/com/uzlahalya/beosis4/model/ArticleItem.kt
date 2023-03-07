package com.uzlahalya.beosis4.model

import com.google.gson.annotations.SerializedName

data class ArticleItem(
    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("article_title")
    val articleTitle: String? = null,

    @field:SerializedName("article_content")
    val articleContent: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("url")
    val url: String? = null
)