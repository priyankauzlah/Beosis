package com.uzlahalya.beosis4.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ResponseScholarship(

    @field: SerializedName("results")
    val results: List<ScholarshipItem?>? = null,

): Parcelable


@Parcelize
data class ScholarshipItem(

    @field:SerializedName("continent")
    val continent: String,

    @field:SerializedName("benefits")
    val benefits: String,

    @field:SerializedName("country")
    val country: String,

    @field:SerializedName("requirements")
    val requirements: String,

    @field:SerializedName("scholarship_name")
    val scholarshipName: String,

    @field:SerializedName("close_register")
    val closeRegister: String,

    @field:SerializedName("degree")
    val degree: String,

    @field:SerializedName("link")
    val link: String,

    @field:SerializedName("created_at")
    val createdAt: @RawValue Any,

    @field:SerializedName("open_register")
    val openRegister: String,

    @field:SerializedName("university_name")
    val universityName: String,

    @field:SerializedName("major")
    val major: String,

    @field:SerializedName("updated_at")
    val updatedAt: @RawValue Any,

    @field:SerializedName("id")
    val id: Int
) : Parcelable