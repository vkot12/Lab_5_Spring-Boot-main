package com.example.lab5.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate
@Entity
data class Article(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var name: String,
    var author: String,
    val numberofwords: Int,
    val numberoflinks: Int,
    val dateofwriting: LocalDate,
    val originallanguage: Boolean
)