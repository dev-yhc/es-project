package com.yhc.es_project.domain.application

import com.yhc.es_project.domain.entity.Lawsuit
import com.yhc.es_project.domain.repository.LawsuitRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LawsuitService(private val lawsuitRepository: LawsuitRepository) {
    fun searchLawsuits(keyword: String, page: Int, size: Int): Page<Lawsuit> {
        val pageable = PageRequest.of(page, size)
        return lawsuitRepository.searchLawsuits(keyword, pageable)
    }
}
