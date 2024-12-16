package com.yhc.es_project.adapter.web

import com.yhc.es_project.domain.application.LawsuitService
import com.yhc.es_project.domain.entity.Lawsuit
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LawsuitController(private val lawsuitService: LawsuitService) {

    @GetMapping("/search")
    fun searchLawsuits(@RequestParam keyword: String): List<Lawsuit> {
        println("searching")
        return lawsuitService.searchLawsuits(keyword)
    }
}

