package com.yhc.es_project.domain.application

import com.yhc.es_project.domain.entity.Lawsuit
import com.yhc.es_project.domain.repository.LawsuitRepository
import org.springframework.stereotype.Service

@Service
class LawsuitService(private val lawsuitRepository: LawsuitRepository) {

    fun searchLawsuits(keyword: String): List<Lawsuit> {
        return lawsuitRepository.findByFullTextContaining(keyword)
    }
}
