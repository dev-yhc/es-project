package com.yhc.es_project.domain.application

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery
import com.yhc.es_project.adapter.web.UpdateProductRequest
import com.yhc.es_project.domain.entity.Product
import com.yhc.es_project.domain.repository.ProductRepository
import org.springframework.data.elasticsearch.client.elc.NativeQuery
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.stereotype.Service

// TODO - rename to productUseCase if necessary
@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val elasticsearchOperations: ElasticsearchOperations,
) {

    fun createProduct(product: Product): Product {
        return productRepository.save(product)
    }

    fun getProductById(id: String): Product {
        return productRepository.findProductById(id) ?: throw ProductNotFoundException("Product not found with id: $id")
    }

    fun updateProduct(id: String, request: UpdateProductRequest): Product {
        val product = productRepository.findProductById(id) ?: throw ProductNotFoundException("Product not found with id: $id")

        product.name = request.name
        product.price = request.price
        product.description = request.description
        return productRepository.save(product)
    }

    fun deleteProduct(id: String) {
        if (!productRepository.existsById(id)) {
            throw ProductNotFoundException("Product not found with id: $id")
        }
        productRepository.deleteById(id)
    }

    fun searchProducts(query: String): List<Product> {
        return productRepository.findByNameContaining(query)
    }

    fun getAllProducts(): List<Product> {
        val searchQuery = NativeQuery.builder()
            .withQuery { q -> q.matchAll(MatchAllQuery.of { it }) }
            .build()

        return elasticsearchOperations.search(searchQuery, Product::class.java)
            .searchHits
            .map { it.content }
    }
}

class ProductNotFoundException(message: String) : RuntimeException(message)
