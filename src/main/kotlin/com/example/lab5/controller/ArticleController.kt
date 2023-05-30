package com.example.lab5.controller

import com.example.lab5.dto.ArticleRequestDto
import com.example.lab5.dto.ArticleResponseDto
import com.example.lab5.svc.ArticleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/articles")
class ArticleController(private val articleService: ArticleService) {
    @GetMapping("/{id}")
    fun getArticleById(@PathVariable id: Long): ResponseEntity<ArticleResponseDto> {
        val article = articleService.getArticleById(id)
        return if (article != null) {
            ResponseEntity.ok(article)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createArticle(@RequestBody articleDto: ArticleRequestDto): ResponseEntity<ArticleResponseDto> {
        val createdArticle = articleService.createArticle(articleDto)
        return ResponseEntity.created(URI.create("/articles/${createdArticle.id}")).body(createdArticle)
    }
    @PutMapping("/{id}")
    fun updateArticle(@PathVariable id: Long, @RequestBody articleDto: ArticleRequestDto): ResponseEntity<ArticleResponseDto> {
        val updatedArticle = articleService.updateArticle(id, articleDto)
        return if (updatedArticle != null) {
            ResponseEntity.ok(updatedArticle)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: Long): ResponseEntity<Boolean> {
        val isDeleted = articleService.deleteArticle(id)
        return if (isDeleted) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}