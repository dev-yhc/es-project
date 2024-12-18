package com.yhc.es_project.domain.repository

import com.yhc.es_project.domain.entity.Lawsuit
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomLawsuitRepository {
    fun searchLawsuits(keyword: String, pageable: Pageable): Page<Lawsuit>
}