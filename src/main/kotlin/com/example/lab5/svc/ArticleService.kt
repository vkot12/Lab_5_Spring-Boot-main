package com.example.lab5.svc

import com.example.lab5.dto.ArticleRequestDto
import com.example.lab5.dto.ArticleResponseDto

interface ArticleService {

    fun getArticleById(id: Long): ArticleResponseDto?
    fun createArticle(articleDto: ArticleRequestDto): ArticleResponseDto
    fun updateArticle(id: Long, articleDto: ArticleRequestDto): ArticleResponseDto?
    fun deleteArticle(id: Long): Boolean
}