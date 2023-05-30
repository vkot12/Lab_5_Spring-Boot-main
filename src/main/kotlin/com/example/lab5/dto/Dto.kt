package com.example.lab5.dto

import com.example.lab5.entity.Magazine
import com.example.lab5.entity.Article
import java.time.LocalDate

data class ExampleRequest(
    var name: String
)

data class ExampleResponse(
    var id: Long,
    var name: String
)

data class MagazineRequestDto(
    val name: String,
    val subject: String,
    val dateofestablishment: LocalDate,
    val ISSN: Int,
    val price: Int,
    val language: String,
    val periodicity: Boolean,
    val articles: List<ArticleResponseDto>

)

data class MagazineResponseDto(
    val id: Long,
    val name: String,
    val subject: String,
    val dateofestablishment: LocalDate,
    val ISSN: Int,
    val price: Int,
    val language: String,
    val periodicity: Boolean,
    val articles: List<ArticleResponseDto>
)  {
    companion object {
        fun fromEntity(magazine: Magazine): MagazineResponseDto {

            val articleDtos = magazine.articles.map { ArticleResponseDto.fromEntity(it) }

            return MagazineResponseDto(
                    id = magazine.id,
                    name = magazine.name,
                    subject = magazine.subject,
                    dateofestablishment = magazine.dateofestablishment,
                    ISSN = magazine.ISSN,
                    price = magazine.price,
                    language = magazine.language,
                    periodicity = magazine.periodicity,
                    articles = articleDtos
            )
        }
    }
}

data class ArticleRequestDto(
    val id: Long,
    val name: String,
    val author: String,
    val numberofwords: Int,
    val numberoflinks: Int,
    val dateofwriting: LocalDate,
    val originallanguage: Boolean
)

data class ArticleResponseDto(
    val id: Long,
    val name: String,
    val author: String,
    val numberofwords: Int,
    val numberoflinks: Int,
    val dateofwriting: LocalDate,
    val originallanguage: Boolean
) {
    companion object {
        fun fromEntity(article: Article): ArticleResponseDto {
            return ArticleResponseDto(
                    id = article.id,
                    name = article.name,
                    author = article.author,
                    numberofwords = article.numberofwords,
                    numberoflinks = article.numberoflinks,
                    dateofwriting = article.dateofwriting,
                    originallanguage = article.originallanguage
            )
        }
    }
}


