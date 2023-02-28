package com.uzlahalya.beosis4.mvvm.database

import ScholarshipItem
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ScholarshipTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun scholarshipDataToString(scholarship: ScholarshipItem): String {
        return gson.toJson(scholarship)
    }

    @TypeConverter
    fun scholarshipStringToData(string: String): ScholarshipItem {
        val listType = object : TypeToken<ScholarshipItem>(){}.type
        return gson.fromJson(string, listType)
    }
}