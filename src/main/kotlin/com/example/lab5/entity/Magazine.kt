package com.example.lab5.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Magazine(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var name: String,
    var subject: String,
    val dateofestablishment: LocalDate,
    val ISSN: Int,
    val price: Int,
    val language: String,
    val periodicity: Boolean,
    @ManyToMany(fetch = FetchType.EAGER)
    val articles: MutableList<Article> = mutableListOf()
)