package com.example.lab5.svc.impl

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import com.example.lab5.dto.ArticleRequestDto
import com.example.lab5.dto.ArticleResponseDto
import com.example.lab5.entity.Article
import com.example.lab5.svc.ArticleService
import com.example.lab5.repository.ArticleRepository

@Service
class ArticleServiceImpl(
    private val articleRepository: ArticleRepository
) : ArticleService {

    @PreAuthorize("hasRole('EDITOR')")
    override fun createArticle(articleRequest: ArticleRequestDto): ArticleResponseDto {
        val articles = Article (name = articleRequest.name, author = articleRequest.author, numberofwords = articleRequest.numberofwords, numberoflinks = articleRequest.numberoflinks, dateofwriting = articleRequest.dateofwriting, originallanguage = articleRequest.originallanguage)
        val savedArticles = articleRepository.save(articles)
        return ArticleResponseDto.fromEntity(savedArticles)
    }
    @PreAuthorize("hasAnyRole('EDITOR', 'VIEWER')")
    override fun getArticleById(id: Long): ArticleResponseDto {
        val articles = articleRepository.findById(id).orElseThrow()
        return ArticleResponseDto.fromEntity(articles)
    }
    @PreAuthorize("hasRole('EDITOR')")
    override fun updateArticle(id: Long, articleRequest: ArticleRequestDto): ArticleResponseDto {
        val articles = articleRepository.findById(id).orElseThrow()
        articles.name = articleRequest.name
        articles.author = articleRequest.author
        val updateArticles = articleRepository.save(articles)
        return ArticleResponseDto.fromEntity(updateArticles)
    }
    @PreAuthorize("hasRole('EDITOR')")
    override fun deleteArticle(id: Long): Boolean {
        return if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}