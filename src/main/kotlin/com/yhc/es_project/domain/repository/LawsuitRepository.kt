package com.yhc.es_project.domain.repository

import com.yhc.es_project.domain.entity.Lawsuit
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface LawsuitRepository : ElasticsearchRepository<Lawsuit, String> {
    fun findByFullTextContaining(keyword: String): List<Lawsuit>
}
