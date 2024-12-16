package com.yhc.es_project.domain.repository

import com.yhc.es_project.domain.entity.Product
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface ProductRepository : ElasticsearchRepository<Product, String> {
    fun findByNameContaining(name: String): List<Product>
    fun findProductById(id: String): Product?
}