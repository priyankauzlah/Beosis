package com.uzlahalya.beosis4.mvvm

import com.uzlahalya.beosis4.data.Article
import com.uzlahalya.beosis4.data.Scholarship
import com.uzlahalya.beosis4.model.ArticleItem
import com.uzlahalya.beosis4.model.ArticleResponse
import com.uzlahalya.beosis4.model.ScholarshipResponse
import com.uzlahalya.beosis4.mvvm.database.ScholarshipEntity
import ScholarshipItem
import com.uzlahalya.beosis4.mvvm.database.ScholarshipDao
import com.uzlahalya.beosis4.mvvm.database.ScholarshipDatabase
import com.uzlahalya.beosis4.mvvm.network.api.ApiService
import com.uzlahalya.beosis4.util.ContextProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.http.GET


/** Mapper **/
fun Scholarship.toEntity(): ScholarshipEntity {
    return ScholarshipEntity(
        id = id.toInt(),
        logo = logo,
        name = name,
        university = university,
        country = country,
        majors = majors,
        degree = degree,
        closeregistration = closeregistration,
        link = link,
        openregistration = openregistration,
        benefit = benefit,
        requirement = requirement
    )
}

fun ArticleResponse.map(): List<Article> {
    return this.article?.map { it.map() }.orEmpty()
}

fun ArticleItem.map(): Article {
    return Article(
        image = image.orEmpty(),
        title = articleTitle.orEmpty(),
        country = country.orEmpty(),
        content = articleContent.orEmpty()
    )
}

fun List<ScholarshipEntity>.map(): List<Scholarship> {
    return this.map { it.map() }
}

fun ScholarshipEntity.map(): Scholarship {
    return Scholarship(
        id = id.toString(),
        logo = logo,
        name = name,
        university = university,
        country = country,
        majors = majors,
        degree = degree,
        closeregistration = closeregistration,
        link = link,
        openregistration = openregistration,
        benefit = benefit,
        requirement = requirement
    )
}

fun ScholarshipResponse.map(): List<Scholarship> {
    return this.scholarship?.map { it.map() }.orEmpty()
}

fun ScholarshipItem.map(): Scholarship {
    return Scholarship(
        id = id.toString(),
        logo = image.orEmpty(),
        name = scholarshipName.orEmpty(),
        university = universityName.orEmpty(),
        country = country.orEmpty(),
        majors = major.orEmpty(),
        degree = degree.orEmpty(),
        closeregistration = closeRegister.orEmpty(),
        link = link.orEmpty(),
        openregistration = openRegister.orEmpty(),
        benefit = benefits.orEmpty(),
        requirement = requirements.orEmpty()
    )
}