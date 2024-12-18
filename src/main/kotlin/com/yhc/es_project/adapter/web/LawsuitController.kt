package com.yhc.es_project.adapter.web

import com.yhc.es_project.domain.application.LawsuitService
import com.yhc.es_project.domain.entity.Lawsuit
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lawsuits")
class LawsuitController(private val lawsuitService: LawsuitService) {

    @GetMapping("/search")
    fun searchLawsuits(
        @RequestParam keyword: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<Lawsuit>> {
        println("searching")
        val searchResults = lawsuitService.searchLawsuits(keyword, page, size)
        return ResponseEntity.ok(searchResults)
    }
}

