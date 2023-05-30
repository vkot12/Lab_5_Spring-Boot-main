package com.example.lab5.controller

import com.example.lab5.dto.MagazineRequestDto
import com.example.lab5.dto.MagazineResponseDto
import com.example.lab5.svc.MagazineService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/magazines")
class MagazineController(private val magazineService: MagazineService) {

    @GetMapping("/{id}")
    fun getMagazineById(@PathVariable id: Long): ResponseEntity<MagazineResponseDto> {
        val magazine = magazineService.getMagazineById(id)
        return if (magazine != null) {
            ResponseEntity.ok(magazine)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    @PostMapping
    fun createMagazine(@RequestBody magazineRequest: MagazineRequestDto): ResponseEntity<MagazineResponseDto> {
        val createdMagazine = magazineService.createMagazine(magazineRequest)
        return ResponseEntity.created(URI.create("/magazines/${createdMagazine.id}")).body(createdMagazine)
    }

    @PutMapping("/{id}")
    fun updateMagazine(
        @PathVariable id: Long,
        @RequestBody magazineRequest: MagazineRequestDto
    ): ResponseEntity<MagazineResponseDto> {
        val updatedMagazine = magazineService.updateMagazine(id, magazineRequest)
        return if (updatedMagazine != null) {
            ResponseEntity.ok(updatedMagazine)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    @DeleteMapping("/{id}")
    fun deleteMagazine(@PathVariable id: Long): ResponseEntity<Boolean> {
        val deleted = magazineService.deleteMagazine(id)
        return if (deleted) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}