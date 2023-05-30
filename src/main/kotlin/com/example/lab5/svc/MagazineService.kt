package com.example.lab5.svc

import com.example.lab5.dto.MagazineRequestDto
import com.example.lab5.dto.MagazineResponseDto

interface MagazineService {

    fun getMagazineById(id: Long): MagazineResponseDto?
    fun createMagazine(magazineDto: MagazineRequestDto): MagazineResponseDto
    fun updateMagazine(id: Long, magazineDto: MagazineRequestDto): MagazineResponseDto?
    fun deleteMagazine(id: Long): Boolean
}